package com.powersub.core.service;

import com.powersub.core.entity.Account;
import com.powersub.core.entity.AccountAuthenticationDTO;
import com.powersub.core.exception.InvalidCredentialsException;
import com.powersub.core.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder encoder;

    private final Clock clock;

    public Account register(AccountAuthenticationDTO account) throws InvalidCredentialsException {
        if (isValidCredentials(account)) {
            Account acc = Account.builder()
                    .email(account.getEmail()).password(encoder.encode(account.getPassword()))
                    .createdAt(ZonedDateTime.now(clock))
                    .build();
            return accountRepository.save(acc);
        } else {
            throw new InvalidCredentialsException("Registration failed, wrong credentials");
        }
    }

    private boolean isValidCredentials(AccountAuthenticationDTO account) {
        return accountRepository.findByEmail(account.getEmail()) == null
                && EmailValidator.getInstance().isValid(account.getEmail())
                && !account.getPassword().contains(" ")
                && account.getPassword().length() >= 8
                && account.getPassword().length() <= 64;
    }
}
