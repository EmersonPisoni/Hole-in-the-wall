package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import br.com.crescer.holeinthewallapi.exception.PartidaNotFoundException;
import br.com.crescer.holeinthewallapi.repository.partida.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarPartidaAtualService {

    @Autowired
    PartidaRepository repository;

    public Partida buscar(Long idUsuarioLogado) {
        Optional<Partida> optionalPartidaAtual = repository.partidaAtualUsuario(idUsuarioLogado);

        return optionalPartidaAtual.orElseThrow(PartidaNotFoundException::new);
    }

}
