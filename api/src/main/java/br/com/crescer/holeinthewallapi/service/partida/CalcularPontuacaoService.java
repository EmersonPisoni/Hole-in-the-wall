package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.domain.partida.Dificuldade;
import org.springframework.stereotype.Service;

@Service
public class CalcularPontuacaoService {

    private static final int PONTUACAO_GANHA_BASE = 100;
    private static final int MULTIPLICADOR_DE_PONTUACAO_POR_NIVEL = 10;

    public int calcularPontuacao(int nivel, Dificuldade dificuldade) {
        int pontuacaoBonusDoNivel = ((nivel - 1) * MULTIPLICADOR_DE_PONTUACAO_POR_NIVEL);
        int bonusPorDificuldade = dificuldade.getBonusDePontuacao();

        return PONTUACAO_GANHA_BASE + pontuacaoBonusDoNivel + bonusPorDificuldade;
    }

}
