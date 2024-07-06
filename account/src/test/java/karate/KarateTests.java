package karate;

import com.intuit.karate.junit5.Karate;
import com.josue.account.AccountApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {AccountApplication.class})
@Testcontainers
class KarateTests {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @Karate.Test
    Karate dummyTest() {
        return Karate.run("classpath:karate/dummy.feature");
    }

    @Karate.Test
    Karate accountCreateTest() {
        return Karate.run("classpath:karate/account/create.feature");
    }



}