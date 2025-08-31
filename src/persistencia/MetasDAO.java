package persistencia;

import model.Metas;
import model.Categoria;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetasDAO {

    // Método para adicionar uma meta
    public boolean adicionar(Metas meta) {
        Connection conexao = null;
        String sql = "INSERT INTO Metas (limite, data_criacao, fk_Usuario_id_usuario, fk_Categoria_id_categoria) "
                + "VALUES (?, ?, ?, ?)";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, meta.getLimite());
            stmt.setTimestamp(2, meta.getDataCriacao());
            stmt.setInt(3, meta.getUsuario().getIdUsuario());
            stmt.setInt(4, meta.getCategoria().getIdCategoria());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return false;
    }

    public List<Metas> buscarTodas() {
        List<Metas> metas = new ArrayList<>();
        String sql = "SELECT m.id_metas, m.limite, m.fk_Usuario_id_usuario, m.fk_Categoria_id_categoria, "
                + "u.id_usuario, u.nome, u.email, u.data_criacao, " + "c.id_categoria, c.nome_categoria " + "FROM Metas m "
                + "JOIN Usuario u ON m.fk_Usuario_id_usuario = u.id_usuario "
                + "JOIN Categoria c ON m.fk_Categoria_id_categoria = c.id_categoria";

        Connection conexao = null;
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Metas meta = new Metas();
                meta.setIdMetas(rs.getInt("id_metas"));
                meta.setLimite(rs.getDouble("limite"));

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

                // Atribuindo os objetos à meta
                meta.setUsuario(usuario);
                meta.setCategoria(categoria);

                metas.add(meta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return metas;
    }

    // Método para buscar metas por categoria
    public List<Metas> buscarPorCategoria(int idCategoria) {
        Connection conexao = null;
        List<Metas> metas = new ArrayList<>();
        String sql = "SELECT * FROM Metas WHERE fk_Categoria_id_categoria = ?";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Metas meta = mapearMeta(rs);
                metas.add(meta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return metas;
    }

    // Método para buscar uma meta por ID
    public Metas buscarPorId(int idMetas) {
        Connection conexao = null;
        String sql = "SELECT * FROM Metas WHERE id_metas = ?";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idMetas);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearMeta(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return null;
    }

    // Método para atualizar uma meta
    public boolean atualizar(Metas meta) {
        Connection conexao = null;
        String sql = "UPDATE Metas SET limite = ?, data_criacao = ?, fk_Usuario_id_usuario = ?, "
                + "fk_Categoria_id_categoria = ? WHERE id_metas = ?";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, meta.getLimite());
            stmt.setTimestamp(2, meta.getDataCriacao());
            stmt.setInt(3, meta.getUsuario().getIdUsuario());
            stmt.setInt(4, meta.getCategoria().getIdCategoria());
            stmt.setInt(5, meta.getIdMetas());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return false;
    }

    // Método para excluir uma meta
    public boolean excluir(int idMetas) {
        Connection conexao = null;
        String sql = "DELETE FROM Metas WHERE id_metas = ?";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idMetas);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return false;
    }

    // Método para mapear resultado do ResultSet para objeto Metas
    private Metas mapearMeta(ResultSet rs) throws SQLException {
        Metas meta = new Metas();
        meta.setIdMetas(rs.getInt("id_metas"));
        meta.setLimite(rs.getDouble("limite"));
        meta.setDataCriacao(rs.getTimestamp("data_criacao"));

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("fk_Usuario_id_usuario"));
        meta.setUsuario(usuario);

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(rs.getInt("fk_Categoria_id_categoria"));
        meta.setCategoria(categoria);

        return meta;
    }
}
