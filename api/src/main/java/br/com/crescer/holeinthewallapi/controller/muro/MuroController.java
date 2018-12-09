package br.com.crescer.holeinthewallapi.controller.muro;


import br.com.crescer.holeinthewallapi.controller.request.MuroRequest;
import br.com.crescer.holeinthewallapi.dto.partida.PartidaContinuarDto;
import br.com.crescer.holeinthewallapi.security.UserPrincipal;
import br.com.crescer.holeinthewallapi.service.muro.CompararFotosService;
import br.com.crescer.holeinthewallapi.service.partida.BuscarPartidaAtualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/muros")
public class MuroController {

    @Autowired
    private CompararFotosService compararFotosService;

    @Autowired
    private BuscarPartidaAtualService buscarPartidaAtualService;

    @PostMapping
    public PartidaContinuarDto compararImagemDoMuro(@RequestBody MuroRequest muroRequest,
                                                    @AuthenticationPrincipal UserPrincipal userPrincipal) throws IOException, URISyntaxException {
        return compararFotosService.compararFotos(
                muroRequest.getImg(), userPrincipal.getId());
    }

}
