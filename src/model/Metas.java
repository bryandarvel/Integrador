package model;

import java.sql.Timestamp;

public class Metas {
    private int idMetas;
    private double limite;
    private Timestamp dataCriacao;
    private Usuario usuario; // Chave estrangeira para Usuario
    private Categoria categoria; // Chave estrangeira para Categoria

    // Construtores
    public Metas() {
    }

    public Metas(int idMetas, double limite, Timestamp dataCriacao, Usuario usuario, Categoria categoria) {
        this.idMetas = idMetas;
        this.limite = limite;
        this.dataCriacao = dataCriacao;
        this.usuario = usuario;
        this.categoria = categoria;
    }

    // Getters e Setters
    public int getIdMetas() {
        return idMetas;
    }

    public void setIdMetas(int idMetas) {
        this.idMetas = idMetas;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format("""
				Meta:
				- ID: %d
				- Limite: R$ %.2f
				- Categoria: %s
				- Usuário: %s
				""", idMetas, limite, categoria != null ? categoria.getNomeCategoria() : "Sem Categoria",
                usuario != null ? usuario.getNome() : "Desconhecido");
    }

}
