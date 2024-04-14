package com.forescout.cysiv.keyvault.config;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.keys.KeyClient;
import com.azure.security.keyvault.keys.KeyClientBuilder;
import com.azure.security.keyvault.keys.cryptography.CryptographyClient;
import com.azure.security.keyvault.keys.cryptography.CryptographyClientBuilder;
import com.azure.security.keyvault.keys.models.KeyVaultKey;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class AzureCredentialConfiguration {

    @Value("${azure.keyvault.uri}")
    private String keyVaultUri;

    @Value("${FORESCOUT-PFX-TEST-CERT}")
    private String certificateName;

    @Bean
    public SecretClient getSecretClient() {

        return new SecretClientBuilder()
            .vaultUrl(keyVaultUri)
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();
    }

    @Bean
    public KeyVaultKey getKeyVaultKey() {

        KeyClient keyClient = new KeyClientBuilder()
            .vaultUrl(keyVaultUri)
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();

        return keyClient.getKey(certificateName);
    }

    @Bean
    public CryptographyClient getCryptographyClient(KeyVaultKey keyVaultKey) {

        return new CryptographyClientBuilder()
            .keyIdentifier(keyVaultKey.getId())
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();
    }

    @Bean
    public RSAPublicKey getRSAPublicKey(KeyVaultKey keyVaultKey) {
        return (RSAPublicKey) keyVaultKey.getKey().toRsa().getPublic();
    }

    @Bean
    public RSAPrivateKey getRSAPrivateKey(KeyVaultKey keyVaultKey) {
        return (RSAPrivateKey) keyVaultKey.getKey().toRsa().getPrivate();
    }
}
