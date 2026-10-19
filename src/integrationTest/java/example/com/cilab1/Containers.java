package example.com.cilab1;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.postgresql.PostgreSQLContainer;

final class Containers {

    private Containers() {
    }

    static PostgreSQLContainer postgres() {
        return new PostgreSQLContainer("postgres:16");
    }

    static GenericContainer<?> redis() {
        return new GenericContainer<>("redis:7").withExposedPorts(6379);
    }
}