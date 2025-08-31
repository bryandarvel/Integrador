package model;

public class Categoria {
    private int idCategoria;
    private String nomeCategoria;

    // Construtores
    public Categoria() {
    }

    public Categoria(int idCategoria, String nomeCategoria) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
    }

    // Getters e Setters
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    @Override
    public String toString() {
        return String.format("""
				Categoria:
				- ID: %d
				- Nome: %s
				""", idCategoria, nomeCategoria != null ? nomeCategoria : "Desconhecido");
    }

}
