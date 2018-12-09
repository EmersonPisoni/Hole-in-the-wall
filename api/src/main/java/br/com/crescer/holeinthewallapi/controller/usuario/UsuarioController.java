package br.com.crescer.holeinthewallapi.controller.usuario;

import br.com.crescer.holeinthewallapi.controller.mapper.CadastrarUsuarioRequestMapper;
import br.com.crescer.holeinthewallapi.controller.mapper.UsuarioDtoMapper;
import br.com.crescer.holeinthewallapi.controller.request.CadastrarUsuarioRequest;
import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import br.com.crescer.holeinthewallapi.dto.usuario.UsuarioDto;
import br.com.crescer.holeinthewallapi.service.usuario.CadastrarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private CadastrarUsuarioService cadastrarUsuarioService;

    @Autowired
    private UsuarioDtoMapper usuarioDtoMapper;

    @Autowired
    private CadastrarUsuarioRequestMapper cadastrarUsuarioRequestMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto cadastrar(@RequestBody CadastrarUsuarioRequest request) {
        Usuario usuario = cadastrarUsuarioRequestMapper.map(request);

        cadastrarUsuarioService.cadastrar(usuario);

        return usuarioDtoMapper.map(usuario);
    }

}
