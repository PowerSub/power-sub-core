package com.powersub.core.service;

import java.time.Clock;
import java.time.ZonedDateTime;

import com.powersub.core.entity.Account;
import com.powersub.core.entity.AccountDTO;
import com.powersub.core.exception.InvalidCredentialsException;
import com.powersub.core.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder encoder;
    
    private final Clock clock;

    public void register(AccountDTO account) throws InvalidCredentialsException {
        if (isValidCredentials(account)) {
            Account acc = Account.builder()
                    .email(account.getEmail()).password(encoder.encode(account.getPassword()))
                    .createdAt(ZonedDateTime.now(clock))
                    .build();
            accountRepository.save(acc);
        } else {
            throw new InvalidCredentialsException();
        }
    }

    private boolean isValidCredentials(AccountDTO account) {
        boolean result = true;

        if (accountRepository.findByEmail(account.getEmail()) != null
                || !account.getEmail().contains("@")
                || account.getEmail().contains(" ")
                || account.getPassword().contains(" ")
                || account.getPassword().length() < 8
                || account.getPassword().length() > 64
                || account.getEmail().length() > 256
                || account.getEmail().length() < 1
        ) {
            result = false;
        }

        return result;
    }
}
