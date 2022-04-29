package br.pucrs.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import br.pucrs.users.utils.Constants;
import br.pucrs.users.utils.HttpModel;
import br.pucrs.users.models.Token;
import org.springframework.http.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class KeyCloakService {

    @Autowired
    private Constants constants;

    @Autowired
    private RestTemplate restTemplate;

    public Token login(LinkedMultiValueMap<String, String> principal) {
        String url = constants.serverUrl + "/realms/" + constants.realm + "/protocol/openid-connect/token";
        return restTemplate.postForObject(url, HttpModel.entity(principal, MediaType.APPLICATION_FORM_URLENCODED), Token.class);
    }

}
