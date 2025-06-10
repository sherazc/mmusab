package com.sc.mmusab.controller;

import com.sc.mmusab.dto.ScToken;
import com.sc.mmusab.service.auth.ScTokenGeneratorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginTokenController {
    private final ScTokenGeneratorService scTokenGeneratorService;

    public LoginTokenController(ScTokenGeneratorService scTokenGeneratorService) {
        this.scTokenGeneratorService = scTokenGeneratorService;
    }

    @GetMapping("/token")
    // Custom business logic to only let user use this endpoint who have ROLE_USER
    @PreAuthorize("hasRole('USER')")
    public ScToken token(Authentication authentication, @RequestParam String[] requestedScopes) {
        return scTokenGeneratorService.generateToken(authentication, requestedScopes);
    }
}
