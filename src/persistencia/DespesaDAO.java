package persistencia;

import model.Despesa;
import model.Categoria;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO {

    // Método para adicionar uma despesa
    public boolean adicionar(Despesa despesa) {
        Connection conexao = null;
        String sql = "INSERT INTO Despesas (valor, descricao_despesas, data_despesa, fk_Usuario_id_usuario, fk_Categoria_id_categoria) "
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, despesa.getValor());
            stmt.setString(2, despesa.getDescricao());
            stmt.setTimestamp(3, despesa.getDataDespesa());
            stmt.setInt(4, despesa.getUsuario().getIdUsuario());
            stmt.setInt(5, despesa.getCategoria().getIdCategoria());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return false;
    }

    public List<Despesa> buscarTodas() {
        List<Despesa> despesas = new ArrayList<>();
        String sql = "SELECT d.id_despesas, d.valor, d.descricao_despesas, d.data_despesa, d.fk_Usuario_id_usuario, d.fk_Categoria_id_categoria, "
                + "u.id_usuario, u.nome, u.email, u.data_criacao, " + "c.id_categoria, c.nome_categoria "
                + "FROM Despesas d " + "JOIN Usuario u ON d.fk_Usuario_id_usuario = u.id_usuario "
                + "JOIN Categoria c ON d.fk_Categoria_id_categoria = c.id_categoria";

        Connection conexao = null;
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Despesa despesa = new Despesa();
                despesa.setIdDespesa(rs.getInt("id_despesas"));
                despesa.setValor(rs.getDouble("valor"));
                despesa.setDescricao(rs.getString("descricao_despesas"));
                despesa.setDataDespesa(rs.getTimestamp("data_despesa"));

                // Preenchendo o usuário
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setDataCriacao(rs.getTimestamp("data_criacao"));

                // Preenchendo a categoria
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNomeCategoria(rs.getString("nome_categoria"));

                // Atribuindo os objetos à despesa
                despesa.setUsuario(usuario);
                despesa.setCategoria(categoria);

                despesas.add(despesa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return despesas;
    }

    // Método para buscar despesas por categoria
    public List<Despesa> buscarPorCategoria(int idCategoria) {
        Connection conexao = null;
        List<Despesa> despesas = new ArrayList<>();
        String sql = "SELECT * FROM Despesas WHERE fk_Categoria_id_categoria = ?";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Despesa despesa = mapearDespesa(rs);
                despesas.add(despesa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return despesas;
    }

    // Método para buscar uma despesa por ID
    public Despesa buscarPorId(int idDespesa) {
        Connection conexao = null;
        String sql = "SELECT * FROM Despesas WHERE id_despesas = ?";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDespesa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearDespesa(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return null;
    }

    // Método para atualizar uma despesa
    public boolean atualizar(Despesa despesa) {
        Connection conexao = null;
        String sql = "UPDATE Despesas SET valor = ?, descricao_despesas = ?, data_despesa = ?, "
                + "fk_Usuario_id_usuario = ?, fk_Categoria_id_categoria = ? WHERE id_despesas = ?";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, despesa.getValor());
            stmt.setString(2, despesa.getDescricao());
            stmt.setTimestamp(3, despesa.getDataDespesa());
            stmt.setInt(4, despesa.getUsuario().getIdUsuario());
            stmt.setInt(5, despesa.getCategoria().getIdCategoria());
            stmt.setInt(6, despesa.getIdDespesa());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return false;
    }

    // Método para excluir uma despesa
    public boolean excluir(int idDespesa) {
        Connection conexao = null;
        String sql = "DELETE FROM Despesas WHERE id_despesas = ?";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idDespesa);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return false;
    }

    // Método para mapear resultado do ResultSet para objeto Despesa
    private Despesa mapearDespesa(ResultSet rs) throws SQLException {
        Despesa despesa = new Despesa();
        despesa.setIdDespesa(rs.getInt("id_despesas"));
        despesa.setValor(rs.getDouble("valor"));
        despesa.setDescricao(rs.getString("descricao_despesas"));
        despesa.setDataDespesa(rs.getTimestamp("data_despesa"));

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("fk_Usuario_id_usuario"));
        despesa.setUsuario(usuario);

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(rs.getInt("fk_Categoria_id_categoria"));
        despesa.setCategoria(categoria);

        return despesa;
    }
}
