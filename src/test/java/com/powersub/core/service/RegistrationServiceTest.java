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
import com.powersub.core.entity.AccountDTO;
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
        AccountDTO correctAccountDTO = new AccountDTO("ffff@ya.ru", "hhhddddrew");
        when(encoder.encode(correctAccountDTO.getPassword())).thenReturn("cryptoPassword");

        registrationService.register(correctAccountDTO);
        var accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);


        verify(accountRepository, Mockito.times(1)).save(accountArgumentCaptor.capture());

        var value = accountArgumentCaptor.getValue();
        assertNotNull(value);
        assertEquals(correctAccountDTO.getEmail(), value.getEmail());
        assertNotEquals(correctAccountDTO.getPassword(), value.getPassword());
        assertEquals("cryptoPassword", value.getPassword());
    }

    @Test
    void registerNegative() throws InvalidCredentialsException {
        AccountDTO incorrectAccountDTO1 = new AccountDTO("ffff@ya.ru", "hh hddddrew");
        AccountDTO incorrectAccountDTO2 = new AccountDTO("ffff@ya.ru", "hh");
        AccountDTO incorrectAccountDTO3 = new AccountDTO("ffff@ya.ru", "hhhhhhhhhhhhhhhh" +
                "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        AccountDTO incorrectAccountDTO4 = new AccountDTO("ffff@ya@ru", "hhhddddrew");
        AccountDTO incorrectAccountDTO5 = new AccountDTO("@ya.ru", "hhhddddrew");

        var exception = Assertions.assertThrowsExactly(InvalidCredentialsException.class, () -> 
            registrationService.register(incorrectAccountDTO1)
        );

        assertEquals(GenericExceptionCodes.INVALID_CREDENTIALS, exception.getCode());

        Assertions.assertThrowsExactly(InvalidCredentialsException.class, () -> 
            registrationService.register(incorrectAccountDTO2)
        );
        Assertions.assertThrowsExactly(InvalidCredentialsException.class, () -> 
            registrationService.register(incorrectAccountDTO3)
        );
        Assertions.assertThrowsExactly(InvalidCredentialsException.class, () -> 
            registrationService.register(incorrectAccountDTO4)
        );
        Assertions.assertThrowsExactly(InvalidCredentialsException.class, () -> 
            registrationService.register(incorrectAccountDTO5)
        );
    }

}