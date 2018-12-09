package br.com.crescer.holeinthewallapi.domain.muro;

import br.com.crescer.holeinthewallapi.domain.Dominio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@SequenceGenerator(name = "gerador_id", sequenceName = "seq_muro", initialValue = 1, allocationSize = 1)
public class Muro extends Dominio {

    @NotNull
    private String imagemMuro;

    @NotNull
    private String posicoesGabarito;

}
