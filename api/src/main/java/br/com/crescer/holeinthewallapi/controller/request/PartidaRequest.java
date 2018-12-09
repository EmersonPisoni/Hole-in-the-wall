package br.com.crescer.holeinthewallapi.controller.request;

import br.com.crescer.holeinthewallapi.domain.partida.Dificuldade;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PartidaRequest {

    private Dificuldade dificuldade;

}
