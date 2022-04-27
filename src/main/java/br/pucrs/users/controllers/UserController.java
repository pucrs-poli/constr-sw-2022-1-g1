package br.pucrs.users.controllers;

import br.pucrs.users.models.User;
import br.pucrs.users.services.UserService;
import lombok.Getter;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/{realm}/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{realm}/users/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{realm}/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{realm}/users/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRepresentation> getUser(@PathVariable String userId) {
        UserRepresentation user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

//    @PostMapping
//    public ResponseEntity authorize() {
//        userService.authorize();
//    }
}
