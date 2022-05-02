package br.pucrs.users.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@NoArgsConstructor
public class Constants {
    
    @Value("${keycloak.server.url}")
    public String serverUrl;

    @Value("${keycloak.realm}")
    public String realm;

    @Value("${keycloak.client.id:}")
    public String clientId;

    @Value("${keycloak.client.secret:}")
    public String clientSecret;

    @Value("${keycloak.username:}")
    public String username;

    @Value("${keycloak.password:}")
    public String password;
}
