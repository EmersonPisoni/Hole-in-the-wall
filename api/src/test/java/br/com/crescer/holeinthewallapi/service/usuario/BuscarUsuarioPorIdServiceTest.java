package br.com.crescer.holeinthewallapi.service.usuario;

import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import br.com.crescer.holeinthewallapi.repository.usuario.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BuscarUsuarioPorIdServiceTest {

    @InjectMocks
    private BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void buscarUsuarioPorId() {
        Usuario usuario = new Usuario();

        Mockito.when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Usuario usuarioEncontrado = buscarUsuarioPorIdService.buscarUsuarioPorId(1L);

        assertEquals(usuario, usuarioEncontrado);
    }
}