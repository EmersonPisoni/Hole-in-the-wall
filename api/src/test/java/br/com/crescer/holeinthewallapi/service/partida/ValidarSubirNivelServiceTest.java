package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.domain.partida.Dificuldade;
import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import br.com.crescer.holeinthewallapi.dto.partida.PartidaContinuarDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ValidarSubirNivelServiceTest {

    @InjectMocks
    private ValidarSubirNivelService validarSubirNivelService;

    @Mock
    private ValidarPontuacaoService validarPontuacaoService;

    @Mock
    private CalcularPontuacaoService calcularPontuacaoService;

    @Mock
    private SalvarPartidaQuandoAcertouService salvarPartidaQuandoAcertouService;

    @Mock
    private SalvarPartidaQuandoErrouService salvarPartidaQuandoErrouService;

    @Mock
    private BuscarPartidaAtualService buscarPartidaAtualService;

    @Test
    void subirNivelPassouPeloMuro() throws IOException, URISyntaxException {
        long idUsuarioLogado = 1L;
        Float[] partesDoCorpo = {};
        boolean passouPeloMuro = true;
        Partida partida = new Partida();
        partida.setDificuldade(Dificuldade.FACIL);
        partida.setNivelAtual(1);
        int pontos = 10;

        Mockito.when(buscarPartidaAtualService.buscar(idUsuarioLogado)).thenReturn(partida);
        Mockito.when(validarPontuacaoService.validarPontuacao(partesDoCorpo, partida.getDificuldade())).thenReturn(passouPeloMuro);
        Mockito.when(calcularPontuacaoService.calcularPontuacao(partida.getNivelAtual(), partida.getDificuldade())).thenReturn(pontos);
        Mockito.when(salvarPartidaQuandoAcertouService.salvarPartida(pontos, partida)).thenReturn(partida);
        PartidaContinuarDto partidaContinuarDto = validarSubirNivelService.subirNivel(partesDoCorpo, idUsuarioLogado);

        assertTrue(partidaContinuarDto.isPassou());
    }

    @Test
    void subirNivelNaoPassouPeloMuro() throws IOException, URISyntaxException {
        long idUsuarioLogado = 1L;
        Float[] partesDoCorpo = {};
        boolean passouPeloMuro = false;
        Partida partida = new Partida();
        partida.setDificuldade(Dificuldade.FACIL);
        partida.setNivelAtual(1);

        Mockito.when(buscarPartidaAtualService.buscar(idUsuarioLogado)).thenReturn(partida);
        Mockito.when(validarPontuacaoService.validarPontuacao(partesDoCorpo, partida.getDificuldade())).thenReturn(passouPeloMuro);
        Mockito.when(salvarPartidaQuandoErrouService.salvarPartida(partida)).thenReturn(partida);
        PartidaContinuarDto partidaContinuarDto = validarSubirNivelService.subirNivel(partesDoCorpo, idUsuarioLogado);

        assertFalse(partidaContinuarDto.isPassou());
    }

}