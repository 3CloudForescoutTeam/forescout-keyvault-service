package com.forescout.cysiv.keyvault;

import com.forescout.cysiv.keyvault.config.KeyVaultProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(KeyVaultProperties.class)
public class KeyVaultServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(KeyVaultServiceApplication.class, args);
    }
}
