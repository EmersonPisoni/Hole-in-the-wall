package br.com.crescer.holeinthewallapi.domain.partida;

import br.com.crescer.holeinthewallapi.domain.Dominio;
import br.com.crescer.holeinthewallapi.domain.muro.Muro;
import br.com.crescer.holeinthewallapi.domain.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@SequenceGenerator(name = "gerador_id", sequenceName = "seq_partida", initialValue = 1, allocationSize = 1)
public class Partida extends Dominio {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Dificuldade dificuldade;

    @NotNull
    private long pontos;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataHoraPartida;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    @NotNull
    private Usuario usuario;

    @NotNull
    private int nivelAtual;

    @NotNull
    private int vidas;

    @ManyToOne
    @JoinColumn(name = "muro_id")
    private Muro muro;

    @NotNull
    private LocalDateTime dataHoraAlteracao;

    @Transient
    private String stickmanBase64;

}
