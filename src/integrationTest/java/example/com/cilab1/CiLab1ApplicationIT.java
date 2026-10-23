package example.com.cilab1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

@SpringBootTest
@Testcontainers
class CiLab1ApplicationIT {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = Containers.postgres();

    @Container
    @ServiceConnection(name = "redis")
    static GenericContainer<?> redis = Containers.redis();

    @Test
    void contextLoads() {
    }
}