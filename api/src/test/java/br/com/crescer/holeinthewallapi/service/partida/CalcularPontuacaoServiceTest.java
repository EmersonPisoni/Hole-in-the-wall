package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.domain.partida.Dificuldade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
class CalcularPontuacaoServiceTest {

    @InjectMocks
    private CalcularPontuacaoService calcular;

    @Test
    void testarCalculoNivel1DificuldadeFacil() {
        Integer resultado = calcular.calcularPontuacao(1, Dificuldade.FACIL);

        assertEquals(resultado, new Integer(100));
    }

    @Test
    void testarCalculoNivel1DificuldadeMedia() {
        Integer resultado = calcular.calcularPontuacao(1, Dificuldade.MEDIO);

        assertEquals(resultado, new Integer(125));
    }

    @Test
    void testarCalculoNivel1DificuldadeDificil() {
        Integer resultado = calcular.calcularPontuacao(1, Dificuldade.DIFICIL);

        assertEquals(resultado, new Integer(150));
    }

    @Test
    void testarCalculoNivel1DificuldadeHardcore() {
        Integer resultado = calcular.calcularPontuacao(1, Dificuldade.HARDCORE);

        assertEquals(resultado, new Integer(250));
    }

    @Test
    void testarCalculoNivel5DificuldadeFacil() {
        Integer resultado = calcular.calcularPontuacao(5, Dificuldade.FACIL);

        assertEquals(resultado, new Integer(140));
    }

    @Test
    void testarCalculoNivel5DificuldadeMedia() {
        Integer resultado = calcular.calcularPontuacao(5, Dificuldade.MEDIO);

        assertEquals(resultado, new Integer(165));
    }

    @Test
    void testarCalculoNivel5DificuldadeDificil() {
        Integer resultado = calcular.calcularPontuacao(5, Dificuldade.DIFICIL);

        assertEquals(resultado, new Integer(190));
    }

    @Test
    void testarCalculoNivel5DificuldadeHardcore() {
        Integer resultado = calcular.calcularPontuacao(5, Dificuldade.HARDCORE);

        assertEquals(resultado, new Integer(290));
    }

}
