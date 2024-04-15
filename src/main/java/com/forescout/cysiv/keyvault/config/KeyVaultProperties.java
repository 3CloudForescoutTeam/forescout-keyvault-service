package com.forescout.cysiv.keyvault.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "azure.keyvault")
public class KeyVaultProperties {

    private String uri;
    private String pfxCertificate;
    private String azureCertificate;
    private String keyIdentifier;
    private String jwtSecretSignKey;
}
