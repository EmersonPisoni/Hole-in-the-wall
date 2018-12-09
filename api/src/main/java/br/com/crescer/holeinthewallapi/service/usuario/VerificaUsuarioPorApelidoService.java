package br.com.crescer.holeinthewallapi.service.usuario;

import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import br.com.crescer.holeinthewallapi.exception.ApelidoJaExistenteException;
import br.com.crescer.holeinthewallapi.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificaUsuarioPorApelidoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void verificarPorApelido(String apelido) {
        Optional<Usuario> usuario = usuarioRepository.buscarPorApelido(apelido);

        if (usuario.isPresent()) {
            throw new ApelidoJaExistenteException();
        }
    }

}
