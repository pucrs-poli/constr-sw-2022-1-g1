package br.pucrs.users.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordUpdate {
    private final String type = "password";
    private final String temporary = "false";
    private String value;
}
