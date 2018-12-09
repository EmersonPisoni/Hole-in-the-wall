package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.controller.mapper.PartidaIniciarMapper;
import br.com.crescer.holeinthewallapi.domain.muro.Muro;
import br.com.crescer.holeinthewallapi.domain.partida.Dificuldade;
import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import br.com.crescer.holeinthewallapi.dto.partida.PartidaIniciarDto;
import br.com.crescer.holeinthewallapi.repository.partida.PartidaRepository;
import br.com.crescer.holeinthewallapi.service.muro.ConverterImagemResourceParaBase64Service;
import br.com.crescer.holeinthewallapi.service.muro.SortearMuroService;
import br.com.crescer.holeinthewallapi.service.usuario.BuscarUsuarioPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Service
public class IniciarPartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Autowired
    private SortearMuroService sortearMuroService;

    @Autowired
    private PartidaIniciarMapper partidaIniciarMapper;

    @Autowired
    private ConverterImagemResourceParaBase64Service converterImagemResourceParaBase64Service;

    public PartidaIniciarDto iniciarPartida(long usuarioId, Dificuldade dificuldade) throws IOException, URISyntaxException {
        Usuario usuario = buscarUsuarioPorIdService.buscarUsuarioPorId(usuarioId);
        Muro muroSorteado = sortearMuroService.sortear();
        Partida partida = new Partida(dificuldade, 0, LocalDateTime.now(), usuario, 1,
                dificuldade.getVidas(), muroSorteado, LocalDateTime.now(), converterImagemResourceParaBase64Service.converterParaBase64(muroSorteado.getImagemMuro()));

        partidaRepository.save(partida);

        return partidaIniciarMapper.map(partida);
    }

}
