package br.pucrs.users.controllers;

import br.pucrs.users.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class PinguimController {

    @RequestMapping(
            value = "/", method = RequestMethod.GET,
            consumes = MediaType.ALL_VALUE,
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<Void> PinguimTeam() {
        return new ResponseEntity<Void>(HttpStatus.I_AM_A_TEAPOT);
    }

}
