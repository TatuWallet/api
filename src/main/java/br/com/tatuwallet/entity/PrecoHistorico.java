package br.com.tatuwallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "preco_historico")
@Getter @Setter
@NoArgsConstructor
public class PrecoHistorico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "criptomoeda_id")
    private Criptomoeda criptomoeda;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal precoBrl;

    @Column(nullable = false)
    private Instant coletadoEm;

    @Column(nullable = false, unique = true)
    private String fonte; // "coinbase", "coingecko", etc. (ajuste ou remova)
}
