package br.com.crescer.holeinthewallapi.service.muro;

import br.com.crescer.holeinthewallapi.domain.muro.Muro;
import br.com.crescer.holeinthewallapi.exception.NenhumMuroEncontradoException;
import br.com.crescer.holeinthewallapi.repository.muro.MuroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class SortearMuroServiceTest {

    @InjectMocks
    private SortearMuroService sortearMuroService;

    @Mock
    private MuroRepository muroRepository;

    @Mock
    Random random;

    @Test
    void sortearListaVazia() {
        List<Muro> muros = new ArrayList<>();

        Mockito.when(muroRepository.findAll()).thenReturn(muros);

        assertThrows(NenhumMuroEncontradoException.class, () ->
                sortearMuroService.sortear()
        );
    }

    @Test
    void sortearListaComMuros() {
        List<Muro> muros = new ArrayList<>();
        Muro muro = new Muro();

        muros.add(muro);
        muros.add(new Muro());

        Mockito.when(muroRepository.findAll()).thenReturn(muros);
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);
        sortearMuroService.sortear();

        muros = new ArrayList<>();
        muros.add(muro);
        muros.add(new Muro());

        Mockito.when(muroRepository.findAll()).thenReturn(muros);
        sortearMuroService.sortear();

        muros = new ArrayList<>();
        muros.add(muro);
        muros.add(new Muro());

        Mockito.when(muroRepository.findAll()).thenReturn(muros);
        Muro muroSorteado = sortearMuroService.sortear();

        assertEquals(muro, muroSorteado);
    }

}