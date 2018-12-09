package br.com.crescer.holeinthewallapi.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarUsuarioRequest {

    @NotNull
    private String nome;

    @NotNull
    private String apelido;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String senha;

}
