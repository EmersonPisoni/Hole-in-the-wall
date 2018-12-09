package br.com.crescer.holeinthewallapi.service.usuario;

import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import br.com.crescer.holeinthewallapi.exception.UsuarioNotFoundException;
import br.com.crescer.holeinthewallapi.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarUsuarioPorIdService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarUsuarioPorId(long usuarioId) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioId);

        return optionalUsuario.orElseThrow(UsuarioNotFoundException::new);
    }

}
