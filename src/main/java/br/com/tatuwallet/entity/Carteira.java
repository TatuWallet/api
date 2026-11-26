package br.com.tatuwallet.entity;

import br.com.tatuwallet.Enum.TipoCarteira;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "carteira")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long versao;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "saldo_fiat", nullable = false, precision = 20, scale = 2)
    private BigDecimal saldoFiat = new BigDecimal("1000.00");

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false, updatable = false)
    private User usuario;

    @Column(name = "endereco", nullable = false, length = 128, updatable = false)
    private String endereco;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private Instant dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoCarteira tipo = TipoCarteira.SELF_CUSTODY;

    @Column(name = "verified", nullable = false)
    private boolean verified = false;

    @Column(name = "rede", nullable = false, length = 20)
    private String rede;

    @PrePersist
    void prePersist() {
        this.dataCriacao = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSaldoFiat() {
        return saldoFiat;
    }

    public void setSaldoFiat(BigDecimal saldoFiat) {
        this.saldoFiat = saldoFiat;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public TipoCarteira getTipo() {
        return tipo;
    }

    public Long getVersao() {
        return versao;
    }

    public void setTipo(TipoCarteira tipo) {
        this.tipo = tipo;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getRede() {
        return rede;
    }

    public void setRede(String rede) {
        this.rede = rede;
    }
}