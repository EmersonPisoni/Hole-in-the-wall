package br.com.crescer.holeinthewallapi.repository.usuario;

import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.apelido like ?1")
    Optional<Usuario> buscarPorApelido(String apelido);

    @Query("SELECT u FROM Usuario u WHERE u.apelido like ?1 OR u.email like ?1")
    Optional<Usuario> buscarPorEmailOuApelido(String emailOuApelido);

    @Query("SELECT u FROM Usuario u WHERE u.email like ?1")
    Optional<Usuario> buscarPorEmail(String email);

}
