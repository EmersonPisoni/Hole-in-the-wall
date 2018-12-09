package br.com.crescer.holeinthewallapi.dto.partida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PartidaIniciarDto {

    private int vidas;

    private String stickmanBase64;

}
