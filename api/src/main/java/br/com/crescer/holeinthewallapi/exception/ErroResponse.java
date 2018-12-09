package br.com.crescer.holeinthewallapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErroResponse {

    private String mensagem;

    private int codigo;

}
