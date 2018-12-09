package br.com.crescer.holeinthewallapi.service.usuario;

import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import br.com.crescer.holeinthewallapi.exception.ApelidoJaExistenteException;
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
public class VerificaUsuarioPorApelidoServiceTest {

    @InjectMocks
    private VerificaUsuarioPorApelidoService verificaUsuarioPorApelidoService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void buscarUsuarioPorApelidoDeveRetornarUmOptionalVazio() {
        Usuario usuario = new Usuario("João", "João", "joao@mail.com", "123");

        Mockito.when(usuarioRepository.buscarPorApelido(usuario.getApelido())).thenReturn(Optional.empty());

        verificaUsuarioPorApelidoService.verificarPorApelido(usuario.getApelido());

        Mockito.verify(usuarioRepository, Mockito.times(1)).buscarPorApelido(usuario.getApelido());
    }

    @Test
    public void buscarUsuarioPorApelidoDeveLancarException() {
        Usuario usuario = new Usuario("João", "João", "joao@mail.com", "123");

        Mockito.when(usuarioRepository.buscarPorApelido(usuario.getApelido())).thenReturn(Optional.of(usuario));

        Assertions.assertThrows(ApelidoJaExistenteException.class, () ->
                verificaUsuarioPorApelidoService.verificarPorApelido(usuario.getApelido())
        );
    }

}
