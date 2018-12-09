package br.com.crescer.holeinthewallapi.repository.partida;

import br.com.crescer.holeinthewallapi.domain.partida.Partida;
import br.com.crescer.holeinthewallapi.dto.partida.PartidaNomePontosDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PartidaRepository extends CrudRepository<Partida, Long> {

    @Query(value = "SELECT * FROM (SELECT * FROM partida WHERE usuario_id = ?1 ORDER BY data_hora_partida DESC)WHERE  ROWNUM = 1", nativeQuery = true)
    Optional<Partida> partidaAtualUsuario(Long id);

    @Query("SELECT new br.com.crescer.holeinthewallapi.dto.partida.PartidaNomePontosDto" +
            "(p.usuario.apelido, p.pontos) FROM Partida p")
    Page<PartidaNomePontosDto> ranking(Pageable pageable);

}
