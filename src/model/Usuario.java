package model;

import java.sql.Timestamp;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String senha;
    private String email;
    private Timestamp dataCriacao;

    // Construtores
    public Usuario() {
    }

    public Usuario(int idUsuario, String nome, String senha, String email, Timestamp dataCriacao) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataCriacao = dataCriacao;
    }

    // Getters e Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return String.format("""
				Usuário:
				- ID: %d
				- Nome: %s
				- Email: %s
				- Data de Criação: %s
				""", idUsuario, nome, email, dataCriacao != null ? dataCriacao.toString() : "Não Disponível");
    }

}
