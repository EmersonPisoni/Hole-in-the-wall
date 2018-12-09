package br.com.crescer.holeinthewallapi.domain.partida;

import lombok.Getter;

@Getter
public enum Dificuldade {

    FACIL(4, 0, 0.6f),
    MEDIO(3, 25, 0.7f),
    DIFICIL(2, 50, 0.8f),
    HARDCORE(1, 150, 0.9f);

    private int vidas;

    private int bonusDePontuacao;

    private float pontuacaoMinimaPraPassar;

    Dificuldade(int vidas, int bonusDePontuacao, float pontuacaoMinimaPraPassar) {
        this.vidas = vidas;
        this.bonusDePontuacao = bonusDePontuacao;
        this.pontuacaoMinimaPraPassar = pontuacaoMinimaPraPassar;
    }

}
