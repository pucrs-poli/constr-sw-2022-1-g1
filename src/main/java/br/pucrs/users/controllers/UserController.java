package br.pucrs.users.controllers;

import br.pucrs.users.models.User;
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
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        try {
            userService.createUser(user, Authorization);
        } catch (HttpClientErrorException.Unauthorized unauthorized) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        } catch (HttpClientErrorException.Conflict conflict) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}
