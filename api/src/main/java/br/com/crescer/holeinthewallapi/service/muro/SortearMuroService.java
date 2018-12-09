package br.com.crescer.holeinthewallapi.service.muro;

import br.com.crescer.holeinthewallapi.domain.muro.Muro;
import br.com.crescer.holeinthewallapi.exception.NenhumMuroEncontradoException;
import br.com.crescer.holeinthewallapi.repository.muro.MuroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SortearMuroService {

    @Autowired
    private MuroRepository muroRepository;

    @Autowired
    private Random random;

    private List<Muro> murosQueForamSorteados = new ArrayList<>();

    public Muro sortear() {
        List<Muro> muros = muroRepository.findAll();

        if (muros.isEmpty()) {
            throw new NenhumMuroEncontradoException();
        }

        if(murosQueForamSorteados.size() == muros.size()) {
            murosQueForamSorteados = new ArrayList<>();
        }

        muros.removeAll(murosQueForamSorteados);

        Muro muroSorteado = muros.get(random.nextInt(muros.size()));

        murosQueForamSorteados.add(muroSorteado);

        return muroSorteado;
    }

}
