package br.pucrs.users.services;

import br.pucrs.users.models.DTO.PasswordDTO;
import br.pucrs.users.models.PasswordUpdate;
import br.pucrs.users.models.UserWID;
import br.pucrs.users.models.User;
import br.pucrs.users.utils.Constants;
import br.pucrs.users.utils.HttpModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private Constants constants;

    @Autowired
    private RestTemplate restTemplate;

    public void createUser(User user, String Authorization) {
        String url = constants.serverUrl + "/admin/realms/" + constants.realm + "/users";
        restTemplate.postForEntity(url, HttpModel.entity(user, MediaType.APPLICATION_JSON, Authorization), String.class);
    }

    public UserWID[] listUser(String Authorization) {
        String url = constants.serverUrl + "/admin/realms/" + constants.realm + "/users";

        return restTemplate.exchange(
            url,
            HttpMethod.GET,
            HttpModel.entity(null, MediaType.APPLICATION_JSON, Authorization),
            UserWID[].class
        ).getBody();
    }

    public User getUser(String id, String Authorization) {
        String url = constants.serverUrl + "/admin/realms/" + constants.realm + "/users/" + id;

        return restTemplate.exchange(
            url,
            HttpMethod.GET,
            HttpModel.entity(null, MediaType.APPLICATION_JSON, Authorization),
            User.class
        ).getBody();
    }

    public void updateUser(String id, User user, String Authorization) {
        String url = constants.serverUrl + "/admin/realms/" + constants.realm + "/users/" + id;

        restTemplate.exchange(
            url,
            HttpMethod.PUT,
            HttpModel.entity(user, MediaType.APPLICATION_JSON, Authorization),
            String.class
        );

    }

    public void updatePassword(String id, PasswordDTO password, String Authorization) {
        String url = constants.serverUrl + "/admin/realms/" + constants.realm + "/users/" + id + "/reset-password";
        PasswordUpdate passwordUpdate = new PasswordUpdate(password.getPassword());

        restTemplate.exchange(
                url,
                HttpMethod.PUT,
                HttpModel.entity(passwordUpdate, MediaType.APPLICATION_JSON, Authorization),
                String.class
        );
    }


}
