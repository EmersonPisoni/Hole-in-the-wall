package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import br.com.crescer.holeinthewallapi.exception.PartidaNotFoundException;
import br.com.crescer.holeinthewallapi.repository.partida.PartidaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BuscarPartidaAtualServiceTest {

    @InjectMocks
    private BuscarPartidaAtualService buscarPartidaAtualService;

    @Mock
    PartidaRepository repository;

    @Test
    void buscarComPartida() {
        Partida partida = new Partida();

        Mockito.when(repository.partidaAtualUsuario(1L)).thenReturn(Optional.of(partida));
        Partida partidaAtual = buscarPartidaAtualService.buscar(1L);

        assertEquals(partida, partidaAtual);
    }

    @Test
    void buscarSemPartida() {
        Mockito.when(repository.partidaAtualUsuario(1L)).thenReturn(Optional.empty());

        assertThrows(PartidaNotFoundException.class, () ->
                buscarPartidaAtualService.buscar(1L)
        );
    }

}
