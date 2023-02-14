package com.powersub.core.service;

import com.powersub.core.entity.AccountDTO;
import com.powersub.core.exception.InvalidCredentialsException;
import com.powersub.core.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Clock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {
    @Mock
    AccountRepository accountRepository;
    @Mock
    PasswordEncoder encoder;

    @Test
    void register() throws InvalidCredentialsException {
        Clock clock2 = Clock.systemUTC();
        AccountDTO correctAccountDTO = new AccountDTO("ffff@ya.ru", "hhhddddrew");
        AccountDTO incorrectAccountDTO1 = new AccountDTO("ffff@ya.ru", "hh hddddrew");
        AccountDTO incorrectAccountDTO2 = new AccountDTO("ffff@ya.ru", "hh");
        AccountDTO incorrectAccountDTO3 = new AccountDTO("ffff@ya.ru", "hhhhhhhhhhhhhhhh" +
                "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        AccountDTO incorrectAccountDTO4 = new AccountDTO("ffff@ya@ru", "hhhddddrew");
        AccountDTO incorrectAccountDTO5 = new AccountDTO("@ya.ru", "hhhddddrew");


        RegistrationService registrationService = new RegistrationService(accountRepository, encoder, clock2);
        when(encoder.encode(correctAccountDTO.getPassword())).thenReturn("cryptoPassword");

        registrationService.register(correctAccountDTO);
        verify(accountRepository, Mockito.times(1)).save(Mockito.any());

        Assertions.assertThrowsExactly(InvalidCredentialsException.class, () -> {
            registrationService.register(incorrectAccountDTO1);
        });
        Assertions.assertThrowsExactly(InvalidCredentialsException.class, () -> {
            registrationService.register(incorrectAccountDTO2);
        });
        Assertions.assertThrowsExactly(InvalidCredentialsException.class, () -> {
            registrationService.register(incorrectAccountDTO3);
        });
        Assertions.assertThrowsExactly(InvalidCredentialsException.class, () -> {
            registrationService.register(incorrectAccountDTO4);
        });
        Assertions.assertThrowsExactly(InvalidCredentialsException.class, () -> {
            registrationService.register(incorrectAccountDTO5);
        });
    }

}