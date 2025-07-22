package br.com.andreaza.cdv_api.service;

import br.com.andreaza.cdv_api.entity.Peca;
import br.com.andreaza.cdv_api.repository.PecaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class PecaService {

    private static final String DIRETORIO_IMAGENS = new File("imagens").getAbsolutePath();

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

    public Peca buscarPorId(Long id) {
        return pecaRepository.findById(id).orElse(null);
    }

    public String salvarImagem(Long id, MultipartFile imagem) throws IOException {
        Peca peca = buscarPorId(id);

        if(peca == null) {
            throw new IOException("Peça não encontrada");
        }

        File pasta = new File(DIRETORIO_IMAGENS);
        if(!pasta.exists()) {
            pasta.mkdirs();
        }

        String caminho = DIRETORIO_IMAGENS + File.separator + imagem.getOriginalFilename();
        imagem.transferTo(new File(caminho));

        peca.setImagemUrl(caminho);

        pecaRepository.save(peca);

        return caminho;
    }
}
