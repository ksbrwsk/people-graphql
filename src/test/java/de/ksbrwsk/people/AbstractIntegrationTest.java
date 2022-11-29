package de.ksbrwsk.people;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class AbstractIntegrationTest {

    @Container
    public static PostgreSQLContainer<?> postgres =
            (PostgreSQLContainer<?>) new PostgreSQLContainer(DockerImageName.parse("postgres:14.1-alpine"))
                    .withInitScript("schema.sql");

    @DynamicPropertySource
    public static void register(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> postgres.getJdbcUrl());
        registry.add("spring.datasource.hikari.username", () -> postgres.getUsername());
        registry.add("spring.datasource.hikari.password", () -> postgres.getPassword());
    }
}
