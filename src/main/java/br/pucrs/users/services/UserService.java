package br.pucrs.users.services;

import br.pucrs.users.conections.KeyCloakConnection;
import br.pucrs.users.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private KeyCloakConnection keyCloakConnection;

    public void createUser(User user) {
        keyCloakConnection.createUser(user);
    }
}
