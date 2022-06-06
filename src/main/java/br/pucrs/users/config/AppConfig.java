package br.pucrs.users.config;

import lombok.Data;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AppConfig {

    @Value("${keycloak.authorization-grant-type}")
    private String keycloakGrantType;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Value("${keycloak.admin.username}")
    private String username;

    @Value("${keycloak.admin.password}")
    private String password;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId("login-app")
                .clientSecret("yjHaEaOOYs8JasHdKt3PCMv90dLETnDr")
                .grantType(OAuth2Constants.PASSWORD)
                .username("admin")
                .password("admin")
                .resteasyClient(new ResteasyClientBuilder()
                        .connectionPoolSize(10)
                        .build()
                )
                .build();
    }
}
