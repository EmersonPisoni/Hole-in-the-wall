package br.com.crescer.holeinthewallapi.controller.mapper;

import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import br.com.crescer.holeinthewallapi.dto.partida.PartidaIniciarDto;
import org.springframework.stereotype.Component;

@Component
public class PartidaIniciarMapper {

    public PartidaIniciarDto map(Partida partida) {
        return new PartidaIniciarDto(
                partida.getVidas(),
                partida.getStickmanBase64());
    }

}
