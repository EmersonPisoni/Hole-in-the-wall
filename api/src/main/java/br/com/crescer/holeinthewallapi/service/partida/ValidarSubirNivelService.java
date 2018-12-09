package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import br.com.crescer.holeinthewallapi.dto.partida.PartidaContinuarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class ValidarSubirNivelService {

    @Autowired
    private ValidarPontuacaoService validarPontuacaoService;

    @Autowired
    private CalcularPontuacaoService calcularPontuacaoService;

    @Autowired
    private SalvarPartidaQuandoAcertouService salvarPartidaQuandoAcertouService;

    @Autowired
    private SalvarPartidaQuandoErrouService salvarPartidaQuandoErrouService;

    @Autowired
    private BuscarPartidaAtualService buscarPartidaAtualService;

    public PartidaContinuarDto subirNivel(Float[] partesDoCorpo, Long idUsuarioLogado) throws IOException, URISyntaxException {
        Partida partidaAtual = buscarPartidaAtualService.buscar(idUsuarioLogado);
        Boolean acertou = validarPontuacaoService.validarPontuacao(partesDoCorpo, partidaAtual.getDificuldade());

        if (acertou) {
            int pontuacaoNivel = calcularPontuacaoService.calcularPontuacao(partidaAtual.getNivelAtual(), partidaAtual.getDificuldade());

            partidaAtual = salvarPartidaQuandoAcertouService.salvarPartida(pontuacaoNivel, partidaAtual);

            return new PartidaContinuarDto(true, partidaAtual.getPontos(), partidaAtual.getVidas(), partidaAtual.getStickmanBase64());
        } else {
            partidaAtual = salvarPartidaQuandoErrouService.salvarPartida(partidaAtual);

            return new PartidaContinuarDto(false, partidaAtual.getPontos(), partidaAtual.getVidas(), partidaAtual.getStickmanBase64());
        }

    }

}
