package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.domain.partida.Dificuldade;
import org.springframework.stereotype.Service;

@Service
public class ValidarPontuacaoService {

    private static final int MAX_NULOS = 5;
    private int contadorNulo = 0;

    public Boolean validarPontuacao(Float[] pontuacoes, Dificuldade dificuldade) {
        for (Float pontuacao : pontuacoes) {
            if (pontuacao == null || pontuacao.isNaN()) {
                contadorNulo++;
                if (contadorNulo >= MAX_NULOS) {
                    return false;
                }

            } else if (pontuacao < dificuldade.getPontuacaoMinimaPraPassar()) {
                return false;
            }

        }

        return true;
    }

}
