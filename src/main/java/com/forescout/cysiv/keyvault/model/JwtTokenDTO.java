package com.forescout.cysiv.keyvault.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtTokenDTO {

    private String secretSignedToken;
    private String privateKeySignedToken;
    private String certificateSignedToken;
}
