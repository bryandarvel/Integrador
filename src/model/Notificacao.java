package model;

import java.sql.Timestamp;

public class Notificacao {
    private int idNotificacao;
    private String mensagem;
    private Timestamp dataNotificacao;
    private Usuario usuario; // Chave estrangeira para Usuario

    // Construtores
    public Notificacao() {
    }

    public Notificacao(int idNotificacao, String mensagem, Timestamp dataNotificacao, Usuario usuario) {
        this.idNotificacao = idNotificacao;
        this.mensagem = mensagem;
        this.dataNotificacao = dataNotificacao;
        this.usuario = usuario;
    }

    // Getters e Setters
    public int getIdNotificacao() {
        return idNotificacao;
    }

    public void setIdNotificacao(int idNotificacao) {
        this.idNotificacao = idNotificacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Timestamp getDataNotificacao() {
        return dataNotificacao;
    }

    public void setDataNotificacao(Timestamp dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Notificacao{" + "idNotificacao=" + idNotificacao + ", mensagem='" + mensagem + '\''
                + ", dataNotificacao=" + dataNotificacao + ", usuario=" + usuario + '}';
    }
}
