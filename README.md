# Forescout KeyVault Application

Welcome to the Forescout KeyVault Demo Application! 
This demo application is designed to showcase how Spring Boot Applications integrate with Azure KeyVault.

## Azure Key Vault

[Azure Key Vault](https://learn.microsoft.com/en-us/azure/key-vault/general/overview) is a cloud service provided by Microsoft Azure that allows you to securely store and manage sensitive information such as passwords, encryption keys, certificates, and secrets. 
It provides a centralized repository for managing cryptographic keys and secrets, helping you to protect your sensitive data and meet compliance requirements.

Here are some key features and capabilities of [Azure Key Vault](https://learn.microsoft.com/en-us/azure/key-vault/general/overview):

- Secure Storage: Azure Key Vault provides a secure and centralized location for storing sensitive information such as passwords, encryption keys, and secrets. Data is encrypted at rest and in transit, ensuring confidentiality and integrity.
- Key Management: Key Vault allows you to create, import, and manage cryptographic keys used for encryption, decryption, and signing operations. It supports both software-protected keys and hardware security modules (HSMs) for enhanced security.
- Secret Management: You can store and manage application secrets, connection strings, API keys, and other sensitive information securely in Key Vault. Secrets are stored encrypted and can be accessed programmatically using Azure APIs or SDKs.
- Certificate Management: Key Vault supports the management of digital certificates, including certificate creation, import, renewal, and deletion. It provides a centralized repository for storing and managing SSL/TLS certificates, reducing the complexity of certificate management.

## Use Cases

Here are Use Cases for Azure Key Vault, based on the Requirements Analysis of the [Cysiz Application](https://dev.azure.com/3Cloud/Forescout%20%E2%80%93%20Replatform%20Program%20%E2%80%93%20Delivery%20Backlog/_backlogs/backlog/JIRA%20Backlog/Stories):

- Cosmosdb for Postgres
  - Use Azure Key Vault to securely store database credentials
- JWT Token
  - Use Azure Key Vault Secrets to secure a Sign Key used to sign token.
  - Use Azure Key Vault Private Key (Private Keys created withing Azure KeyVault never leave the Vault) to sign token.
  - Azure Key Vault Certificate (can import Certificates signed by a Certificate Authority (CA) or generate one within KeyVault) to sign token.
- mTLS
  - Use Azure Key Vault to manage digital Certificates to authenticate identities using mTLS (Mutual Transport Layer Security)
- API Keys
  - Use Azure Key Vault Secrets to manage API Keys

## Scope Definition

- For this demo, we will shows case the 3 JWT Uses Case above.
- mTLS and API Keys are out or scope.

  
## Running Application

1. Clone the repository [forescout-keyvault-service](https://github.com/3CloudForescoutTeam/forescout-keyvault-service)
2. Switch to the develop branch
3. Build project using Maven
4. Start Application (use local profile if testing locally on laptop)

## Testing

The Cosmosdb for Postgres database has been pre-populated with test data (forescout | password123).
We will use PostMan to test the Login API. This API will showcase how to generate tokens using the 3 approaches in uses case above.

1. Create an HTTP POST Request to <hostname>/forescout-keyvault-service/api/v1/login. To test locally use: http://localhost:8085/forescout-keyvault-service/api/v1/login
2. Use the payload below to authenticate the test use using the login API:  {
   "username": "forescout",
   "password": "password123"
   }
3. After executing request, you will see the 3 tokens as the response.




