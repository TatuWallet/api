package br.com.tatuwallet.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table (name = "usuario")
public class User implements Serializable {

    @Id
    @Column(name = "id_usuario")
    private UUID id;                 // vem do JWT, sem @GeneratedValue

    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private Instant dataCadastro;

    @Column(name = "ativo", nullable = false)
    private Boolean active = true;

    @PrePersist
    void prePersist() {
        this.dataCadastro = Instant.now();
    }

    public UUID getId_usuario() {
        return id;
    }

    public void setId_usuario(UUID id_usuario) {
        this.id = id_usuario;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Instant getData_cadastro() {
        return dataCadastro;
    }

    public void setData_cadastro(Instant data_cadastro) {
        this.dataCadastro = data_cadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
