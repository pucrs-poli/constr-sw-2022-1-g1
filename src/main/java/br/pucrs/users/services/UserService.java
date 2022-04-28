package br.pucrs.users.services;

import br.pucrs.users.models.User;
import br.pucrs.users.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private Constants constants;

    @Autowired
    private RestTemplate restTemplate;

    public void createUser(User user, String Authorization) throws Exception {
        String url = constants.serverUrl + "/admin/realms/" + constants.realm + "/users";
        restTemplate.postForEntity(url, entity(user, MediaType.APPLICATION_JSON, Authorization), String.class);
    }

    public static HttpEntity entity(Object object, MediaType midiaType, String Authorization) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(Authorization.split(" ")[1]);
        headers.setContentType(midiaType);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return new HttpEntity (object, headers);
    }


}
