package com.powersub.core.service;

import com.powersub.core.entity.Account;
import com.powersub.core.entity.AccountDTO;
import com.powersub.core.exception.InvalidCredentialsException;
import com.powersub.core.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder encoder;

    @Autowired
    public RegistrationService(AccountRepository accountRepository, PasswordEncoder encoder) {
        this.accountRepository = accountRepository;
        this.encoder = encoder;
    }

    public void register(AccountDTO account) throws InvalidCredentialsException {
        if (isValidCredentials(account)) {
            Account acc = Account.builder().
                    email(account.getEmail()).password(encoder.encode(account.getPassword())).
                    build();
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
