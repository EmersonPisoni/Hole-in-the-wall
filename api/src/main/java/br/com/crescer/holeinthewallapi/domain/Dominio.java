package br.com.crescer.holeinthewallapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@Setter
@Getter
public class Dominio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gerador_id")
    private Long id;

}
