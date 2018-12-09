package br.com.crescer.holeinthewallapi.controller.partida;

import br.com.crescer.holeinthewallapi.controller.request.PartidaRequest;
import br.com.crescer.holeinthewallapi.dto.partida.PartidaIniciarDto;
import br.com.crescer.holeinthewallapi.dto.partida.PartidaNomePontosDto;
import br.com.crescer.holeinthewallapi.security.UserPrincipal;
import br.com.crescer.holeinthewallapi.service.partida.IniciarPartidaService;
import br.com.crescer.holeinthewallapi.service.partida.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/partidas")
public class PartidaController {

    @Autowired
    private IniciarPartidaService iniciarPartidaService;

    @Autowired
    private RankingService rankingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PartidaIniciarDto iniciarPartida(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody PartidaRequest partidaRequest)
            throws IOException, URISyntaxException {
        return iniciarPartidaService.iniciarPartida(userPrincipal.getId(), partidaRequest.getDificuldade());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<PartidaNomePontosDto> ranking(@PageableDefault(sort = "pontos", direction = Sort.Direction.DESC) Pageable pageable) {
        return rankingService.ranking(pageable);
    }

}
