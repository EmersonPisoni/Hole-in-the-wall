package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ValidarTempoRequisicaoService {

    public static final int SEGUNDOS_ENTRE_REQUISICOES = 20;

    public boolean validarTempoRequisicao(Partida partidaAtual) {
        return ChronoUnit.SECONDS.between(partidaAtual.getDataHoraAlteracao(), LocalDateTime.now()) <= SEGUNDOS_ENTRE_REQUISICOES;
    }

}
