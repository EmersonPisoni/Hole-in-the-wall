package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.controller.mapper.PartidaDtoMapper;
import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import br.com.crescer.holeinthewallapi.dto.partida.PartidaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DtoPartidaAtualService {

    @Autowired
    private BuscarPartidaAtualService buscarPartidaAtualService;

    @Autowired
    private PartidaDtoMapper mapper;

    public PartidaDto dtoPartidaAtual(Long idUsuarioLogado) {

        Partida partida = buscarPartidaAtualService.buscar(idUsuarioLogado);

        return mapper.map(partida);
    }

}
