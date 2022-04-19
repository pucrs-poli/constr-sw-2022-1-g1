package br.pucrs.users.conections;


import br.pucrs.users.config.AppConfig;
import br.pucrs.users.models.User;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class KeyCloakConnection {

    @Autowired
    private AppConfig appConfig;
    private RestTemplate restTemplate;

    public void logout(String refreshToken) throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", appConfig.getClientId());
        map.add("client_secret", appConfig.getClientSecret());
        map.add("refresh_token", refreshToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, null);
        restTemplate.postForObject(appConfig.getKeycloakLogout(), request, String.class);
    }

    private String getUserInfo(String token) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
        return restTemplate.postForObject(appConfig.getKeycloakUserInfo(), request, String.class);
    }

    public void createUser(User userToBeCreated) {
        Keycloak keycloak = KeycloakBuilder
                .builder()
                .serverUrl(appConfig.getServerUrl())
                .realm(appConfig.getRealm())
                .username(appConfig.getKeyCloakUsername())
                .password(appConfig.getKeyCloakPassword())
                .clientId(appConfig.getClientId())
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue("12345678");

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userToBeCreated.getEmail());
        userRepresentation.setFirstName("Remus");
        userRepresentation.setLastName("Lupin");
        userRepresentation.setEnabled(true);
        userRepresentation.setCredentials(Arrays.asList(credentialRepresentation));
        keycloak.realm(appConfig.getRealm()).users().create(userRepresentation);

        UsersResource usersResource = keycloak.realm(appConfig.getRealm()).users();
        UserRepresentation user = usersResource.search("lupin").get(0);
        user.setEmail("lupin@hogwarts.co.uk");
        usersResource.get(user.getId()).update(user);
    }
}
