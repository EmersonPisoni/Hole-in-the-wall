package br.com.crescer.holeinthewallapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApelidoJaExistenteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroResponse handleApelidoJaExistenteException(ApelidoJaExistenteException exception) {

        return new ErroResponse("Apelido já cadastrado!", 1520);

    }

    @ExceptionHandler(EmailJaExistenteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroResponse handleApelidoJaExistenteException(EmailJaExistenteException exception) {

        return new ErroResponse("Email já cadastrado!", 1521);

    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErroResponse handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException exception) {

        return new ErroResponse("Usuário ou senha inválidos", 1522);

    }
}
