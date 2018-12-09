package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.dto.partida.PartidaNomePontosDto;
import br.com.crescer.holeinthewallapi.repository.partida.PartidaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RankingServiceTest {

    @InjectMocks
    private RankingService rankingService;

    @Mock
    private PartidaRepository repository;

    @Test
    void ranking() {
        Page<PartidaNomePontosDto> page = new PageImpl<>(new ArrayList<>());
        PageRequest pageable = PageRequest.of(1, 1);

        Mockito.when(repository.ranking(pageable)).thenReturn(page);
        Page pageRanking = rankingService.ranking(pageable);

        assertEquals(page, pageRanking);
    }

}