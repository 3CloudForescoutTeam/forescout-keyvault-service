package com.forescout.cysiv.keyvault.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//@Data
@Configuration
@ConfigurationProperties(prefix = "azure.keyvault")
public class KeyVaultProperties {


    private String uri;
    @Value("${CYSIZ-PFX-CERT}")
    private String pfxCertificate;
    @Value("${CYSIZ-AZURE-CERT}")
    private String azureCertificate;
    @Value("${keyIdentifier")
    private String keyIdentifier;
    @Value("${JWT-TOKEN-SIGN-KEY}")
    private String jwtSecretSignKey;

    @Value("${CYSIZ-PG-DB-URL}")
    private String dbUrl;

    @Value("${CYSIZ-PG-DB-USER}")
    private String dbUser;
    @Value("${CYSIZ-PG-DB-PSWD}")
    private String dbPassword;
    @Value("${JWT-TOKEN-SIGN-KEY}")
    private String tokenSignKey;
    @Value("${test-api-key}")
    private String testApiKey;


    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getTokenSignKey() {
        return tokenSignKey;
    }

    public void setTokenSignKey(String tokenSignKey) {
        this.tokenSignKey = tokenSignKey;
    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPfxCertificate() {
        return pfxCertificate;
    }

    public void setPfxCertificate(String pfxCertificate) {
        this.pfxCertificate = pfxCertificate;
    }

    public String getAzureCertificate() {
        return azureCertificate;
    }

    public void setAzureCertificate(String azureCertificate) {
        this.azureCertificate = azureCertificate;
    }

    public String getKeyIdentifier() {
        return keyIdentifier;
    }

    public void setKeyIdentifier(String keyIdentifier) {
        this.keyIdentifier = keyIdentifier;
    }

    public String getJwtSecretSignKey() {
        return jwtSecretSignKey;
    }

    public void setJwtSecretSignKey(String jwtSecretSignKey) {
        this.jwtSecretSignKey = jwtSecretSignKey;
    }

    public String getTestApiKey() {
        return testApiKey;
    }

    public void setTestApiKey(String testApiKey) {
        this.testApiKey = testApiKey;
    }
}
