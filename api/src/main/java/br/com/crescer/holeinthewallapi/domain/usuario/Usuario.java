package br.com.crescer.holeinthewallapi.domain.usuario;

import br.com.crescer.holeinthewallapi.domain.Dominio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@SequenceGenerator(name = "gerador_id", sequenceName = "seq_usuario", initialValue = 1, allocationSize = 1)
public class Usuario extends Dominio {

    @NotNull
    private String nome;

    @NotNull
    private String apelido;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String senha;

}
