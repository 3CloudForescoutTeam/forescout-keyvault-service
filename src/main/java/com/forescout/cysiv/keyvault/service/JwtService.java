package com.forescout.cysiv.keyvault.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.azure.security.keyvault.keys.cryptography.CryptographyClient;
import com.forescout.cysiv.keyvault.config.KeyVaultProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

    private final KeyVaultProperties keyVaultProperties;

    @Qualifier("privateKeyCryptographyClient")
    private final CryptographyClient certificateCryptographyClient;

    @Qualifier("certificateCryptographyClient")
    private final CryptographyClient privateKeyCryptographyClient;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    private Boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createSignKeyToken(claims, username);
    }

    private String createSignKeyToken(Map<String, Object> claims, String username) {

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
            .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private String createCertificateSignToken(Map<String, Object> claims, String username) {

        String token = JWT.create()
            .withClaim(username, claims)
            .withSubject(username)
            .withIssuedAt(new Date(System.currentTimeMillis()))
            .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*30))
            .sign(Algorithm.none());

        byte[] signature = certificateCryptographyClient.signData(com.azure.security.keyvault.keys.cryptography.models.SignatureAlgorithm.RS256,
                token.getBytes()).getSignature();
        return token + "." + Base64.getUrlEncoder().encodeToString(signature);
    }

    private String createPrivateKeySignToken(Map<String, Object> claims, String username) {

        String token = JWT.create()
                .withClaim(username, claims)
                .withSubject(username)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*30))
                .withIssuer("https://www.forescout.com")
                .sign(Algorithm.none());

        byte[] dataBytes = token.getBytes(StandardCharsets.UTF_8);
        byte[] signature = privateKeyCryptographyClient.signData(com.azure.security.keyvault.keys.cryptography.models.SignatureAlgorithm.RS256,
                dataBytes).getSignature();

        return Base64.getEncoder().encodeToString(signature);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(keyVaultProperties.getJwtSecretSignKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}