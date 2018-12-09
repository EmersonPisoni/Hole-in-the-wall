package br.com.crescer.holeinthewallapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PartidaNotFoundException extends RuntimeException {

    public PartidaNotFoundException() {
        super("Partida não encontrada.");
    }

}
