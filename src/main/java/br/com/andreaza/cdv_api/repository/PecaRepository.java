package br.com.andreaza.cdv_api.repository;

import br.com.andreaza.cdv_api.entity.Peca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PecaRepository extends JpaRepository<Peca, Long> {
}
