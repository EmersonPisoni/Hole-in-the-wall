package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.domain.muro.Muro;
import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import br.com.crescer.holeinthewallapi.repository.partida.PartidaRepository;
import br.com.crescer.holeinthewallapi.service.muro.ConverterImagemResourceParaBase64Service;
import br.com.crescer.holeinthewallapi.service.muro.SortearMuroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Service
public class SalvarPartidaQuandoAcertouService {

    @Autowired
    private PartidaRepository repository;

    @Autowired
    private BuscarPartidaAtualService buscarPartidaAtualService;

    @Autowired
    private SortearMuroService sortearMuroService;

    @Autowired
    private ConverterImagemResourceParaBase64Service converterImagemResourceParaBase64Service;

    public Partida salvarPartida(int pontos, Partida partidaAtual) throws IOException, URISyntaxException {
        partidaAtual.setNivelAtual(partidaAtual.getNivelAtual() + 1);

        partidaAtual.setPontos(partidaAtual.getPontos() + pontos);

        Muro muroSorteado = sortearMuroService.sortear();

        String stickmanBase64 = converterImagemResourceParaBase64Service.converterParaBase64(muroSorteado.getImagemMuro());

        partidaAtual.setMuro(muroSorteado);

        partidaAtual.setStickmanBase64(stickmanBase64);

        partidaAtual.setDataHoraAlteracao(LocalDateTime.now());

        partidaAtual.setMuro(muroSorteado);

        return repository.save(partidaAtual);
    }

}
