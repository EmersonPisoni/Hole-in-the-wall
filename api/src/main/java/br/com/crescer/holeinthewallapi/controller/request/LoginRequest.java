package br.com.crescer.holeinthewallapi.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {

    @NotEmpty
    private String emailOuApelido;

    @NotEmpty
    private String senha;

}
