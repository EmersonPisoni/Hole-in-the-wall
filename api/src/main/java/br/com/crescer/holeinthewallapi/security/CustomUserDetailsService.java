package br.com.crescer.holeinthewallapi.security;

import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import br.com.crescer.holeinthewallapi.exception.UsuarioNaoEncontradoException;
import br.com.crescer.holeinthewallapi.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario usuario = getUser(() -> usuarioRepository.buscarPorEmailOuApelido(username));
        return UserPrincipal.create(usuario);
    }

    public UserDetails loadUserById(Long id) {
        Usuario usuario = getUser(() -> usuarioRepository.findById(id));
        return UserPrincipal.create(usuario);
    }

    private Usuario getUser(Supplier<Optional<Usuario>> supplier) {
        return supplier.get().orElseThrow(UsuarioNaoEncontradoException::new);
    }

}
