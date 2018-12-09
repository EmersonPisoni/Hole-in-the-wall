package br.com.crescer.holeinthewallapi.controller.mapper;

import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import br.com.crescer.holeinthewallapi.dto.partida.PartidaDto;
import org.springframework.stereotype.Component;

@Component
public class PartidaDtoMapper {

    public PartidaDto map(Partida partida) {
        return new PartidaDto(
                partida.getNivelAtual(),
                partida.getDificuldade(),
                partida.getVidas(),
                partida.getStickmanBase64());
    }

}
