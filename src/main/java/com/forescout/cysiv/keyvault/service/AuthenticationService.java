package com.forescout.cysiv.keyvault.service;

import com.forescout.cysiv.keyvault.model.JwtTokenDTO;
import com.forescout.cysiv.keyvault.model.LoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public JwtTokenDTO login(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        if (!authentication.isAuthenticated())
            throw new UsernameNotFoundException("Invalid Credentials. Username Not Found");

        return JwtTokenDTO.builder()
                .secretSignedToken(jwtService.generateSecretSignToken(loginDTO.getUsername()))
                .privateKeySignedToken(jwtService.generatePrivateKeySignToken(loginDTO.getUsername()))
                .certificateSignedToken(jwtService.generateCertificateSignToken(loginDTO.getUsername()))
                .build();
    }
}
