package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.BDConfigs;

public class Conexao {

    public static Connection abrirConexao() {
        Connection conexao = null;
        try {
            String url = "jdbc:mysql://" + BDConfigs.IP + ":" + BDConfigs.PORTA + "/" + BDConfigs.NOME_BD;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, BDConfigs.USUARIO, BDConfigs.SENHA);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conexao;
    }

    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
