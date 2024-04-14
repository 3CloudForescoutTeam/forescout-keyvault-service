package com.forescout.cysiv.keyvault.service;

import com.azure.security.keyvault.secrets.SecretClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//@Service
//@RequiredArgsConstructor
@Slf4j
public class SecretService {

    //private final SecretClient secretClient;

    //@Value("${test-api-key}")
    //private String testApiKey;

    //@PostConstruct
    public void init() {

        //String testApiKey = secretClient.getSecret("test-api-key").getValue();
        //log.info("found vault secret: {}", testApiKey);
    }
}
