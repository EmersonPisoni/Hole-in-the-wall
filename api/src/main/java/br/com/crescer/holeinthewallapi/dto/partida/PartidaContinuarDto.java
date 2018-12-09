package br.com.crescer.holeinthewallapi.dto.partida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PartidaContinuarDto {

    @NotNull
    private boolean passou;

    @NotNull
    private long pontos;

    @NotNull
    private int vidas;

    @NotNull
    private String stickmanBase64;

}
