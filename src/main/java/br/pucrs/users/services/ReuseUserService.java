//package br.pucrs.users.services;
//
//import org.keycloak.representations.idm.CredentialRepresentation;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.keycloak.admin.client.resource.UsersResource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.keycloak.admin.client.Keycloak;
//import br.pucrs.users.config.Credentials;
//import br.pucrs.users.utils.Constants;
//import br.pucrs.users.models.User;
//import lombok.AllArgsConstructor;
//
//import java.util.Collections;
//
//@Service
//@AllArgsConstructor
//public class ReuseUserService {
//
//    @Autowired
//    private Keycloak keyCloak;
//    private Constants constants;
//
//    public void createUser(User user) {
//        CredentialRepresentation credential = Credentials
//                .createPasswordCredentials(user.getPassword());
//
//        UserRepresentation userRepresentation = new UserRepresentation();
//        userRepresentation.setUsername(user.getUsername());
//        userRepresentation.setEmail(user.getEmail());
//        userRepresentation.setCredentials(Collections.singletonList(credential));
//        userRepresentation.setEnabled(true);
//
//        UsersResource instance = getInstance();
//        instance.create(userRepresentation);
//    }
//
//
//    public UsersResource getInstance(){
//        return keyCloak.realm(constants.realm).users();
//    }
//}
