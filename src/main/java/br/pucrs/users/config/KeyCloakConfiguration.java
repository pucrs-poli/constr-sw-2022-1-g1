package br.pucrs.users.config;

import br.pucrs.users.utils.Constants;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:keycloak.properties")
public class KeyCloakConfiguration {

    @Bean
    public Keycloak keycloak(Constants conf) {
        return KeycloakBuilder.builder()
            .serverUrl(conf.serverUrl)
            .realm(conf.realm)
            .grantType(OAuth2Constants.PASSWORD)
            .username(conf.username)
            .password(conf.password)
            .clientId(conf.clientId)
            .clientSecret(conf.clientSecret)
            .resteasyClient(
                new ResteasyClientBuilder()
                    .connectionPoolSize(10)
                    .build()
            )
            .build();
    }


}
