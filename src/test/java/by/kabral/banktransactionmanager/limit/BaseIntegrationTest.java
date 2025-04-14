package by.kabral.banktransactionmanager.limit;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class BaseIntegrationTest {

  protected static final PostgreSQLContainer<?> postgresContainer =
          new PostgreSQLContainer<>(DockerImageName.parse("postgres:15"))
                  .withDatabaseName("testdb")
                  .withUsername("testuser")
                  .withPassword("testpass")
                  .withInitScript("db/scheme.sql");

  static {
    postgresContainer.start();
  }

  @DynamicPropertySource
  static void overrideProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgresContainer::getUsername);
    registry.add("spring.datasource.password", postgresContainer::getPassword);
  }
}
