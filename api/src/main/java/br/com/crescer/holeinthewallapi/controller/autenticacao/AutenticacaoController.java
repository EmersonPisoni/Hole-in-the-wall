package br.com.crescer.holeinthewallapi.controller.autenticacao;

import br.com.crescer.holeinthewallapi.controller.request.LoginRequest;
import br.com.crescer.holeinthewallapi.controller.response.LoginResponse;
import br.com.crescer.holeinthewallapi.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return new LoginResponse(authenticationService
                .authenticate(loginRequest.getEmailOuApelido(), loginRequest.getSenha()));
    }

}
