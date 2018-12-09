package br.com.crescer.holeinthewallapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NenhumMuroEncontradoException extends RuntimeException {

    public NenhumMuroEncontradoException() {
        super("Nenhum Muro Foi encontrado.");
    }

}
