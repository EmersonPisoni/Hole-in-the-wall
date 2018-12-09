package br.com.crescer.holeinthewallapi.service.partida;

import br.com.crescer.holeinthewallapi.domain.partida.Dificuldade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ValidarPontuacaoServiceTest {

    @InjectMocks
    ValidarPontuacaoService validar;

    @Test
    void testaValidarPontuacaoQuePassouFacil() {
        Float[] resultadoMock = {0.8f, 0.7f, 0.6f};

        Boolean resultado = validar.validarPontuacao(resultadoMock, Dificuldade.FACIL);

        assertTrue(resultado);
    }

    @Test
    void testaValidarPontuacaoQueNaoPassouFacil() {
        Float[] resultadoMock = {-0.9f, 0.2f, 0.3f};

        Boolean resultado = validar.validarPontuacao(resultadoMock, Dificuldade.FACIL);

        assertFalse(resultado);
    }

    @Test
    void testaValidarPontuacaoQuePassouMedio() {
        Float[] resultadoMock = {0.9f, 0.7f, 0.8f};

        Boolean resultado = validar.validarPontuacao(resultadoMock, Dificuldade.MEDIO);

        assertTrue(resultado);
    }

    @Test
    void testaValidarPontuacaoQueNaoPassouMedio() {
        Float[] resultadoMock = {-0.9f, 0.2f, 0.3f};

        Boolean resultado = validar.validarPontuacao(resultadoMock, Dificuldade.MEDIO);

        assertFalse(resultado);
    }

    @Test
    void testaValidarPontuacaoQuePassouDificil() {
        Float[] resultadoMock = {1.0f, 0.8f, 0.8f};

        Boolean resultado = validar.validarPontuacao(resultadoMock, Dificuldade.DIFICIL);

        assertTrue(resultado);
    }

    @Test
    void testaValidarPontuacaoQueNaoPassouDificil() {
        Float[] resultadoMock = {-0.9f, 0.2f, 0.3f};

        Boolean resultado = validar.validarPontuacao(resultadoMock, Dificuldade.DIFICIL);

        assertFalse(resultado);
    }

    @Test
    void testaValidarPontuacaoQuePassouHardcore() {
        Float[] resultadoMock = {1.0f, 0.9f, 0.9f};

        Boolean resultado = validar.validarPontuacao(resultadoMock, Dificuldade.HARDCORE);

        assertTrue(resultado);
    }

    @Test
    void testaValidarPontuacaoQueNaoPassouHardcore() {
        Float[] resultadoMock = {-0.9f, 0.2f, 0.3f};

        Boolean resultado = validar.validarPontuacao(resultadoMock, Dificuldade.HARDCORE);

        assertFalse(resultado);
    }

    @Test
    void testaValidarPessoaNaoEstaNaFoto() {
        Float[] resultadoMock = {0.6f, 0.6f, 0.6f, null, null, null, null, null};

        Boolean resultado = validar.validarPontuacao(resultadoMock, Dificuldade.FACIL);

        assertFalse(resultado);
    }

}
