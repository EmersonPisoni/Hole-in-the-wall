package br.com.crescer.holeinthewallapi.controller.mapper;

import br.com.crescer.holeinthewallapi.controller.request.CadastrarUsuarioRequest;
import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class CadastrarUsuarioRequestMapper {

    public Usuario map(CadastrarUsuarioRequest request) {
        return new Usuario(
                request.getNome(),
                request.getApelido(),
                request.getEmail(),
                request.getSenha());
    }

}
