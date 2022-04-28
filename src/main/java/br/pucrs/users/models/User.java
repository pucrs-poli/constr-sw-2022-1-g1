package br.pucrs.users.models;

import lombok.Data;

@Data
public class User {

    private String username;
    private String email;
    private String password;
}
