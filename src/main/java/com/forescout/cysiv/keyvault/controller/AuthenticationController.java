package com.forescout.cysiv.keyvault.controller;

import com.forescout.cysiv.keyvault.model.JwtTokenDTO;
import com.forescout.cysiv.keyvault.model.LoginDTO;
import com.forescout.cysiv.keyvault.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/api/v1/login")
    public ResponseEntity<JwtTokenDTO> AuthenticateAndGetToken(@RequestBody LoginDTO loginDTO) {

        log.info("Authenticating User {}...", loginDTO.getUsername());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        if (!authentication.isAuthenticated())
            throw new UsernameNotFoundException("Invalid Credentials. Username Not Found");

        JwtTokenDTO response =  JwtTokenDTO.builder()
                .accessToken(jwtService.GenerateToken(loginDTO.getUsername()))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
