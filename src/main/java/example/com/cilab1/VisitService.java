package example.com.cilab1;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
public class VisitService {

    static final String CACHE_KEY = "visits_count";
    static final Duration CACHE_TTL = Duration.ofSeconds(10);

    private final VisitRepository repository;
    private final StringRedisTemplate redis;

    public VisitService(VisitRepository repository, StringRedisTemplate redis) {
        this.repository = repository;
        this.redis = redis;
    }

    @Transactional
    public VisitsResponse registerVisit() {
        String cached = redis.opsForValue().get(CACHE_KEY);
        if (cached != null) {
            return new VisitsResponse(Long.parseLong(cached), "cache", null);
        }

        Visit saved = repository.save(new Visit());
        long count = repository.count();

        redis.opsForValue().set(CACHE_KEY, String.valueOf(count), CACHE_TTL);
        return new VisitsResponse(count, "db", saved.getId());
    }
}