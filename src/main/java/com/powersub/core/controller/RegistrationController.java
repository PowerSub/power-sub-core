package com.powersub.core.controller;

import com.powersub.core.entity.AccountDTO;
import com.powersub.core.exception.InvalidCredentialsException;
import com.powersub.core.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class RegistrationController {


    private final RegistrationService registrationService;


    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Operation(summary = "Registration a new account", tags = "registration")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "registration is successful",
                    content = {
                            @Content(
                                    mediaType = "application/json")
                    })
    })
    @PostMapping("/registration")
    public String getCredentials(@RequestBody AccountDTO account) {

        try {
            registrationService.register(account);
            return "New account registered successfully";
        } catch (InvalidCredentialsException e) {
            return "Registration failed. Bad credentials!";
        }
    }

    @PostMapping("/login")
    public void authenticate(@RequestBody AccountDTO account) {

    }
}
