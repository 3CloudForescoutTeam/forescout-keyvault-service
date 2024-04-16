package com.forescout.cysiv.keyvault.config;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.keys.KeyClient;
import com.azure.security.keyvault.keys.KeyClientBuilder;
import com.azure.security.keyvault.keys.cryptography.CryptographyClient;
import com.azure.security.keyvault.keys.cryptography.CryptographyClientBuilder;
import com.azure.security.keyvault.keys.models.KeyVaultKey;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class KeyVaultConfiguration {

    @Bean
    public SecretClient getSecretClient(KeyVaultProperties keyVaultProperties) {

        return new SecretClientBuilder()
            .vaultUrl(keyVaultProperties.getUri())
            .credential(new DefaultAzureCredentialBuilder()
                    .build())
            .buildClient();
    }

    @Bean
    public KeyClient keyClient(KeyVaultProperties keyVaultProperties) {
        return new KeyClientBuilder()
                .vaultUrl(keyVaultProperties.getUri())
                .credential(new DefaultAzureCredentialBuilder()
                        .build())
                .buildClient();
    }

    @Bean
    public KeyVaultKey getKeyVaultKey(KeyVaultProperties keyVaultProperties) {

        KeyClient keyClient = new KeyClientBuilder()
                .vaultUrl(keyVaultProperties.getUri())
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();

        return keyClient.getKey(keyVaultProperties.getPfxCertificate());
    }

    @Bean
    @Primary
    @Qualifier("certificateCryptographyClient")
    public CryptographyClient getCertificateCryptographyClient(KeyVaultKey keyVaultKey) {

        return new CryptographyClientBuilder()
            .keyIdentifier(keyVaultKey.getId())
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();
    }

    @Bean
    @Qualifier("privateKeyCryptographyClient")
    public CryptographyClient getPrivateKeyCryptographyClient(KeyVaultProperties keyVaultProperties) {

        return new CryptographyClientBuilder()
                .keyIdentifier(keyVaultProperties.getKeyIdentifier())
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
    }
}
