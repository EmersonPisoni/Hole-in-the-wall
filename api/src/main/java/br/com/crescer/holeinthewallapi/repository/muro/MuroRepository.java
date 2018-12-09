package br.com.crescer.holeinthewallapi.repository.muro;

import br.com.crescer.holeinthewallapi.domain.muro.Muro;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MuroRepository extends CrudRepository<Muro, Long> {

    List<Muro> findAll();

}
