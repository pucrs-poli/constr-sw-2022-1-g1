package br.pucrs.users.controllers;

import br.pucrs.users.models.DTO.PasswordDTO;
import br.pucrs.users.models.User;
import br.pucrs.users.models.UserWID;
import br.pucrs.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(
            value = "/users", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Void> createUser(@RequestBody User user, @RequestHeader String Authorization) {
        if (Authorization.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            userService.createUser(user, Authorization);
        } catch (HttpClientErrorException httpError) {
            return new ResponseEntity<>(httpError.getStatusCode());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/users", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<UserWID[]> listUser(@RequestHeader String Authorization) {
        if (Authorization.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            return ResponseEntity.ok(userService.listUser(Authorization));
        } catch (HttpClientErrorException httpError) {
            return new ResponseEntity<>(httpError.getStatusCode());
        }
    }

    @RequestMapping(
            value = "/users/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<User> getUser(@PathVariable("id") String id, @RequestHeader String Authorization) {
        if (Authorization.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            return ResponseEntity.ok(userService.getUser(id, Authorization));

        } catch (HttpClientErrorException httpError) {
            return new ResponseEntity<>(httpError.getStatusCode());
        }
    }

    @RequestMapping(
            value = "/users/{id}", method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Void> updateUser(
            @PathVariable("id") String id,
            @RequestBody User user,
            @RequestHeader String Authorization
    ) {
        if (Authorization.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            userService.updateUser(id, user, Authorization);
        } catch (HttpClientErrorException httpError) {
            return new ResponseEntity<>(httpError.getStatusCode());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/users/{id}", method = RequestMethod.PATCH,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<User> updatePassword(
            @PathVariable("id") String id,
            @RequestBody PasswordDTO password,
            @RequestHeader String Authorization
    ) {
        if (Authorization.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try {
            userService.updatePassword(id, password, Authorization);

        } catch (HttpClientErrorException httpError) {
            return new ResponseEntity<>(httpError.getStatusCode());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(
            value = "/users/{id}", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<String> deleteUser(
            @PathVariable("id") String id,
            @RequestHeader String Authorization
    ) {
        Boolean response = userService.deleteUser(id, Authorization);
        return ResponseEntity.ok(response ? "Deleted" : "Not_Deleted");
    }
}
