package br.com.crescer.holeinthewallapi.service.muro;

import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import br.com.crescer.holeinthewallapi.dto.partida.ComparaFotosDto;
import br.com.crescer.holeinthewallapi.dto.partida.PartidaContinuarDto;
import br.com.crescer.holeinthewallapi.service.partida.BuscarPartidaAtualService;
import br.com.crescer.holeinthewallapi.service.partida.SalvarPartidaQuandoErrouService;
import br.com.crescer.holeinthewallapi.service.partida.ValidarSubirNivelService;
import br.com.crescer.holeinthewallapi.service.partida.ValidarTempoRequisicaoService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class CompararFotosService {

    private static final String ENDPOINT_POSE_COMPARING_API_COMPARACAO_FOTOS = "http://pose-comparer.eastus.cloudapp.azure.com/api/compare";

    @Autowired
    private ValidarSubirNivelService validarSubirNivelService;

    @Autowired
    private ValidarTempoRequisicaoService validarTempoRequisicaoService;

    @Autowired
    private BuscarPartidaAtualService buscarPartidaAtualService;

    @Autowired
    private SalvarPartidaQuandoErrouService salvarPartidaQuandoErrouService;

    @Autowired
    private RestTemplate restTemplate;

    public PartidaContinuarDto compararFotos(String base64Foto, Long idUsuarioLogado) throws IOException, URISyntaxException {
        Partida partidaAtual = buscarPartidaAtualService.buscar(idUsuarioLogado);

        if (!validarTempoRequisicaoService.validarTempoRequisicao(partidaAtual)) {
            partidaAtual = salvarPartidaQuandoErrouService.salvarPartida(partidaAtual);
            return new PartidaContinuarDto(false, 0, partidaAtual.getVidas(), partidaAtual.getStickmanBase64());
        }

        String posicoesGabarito = partidaAtual.getMuro().getPosicoesGabarito();

        Gson gson = new Gson();
        float[][] vetorPosicoes = gson.fromJson(posicoesGabarito, float[][].class);

        ComparaFotosDto comparaFotosDto = new ComparaFotosDto(base64Foto, vetorPosicoes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ComparaFotosDto> request = new HttpEntity<>(comparaFotosDto, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(ENDPOINT_POSE_COMPARING_API_COMPARACAO_FOTOS, request, String.class);

        String jsonResponse = response.getBody();

        Float[] partesDoCorpo = gson.fromJson(jsonResponse, Float[].class);

        return validarSubirNivelService.subirNivel(partesDoCorpo, idUsuarioLogado);
    }

}
