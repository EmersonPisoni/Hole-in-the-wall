package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ValidarTempoRequisicaoServiceTest {

    @InjectMocks
    private ValidarTempoRequisicaoService validarTempoRequisicaoService;

    @Test
    void requisicaoValida() {
        Partida partida = new Partida();

        LocalDateTime antes = LocalDateTime.now().minusSeconds(ValidarTempoRequisicaoService.SEGUNDOS_ENTRE_REQUISICOES);

        partida.setDataHoraAlteracao(antes);

        assertTrue(validarTempoRequisicaoService.validarTempoRequisicao(partida));
    }

    @Test
    void requisicaoInvalida() {
        Partida partida = new Partida();

        LocalDateTime antes = LocalDateTime.now().minusSeconds(ValidarTempoRequisicaoService.SEGUNDOS_ENTRE_REQUISICOES + 1);

        partida.setDataHoraAlteracao(antes);

        assertFalse(validarTempoRequisicaoService.validarTempoRequisicao(partida));
    }

}
