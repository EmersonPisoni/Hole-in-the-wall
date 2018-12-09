package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.controller.mapper.PartidaDtoMapper;
import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import br.com.crescer.holeinthewallapi.dto.partida.PartidaDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DtoPartidaAtualServiceTest {

    @InjectMocks
    private DtoPartidaAtualService dtoPartidaAtualService;

    @Mock
    private BuscarPartidaAtualService buscarPartidaAtualService;

    @Mock
    private PartidaDtoMapper mapper;

    @Test
    void dtoPartidaAtual() {
        Partida partida = new Partida();
        PartidaDto partidaDto = new PartidaDto();

        Mockito.when(buscarPartidaAtualService.buscar(1L)).thenReturn(partida);
        Mockito.when(mapper.map(partida)).thenReturn(partidaDto);
        PartidaDto partidaDtoMapeada = dtoPartidaAtualService.dtoPartidaAtual(1L);

        assertEquals(partidaDto, partidaDtoMapeada);
    }

}