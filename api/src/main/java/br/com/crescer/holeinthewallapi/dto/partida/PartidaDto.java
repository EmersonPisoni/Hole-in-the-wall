package br.com.crescer.holeinthewallapi.dto.partida;

import br.com.crescer.holeinthewallapi.domain.partida.Dificuldade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PartidaDto {

    private int nivelAtual;

    private Dificuldade dificuldade;

    private int vidas;

    private String stickmanBase64;

}
