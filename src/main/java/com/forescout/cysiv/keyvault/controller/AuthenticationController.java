package com.forescout.cysiv.keyvault.controller;

import com.forescout.cysiv.keyvault.model.JwtTokenDTO;
import com.forescout.cysiv.keyvault.model.LoginDTO;
import com.forescout.cysiv.keyvault.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/api/v1/login")
    public ResponseEntity<JwtTokenDTO> login(@RequestBody LoginDTO loginDTO) {

        log.info("Authenticating User {}...", loginDTO.getUsername());
        return new ResponseEntity<>(authenticationService.login(loginDTO), HttpStatus.OK);
    }
}
