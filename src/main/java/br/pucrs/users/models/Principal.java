package br.pucrs.users.models;

import lombok.Data;

@Data
public class Principal {
    private String client_id;
    private String client_secret;
    private String username;
    private String password;
    private String grantType;
}
