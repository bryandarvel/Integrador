package model;

import java.sql.Timestamp;

public class Despesa {
    private int idDespesa;
    private double valor;
    private String descricao;
    private Timestamp dataDespesa;
    private Usuario usuario; // Chave estrangeira para Usuario
    private Categoria categoria; // Chave estrangeira para Categoria

    // Construtores
    public Despesa() {
    }

    public Despesa(int idDespesa, double valor, String descricao, Timestamp dataDespesa, Usuario usuario,
                   Categoria categoria) {
        this.idDespesa = idDespesa;
        this.valor = valor;
        this.descricao = descricao;
        this.dataDespesa = dataDespesa;
        this.usuario = usuario;
        this.categoria = categoria;
    }

    // Getters e Setters
    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getDataDespesa() {
        return dataDespesa;
    }

    public void setDataDespesa(Timestamp dataDespesa) {
        this.dataDespesa = dataDespesa;
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
	        Despesa:
	        - ID: %d
	        - Descrição: %s
	        - Valor: R$ %.2f
	        - Data: %s
	        - Categoria: %s
	        - Usuário: %s
	        """,
                idDespesa,
                descricao,
                valor,
                dataDespesa.toString(),
                categoria != null ? categoria.getNomeCategoria() : "Sem Categoria",
                usuario != null ? usuario.getNome() : "Desconhecido"
        );
    }
}
