package example.com.cilab1;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record VisitsResponse(long count, String source, Long lastId) {
}
