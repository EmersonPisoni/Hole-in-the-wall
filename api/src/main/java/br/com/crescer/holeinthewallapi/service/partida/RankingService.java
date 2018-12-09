package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.dto.partida.PartidaNomePontosDto;
import br.com.crescer.holeinthewallapi.repository.partida.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

    @Autowired
    private PartidaRepository repository;

    public Page<PartidaNomePontosDto> ranking(Pageable pageable) {
        return repository.ranking(pageable);
    }

}

