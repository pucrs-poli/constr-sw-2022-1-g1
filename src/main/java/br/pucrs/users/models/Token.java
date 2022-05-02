package br.pucrs.users.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {
    private String token_type;
    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String refresh_expires_in;
}

