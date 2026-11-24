package br.com.tatuwallet.repository;

import br.com.tatuwallet.entity.PrecoHistorico;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrecoHistoricoRepository extends JpaRepository<PrecoHistorico, Long> {
    Optional<PrecoHistorico> findFirstByCriptomoedaSimboloOrderByColetadoEmDesc(String simbolo);
}
