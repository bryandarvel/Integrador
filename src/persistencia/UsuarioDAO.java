package persistencia;

import model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Método para adicionar usuário
    public boolean adicionar(Usuario usuario) {
        Connection conexao = null;
        String sql = "INSERT INTO Usuario (nome, senha, email, data_criacao) VALUES (?, ?, ?, ?)";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getEmail());
            stmt.setTimestamp(4, usuario.getDataCriacao());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return false;
    }

    // Método para buscar todos os usuários
    public List<Usuario> buscarTodos() {
        Connection conexao = null;
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmail(rs.getString("email"));
                usuario.setDataCriacao(rs.getTimestamp("data_criacao"));

                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return usuarios;
    }

    // Método para buscar usuário por ID
    public Usuario buscarPorId(int id) {
        Connection conexao = null;
        String sql = "SELECT * FROM Usuario WHERE id_usuario = ?";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmail(rs.getString("email"));
                usuario.setDataCriacao(rs.getTimestamp("data_criacao"));
                return usuario;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return null;
    }

    // Método para buscar usuário por email e senha (Login)
    public Usuario buscarPorEmailESenha(String email, String senha) {
        Connection conexao = null;
        String sql = "SELECT * FROM Usuario WHERE email = ? AND senha = ?";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmail(rs.getString("email"));
                usuario.setDataCriacao(rs.getTimestamp("data_criacao"));
                return usuario;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return null;
    }

    // Método para atualizar usuário
    public boolean atualizar(Usuario usuario) {
        Connection conexao = null;
        String sql = "UPDATE Usuario SET nome = ?, senha = ?, email = ? WHERE id_usuario = ?";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getEmail());
            stmt.setInt(4, usuario.getIdUsuario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return false;
    }

    // Método para excluir usuário
    public boolean excluir(int id) {
        Connection conexao = null;
        String sql = "DELETE FROM Usuario WHERE id_usuario = ?";
        try {
            conexao = Conexao.abrirConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao(conexao);
        }
        return false;
    }
}
