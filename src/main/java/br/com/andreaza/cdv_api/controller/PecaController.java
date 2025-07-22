package br.com.andreaza.cdv_api.controller;

import br.com.andreaza.cdv_api.entity.Peca;
import br.com.andreaza.cdv_api.service.PecaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pecas")
public class PecaController {

    private PecaService pecaService;

    public PecaController(PecaService pecaService) {
        this.pecaService = pecaService;
    }

    @GetMapping
    public ResponseEntity<List<Peca>> listarPecas() {
        return ResponseEntity.ok(pecaService.listar());
    }

    @PostMapping
    public ResponseEntity<Peca> criarPeca(@RequestBody Peca peca) {
        Peca novaPeca = pecaService.salvar(peca);

        return ResponseEntity.ok(novaPeca);
    }

    @PostMapping("upload/{id}")
    public ResponseEntity<String> uploadImagem(@PathVariable Long id, MultipartFile imagem) {
        try {
            String caminho = pecaService.salvarImagem(id, imagem);
            return ResponseEntity.ok("Imagem salva em: " + caminho);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Erro ao salvar imagem: " + e.getMessage());
        }
    }
}
