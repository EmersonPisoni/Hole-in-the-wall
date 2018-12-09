package br.com.crescer.holeinthewallapi.controller.mapper;

import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import br.com.crescer.holeinthewallapi.dto.usuario.UsuarioDto;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoMapper {

    public UsuarioDto map(Usuario usuario) {
        return new UsuarioDto(
                usuario.getNome(),
                usuario.getApelido(),
                usuario.getEmail());
    }

}
