package br.pucrs.users.services;

import br.pucrs.users.conections.KeyCloakConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private KeyCloakConnection keyCloakConnection;

    public void logout(String refreshToken) throws Exception {
        keyCloakConnection.logout(refreshToken);
    }
}
