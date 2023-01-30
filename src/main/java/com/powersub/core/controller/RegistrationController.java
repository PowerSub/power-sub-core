package com.powersub.core.controller;

import com.powersub.core.entity.AccountDTO;
import com.powersub.core.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {


    private final RegistrationService registrationService;


    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public String GetCredentials(@RequestBody AccountDTO account) {
        if (registrationService.isValidCredentials(account)) {
            registrationService.register(account);
            return "New account registered successfully";
        } else {
            return "Registration failed. Bad credentials!";
        }
    }


}
