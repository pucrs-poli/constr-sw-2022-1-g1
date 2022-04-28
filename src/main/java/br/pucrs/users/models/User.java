package br.pucrs.users.models;

import lombok.Data;

@Data
public class User {
    private String username;
//    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private boolean enabled;
}