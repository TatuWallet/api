package br.com.tatuwallet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table (name = "usuario")
public class User implements Serializable {

    @Id
    private UUID id_usuario;
    private String username;
    private String email;
    private Timestamp data_cadastro;
    private Boolean active;

    public UUID getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(UUID id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Timestamp getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Timestamp data_cadastro) {
        this.data_cadastro = data_cadastro;
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
