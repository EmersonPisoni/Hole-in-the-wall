package br.com.crescer.holeinthewallapi.service.usuario;

import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import br.com.crescer.holeinthewallapi.exception.EmailJaExistenteException;
import br.com.crescer.holeinthewallapi.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificaUsuarioPorEmailService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void verificarPorEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.buscarPorEmail(email);

        if (usuario.isPresent()) {
            throw new EmailJaExistenteException();
        }
    }

}
