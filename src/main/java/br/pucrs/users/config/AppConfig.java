package br.pucrs.users.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConfig {

    @Value("${keycloak.logout}")
    private String keycloakLogout;

    @Value("${keycloak.user-info-uri}")
    private String keycloakUserInfo;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Value("${}")
    private String keyCloakUsername;

    @Value("${}")
    private String serverUrl;

    @Value("${}")
    private String realm;

    @Value("${}")
    private String keyCloakPassword;
}
