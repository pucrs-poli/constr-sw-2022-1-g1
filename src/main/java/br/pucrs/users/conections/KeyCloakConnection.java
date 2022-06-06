package br.pucrs.users.conections;


import br.pucrs.users.config.AppConfig;
import br.pucrs.users.models.User;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.List;

@Component
public class KeyCloakConnection {

    @Autowired
    private Keycloak keycloak;

    @Autowired
    private AppConfig appConfig;

    public List<UserRepresentation> findAllUsers() {
        return keycloak.realm(appConfig.getRealm()).users().list();
    }

    public Response createUser(final User request) {
        CredentialRepresentation password = configUser(request.getPassword());
        UserRepresentation user = configPassword(request, password);
        return keycloak.realm(appConfig.getRealm()).users().create(user);
    }

    public void deleteUser(final String id) {
        keycloak.realm(appConfig.getRealm()).users().delete(id);
    }

    public void updatePassword(final User request, final String id) {
        UserRepresentation userRepresentation = keycloak.realm(appConfig.getRealm()).users().get(id).toRepresentation();
        CredentialRepresentation password = configUser(request.getPassword());
        UserRepresentation user = configPassword(request, password);
        user.setCredentials(List.of(password));
    }

    public UserRepresentation findUserById(final String id) {
        return keycloak.realm(appConfig.getRealm()).users().get(id).toRepresentation();
    }

    private CredentialRepresentation configUser(String password) {
        CredentialRepresentation cR = new CredentialRepresentation();
        cR.setTemporary(false);
        cR.setType(CredentialRepresentation.PASSWORD);
        cR.setValue(password);
        return cR;
    }

    private UserRepresentation configPassword(User request, CredentialRepresentation cR) {
        UserRepresentation newUser = new UserRepresentation();
        newUser.setUsername(request.getUsername());
        newUser.setCredentials(List.of(cR));
        newUser.setEnabled(true);
        return newUser;
    }

    public void authorize() {

    }
}
