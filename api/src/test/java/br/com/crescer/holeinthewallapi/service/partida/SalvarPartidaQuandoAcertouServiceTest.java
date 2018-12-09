package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.domain.muro.Muro;
import br.com.crescer.holeinthewallapi.domain.partida.Dificuldade;
import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import br.com.crescer.holeinthewallapi.repository.partida.PartidaRepository;
import br.com.crescer.holeinthewallapi.service.muro.ConverterImagemResourceParaBase64Service;
import br.com.crescer.holeinthewallapi.service.muro.SortearMuroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SalvarPartidaQuandoAcertouServiceTest {

    @InjectMocks
    private SalvarPartidaQuandoAcertouService salvarPartidaService;

    @Mock
    private BuscarPartidaAtualService buscarPartidaAtualService;

    @Mock
    private PartidaRepository repository;

    @Mock
    private SortearMuroService sortearMuroService;

    @Mock
    private ConverterImagemResourceParaBase64Service converterImagemResourceParaBase64Service;

    private Usuario usuarioAutenticado;

    private LocalDateTime dataEHoraDeAgora;

    private String stickmanBase64Mock;

    @Test
    public void salvarPartida() throws IOException, URISyntaxException {
        usuarioAutenticado = new Usuario("Ana", "Ana", "ana@mail.com", "abc");
        usuarioAutenticado.setId(1L);
        dataEHoraDeAgora = LocalDateTime.now();
        int MOCK_PONTOS = 125;

        Partida partida = new Partida(Dificuldade.FACIL, 0L, dataEHoraDeAgora,
                usuarioAutenticado, 1, Dificuldade.FACIL.getVidas(), new Muro("", ""), dataEHoraDeAgora, stickmanBase64Mock);

        Mockito.when(repository.save(partida)).thenReturn(partida);
        Mockito.when(sortearMuroService.sortear()).thenReturn(new Muro("", ""));
        Mockito.when(converterImagemResourceParaBase64Service.converterParaBase64(Mockito.anyString())).thenReturn("");
        salvarPartidaService.salvarPartida(MOCK_PONTOS, partida);

        assertEquals(partida.getPontos(), 125);
    }

}
