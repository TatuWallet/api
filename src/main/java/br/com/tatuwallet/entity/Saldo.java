package br.com.tatuwallet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.Instant;

@Entity
@Table(name = "saldo")
@Getter @Setter
public class Saldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_carteira", nullable = false, updatable = false)
    private Carteira carteira;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cripto", nullable = false, updatable = false)
    private Criptomoeda criptomoeda;

    @Column(name = "quantidade_wei", nullable = false, precision = 78, scale = 0)
    private BigInteger quantidadeWei = BigInteger.ZERO;

    @Column(name = "atualizado_em", nullable = false)
    private Instant atualizadoEm;

    protected Saldo() { }                       // o JPA exige um construtor sem argumentos

    public Saldo(Carteira carteira, Criptomoeda criptomoeda) {
        this.carteira = carteira;
        this.criptomoeda = criptomoeda;
    }

    @PrePersist
    @PreUpdate
    void atualizarData() {
        this.atualizadoEm = Instant.now();
    }
}