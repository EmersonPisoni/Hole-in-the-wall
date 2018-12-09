package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.controller.mapper.PartidaIniciarMapper;
import br.com.crescer.holeinthewallapi.domain.muro.Muro;
import br.com.crescer.holeinthewallapi.domain.partida.Dificuldade;
import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import br.com.crescer.holeinthewallapi.dto.partida.PartidaIniciarDto;
import br.com.crescer.holeinthewallapi.repository.partida.PartidaRepository;
import br.com.crescer.holeinthewallapi.service.muro.ConverterImagemResourceParaBase64Service;
import br.com.crescer.holeinthewallapi.service.muro.SortearMuroService;
import br.com.crescer.holeinthewallapi.service.usuario.BuscarUsuarioPorIdService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;

@ExtendWith(MockitoExtension.class)
class IniciarPartidaServiceTest {

    @InjectMocks
    private IniciarPartidaService iniciarPartidaService;

    @Mock
    private PartidaRepository partidaRepository;

    @Mock
    private BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Mock
    private SortearMuroService sortearMuroService;

    @Mock
    private PartidaIniciarMapper partidaIniciarMapper;

    @Mock
    private ConverterImagemResourceParaBase64Service converterImagemResourceParaBase64Service;

    private Usuario usuarioAutenticado;

    private String stickmanBase64Mock = "stickmanBase64Mock";

    private Partida partida;

    @BeforeEach
    void setup() {
        this.usuarioAutenticado = new Usuario("Ana", "Ana", "ana@mail.com", "abc");
        this.partida = new Partida();
    }

    @Test
    void iniciarPartida() throws IOException, URISyntaxException {
        PartidaIniciarDto partidaIniciarMock = new PartidaIniciarDto(3, stickmanBase64Mock);

        Mockito.when(buscarUsuarioPorIdService.buscarUsuarioPorId(Mockito.anyLong())).thenReturn(this.usuarioAutenticado);
        Mockito.when(sortearMuroService.sortear()).thenReturn(new Muro("imagemMuro", "[1]"));
        Mockito.when(partidaIniciarMapper.map(Mockito.any(Partida.class))).thenReturn(partidaIniciarMock);
        Mockito.when(converterImagemResourceParaBase64Service.converterParaBase64(Mockito.anyString())).thenReturn("");
        Mockito.when(partidaRepository.save(Mockito.any())).thenReturn(this.partida);
        PartidaIniciarDto partidaIniciada = iniciarPartidaService.iniciarPartida(1L, Dificuldade.FACIL);

        Assertions.assertEquals(partidaIniciarMock.getStickmanBase64(), partidaIniciada.getStickmanBase64());
        Assertions.assertEquals(partidaIniciarMock.getVidas(), partidaIniciada.getVidas());
    }

}
