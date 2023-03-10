package com.powersub.core.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Clock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.powersub.core.entity.Account;
import com.powersub.core.entity.AccountAuthenticationDTO;
import com.powersub.core.exception.GenericExceptionCodes;
import com.powersub.core.exception.InvalidCredentialsException;
import com.powersub.core.repository.AccountRepository;

@SpringJUnitConfig
class RegistrationServiceTest {
    @MockBean
    AccountRepository accountRepository;
    @MockBean
    PasswordEncoder encoder;

    RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        Clock clock2 = Clock.systemUTC();
        registrationService = new RegistrationService(accountRepository, encoder, clock2);
    }

    @Test
    void registerPositive() throws InvalidCredentialsException {
        AccountAuthenticationDTO correctAccountAuthenticationDTO = new AccountAuthenticationDTO("ffff@ya.ru", "hhhddddrew");
        when(encoder.encode(correctAccountAuthenticationDTO.getPassword())).thenReturn("cryptoPassword");

        registrationService.register(correctAccountAuthenticationDTO);
        var accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);


        verify(accountRepository, Mockito.times(1)).save(accountArgumentCaptor.capture());

        var value = accountArgumentCaptor.getValue();
        assertNotNull(value);
        assertEquals(correctAccountAuthenticationDTO.getEmail(), value.getEmail());
        assertNotEquals(correctAccountAuthenticationDTO.getPassword(), value.getPassword());
        assertEquals("cryptoPassword", value.getPassword());
    }

    @Test
    void registerNegative() throws InvalidCredentialsException {
        AccountAuthenticationDTO incorrectAccountAuthenticationDTO1 = new AccountAuthenticationDTO("ffff@ya.ru", "hh hddddrew");
        AccountAuthenticationDTO incorrectAccountAuthenticationDTO2 = new AccountAuthenticationDTO("ffff@ya.ru", "hh");
        AccountAuthenticationDTO incorrectAccountAuthenticationDTO3 = new AccountAuthenticationDTO("ffff@ya.ru", "hhhhhhhhhhhhhhhh" +
                "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        AccountAuthenticationDTO incorrectAccountAuthenticationDTO4 = new AccountAuthenticationDTO("ffff@ya@ru", "hhhddddrew");
        AccountAuthenticationDTO incorrectAccountAuthenticationDTO5 = new AccountAuthenticationDTO("@ya.ru", "hhhddddrew");

        var exception = Assertions.assertThrowsExactly(InvalidCredentialsException.class, () -> 
            registrationService.register(incorrectAccountAuthenticationDTO1)
        );

        assertEquals(GenericExceptionCodes.INVALID_CREDENTIALS, exception.getCode());

        Assertions.assertThrowsExactly(InvalidCredentialsException.class, () -> 
            registrationService.register(incorrectAccountAuthenticationDTO2)
        );
        Assertions.assertThrowsExactly(InvalidCredentialsException.class, () -> 
            registrationService.register(incorrectAccountAuthenticationDTO3)
        );
        Assertions.assertThrowsExactly(InvalidCredentialsException.class, () -> 
            registrationService.register(incorrectAccountAuthenticationDTO4)
        );
        Assertions.assertThrowsExactly(InvalidCredentialsException.class, () -> 
            registrationService.register(incorrectAccountAuthenticationDTO5)
        );
    }

}