package com.powersub.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.powersub.core.entity.AccountDTO;
import com.powersub.core.repository.AccountRepository;
import com.powersub.core.service.RegistrationService;

@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = PowersubCoreApplicationTests.DockerInitializer.class)
class PowersubCoreApplicationTests {

    @Container
    static final PostgreSQLContainer<?> sqlContainer = new PostgreSQLContainer<>("postgres:12");
    
    @Autowired
    RegistrationService registrationService;
    
    @Autowired
    AccountRepository repository;

    @Test
    void contextLoads() {
        var account = repository.findByEmail("aaa@aaa.ru");
        assertNull(account);
        registrationService.register(new AccountDTO("aaa@aaa.ru", "password"));
        account = repository.findByEmail("aaa@aaa.ru");
        assertNotNull(account);
        assertEquals("aaa@aaa.ru", account.getEmail());
        assertNotEquals("password", account.getPassword());
    }

    public static class DockerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    applicationContext,
                    "spring.datasource.url=" + sqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + sqlContainer.getUsername(),
                    "spring.datasource.password=" + sqlContainer.getPassword()
            );
        }
    }

}
