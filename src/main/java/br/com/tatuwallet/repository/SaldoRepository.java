package br.com.tatuwallet.repository;

import br.com.tatuwallet.entity.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaldoRepository extends JpaRepository<Saldo, Long> {
}
