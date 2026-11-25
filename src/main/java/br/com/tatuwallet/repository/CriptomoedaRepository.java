package br.com.tatuwallet.repository;

import br.com.tatuwallet.entity.Criptomoeda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriptomoedaRepository extends JpaRepository<Criptomoeda, Long> {
}
