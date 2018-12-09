package br.com.crescer.holeinthewallapi.service.usuario;

import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import br.com.crescer.holeinthewallapi.repository.usuario.UsuarioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarUsuarioServiceTest {

    @InjectMocks
    private CadastrarUsuarioService service;

    @Mock
    private UsuarioRepository repository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    VerificaUsuarioPorApelidoService verificaUsuarioPorApelidoService;

    @Mock
    VerificaUsuarioPorEmailService verificaUsuarioPorEmailService;

    @Captor
    ArgumentCaptor<Usuario> usuarioArgumentCaptor;

    @Test
    public void cadastrarUsuarioValido() {
        Usuario novoUsuario = new Usuario("Ana", "Ana", "ana@mail.com", "abc");
        Usuario usuarioSave = new Usuario("Ana", "Ana", "Ana@mail.com", "123");

        Mockito.when(passwordEncoder.encode("abc")).thenReturn("123");
        Mockito.when(repository.save(novoUsuario)).thenReturn(usuarioSave);

        Usuario usuarioService = service.cadastrar(novoUsuario);

        Mockito.verify(verificaUsuarioPorApelidoService, times(1))
                .verificarPorApelido(novoUsuario.getApelido());
        Mockito.verify(verificaUsuarioPorEmailService, times(1))
                .verificarPorEmail(novoUsuario.getEmail());
        Mockito.verify(repository, times(1)).save(usuarioArgumentCaptor.capture());
        Usuario usuarioRetornado = usuarioArgumentCaptor.getValue();

        assertEquals("123", usuarioRetornado.getSenha());
        assertEquals(usuarioService, usuarioSave);
    }

}
