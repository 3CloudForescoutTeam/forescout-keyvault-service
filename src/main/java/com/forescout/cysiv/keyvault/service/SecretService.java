package com.forescout.cysiv.keyvault.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecretService {

    @Value("${test-api-key}")
    private String testApiKey;

    @PostConstruct
    public void init() {
        log.info("found vault secret: {}", testApiKey);
    }
}
