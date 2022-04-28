package br.pucrs.users.services;

import br.pucrs.users.conections.KeyCloakConnection;
import br.pucrs.users.models.User;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private KeyCloakConnection keyCloakConnection;

    public void createUser(User user) {
        keyCloakConnection.createUser(user);
    }

    public void deleteUser(String userId) {
        keyCloakConnection.deleteUser(userId);
    }

    public void updateUser(User user) {
        keyCloakConnection.updatePassword(user, user.getPassword());
    }

    public UserRepresentation getUser(String userId) {
        return keyCloakConnection.findUserById(userId);
    }

    public void authorize() {
        keyCloakConnection.authorize();
    }
}
