package br.pucrs.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import br.pucrs.users.services.KeyCloakService;
import org.springframework.http.MediaType;
import br.pucrs.users.models.Token;


@RestController
public class KeyCloakController {

    @Autowired
    private KeyCloakService keyCloakService;

    @RequestMapping(
        value = "/login", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<Token> authenticate(@RequestParam LinkedMultiValueMap<String, String> principalCredentials) {
        return ResponseEntity.ok(keyCloakService.login(principalCredentials));
    }

}
