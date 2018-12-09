package br.com.crescer.holeinthewallapi.dto.partida;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ComparaFotosDto {

    private String frame;

    @JsonProperty("template_vectors")
    private float[][] templateVectors;

}
