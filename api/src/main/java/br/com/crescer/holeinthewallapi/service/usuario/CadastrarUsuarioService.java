package br.com.crescer.holeinthewallapi.service.usuario;

import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import br.com.crescer.holeinthewallapi.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VerificaUsuarioPorApelidoService verificaUsuarioPorApelidoService;

    @Autowired
    private VerificaUsuarioPorEmailService verificaUsuarioPorEmailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrar(Usuario usuario) {

        verificaUsuarioPorApelidoService.verificarPorApelido(usuario.getApelido());
        verificaUsuarioPorEmailService.verificarPorEmail(usuario.getEmail());

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

}
