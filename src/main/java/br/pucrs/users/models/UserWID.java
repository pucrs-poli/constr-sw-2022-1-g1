package br.pucrs.users.models;

import lombok.Data;

@Data
public class UserWID {
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private boolean enabled;
}
