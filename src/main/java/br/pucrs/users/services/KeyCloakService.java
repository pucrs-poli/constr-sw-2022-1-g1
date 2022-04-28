package br.pucrs.users.services;

import br.pucrs.users.config.KeyCloakConfiguration;
import br.pucrs.users.models.Principal;
import br.pucrs.users.utils.Constants;
import br.pucrs.users.utils.HttpModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import br.pucrs.users.models.Token;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.util.Collections;
import java.util.Objects;

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
        return restTemplate.postForObject(url, entity(principal, MediaType.APPLICATION_FORM_URLENCODED), Token.class);
    }

    public static HttpEntity<LinkedMultiValueMap<String, String>> entity(LinkedMultiValueMap<String, String> formData, MediaType midiaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(midiaType);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return new HttpEntity<LinkedMultiValueMap<String, String>> (formData, headers);
    }

}
