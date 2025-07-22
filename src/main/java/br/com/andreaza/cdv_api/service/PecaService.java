package br.com.andreaza.cdv_api.service;

import br.com.andreaza.cdv_api.entity.Peca;
import br.com.andreaza.cdv_api.repository.PecaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PecaService {

    private PecaRepository pecaRepository;

    public PecaService(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
    }

    public Peca salvar(Peca peca) {
        return pecaRepository.save(peca);
    }

    public List<Peca> listar() {
        return pecaRepository.findAll();
    }
}
