package br.pucrs.users.controllers;

import br.pucrs.users.models.User;
import br.pucrs.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/users/logout", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity logout(@RequestHeader("refresh_token") String refreshToken) throws Exception {
        userService.logout(refreshToken);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/{realm}/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }
}
