package br.com.crescer.holeinthewallapi.service.usuario;

import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import br.com.crescer.holeinthewallapi.exception.EmailJaExistenteException;
import br.com.crescer.holeinthewallapi.repository.usuario.UsuarioRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class VerificaUsuarioPorEmailServiceTest {

    @InjectMocks
    private VerificaUsuarioPorEmailService verificaUsuarioPorEmailService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void buscarUsuarioPorEmailDeveRetornarUmOptionalVazio() {
        Usuario usuario = new Usuario("João", "João", "joao@mail.com", "123");

        Mockito.when(usuarioRepository.buscarPorEmail(usuario.getEmail())).thenReturn(Optional.empty());

        verificaUsuarioPorEmailService.verificarPorEmail(usuario.getEmail());

        Mockito.verify(usuarioRepository, Mockito.times(1)).buscarPorEmail(usuario.getEmail());
    }

    @Test
    public void buscarUsuarioPorEmailDeveLancarException() {
        Usuario usuario = new Usuario("João", "João", "joao@mail.com", "123");

        Mockito.when(usuarioRepository.buscarPorEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));

        Assertions.assertThrows(EmailJaExistenteException.class, () ->
                verificaUsuarioPorEmailService.verificarPorEmail(usuario.getEmail())
        );
    }

}
