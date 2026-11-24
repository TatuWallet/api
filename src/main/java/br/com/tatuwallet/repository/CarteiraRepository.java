package br.com.tatuwallet.repository;

import br.com.tatuwallet.entity.Carteira;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    @Override
    @QueryHints(@QueryHint(
            name = "jakarta.persistence.query.timeout",
            value = "5000"
    ))
    Optional<Carteira> findById(Long id);
}