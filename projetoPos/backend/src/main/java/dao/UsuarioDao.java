package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import util.ConexaoBanco;

public class UsuarioDao {

    public void cadastrar(Usuario user) throws SQLException {

        Connection con = new ConexaoBanco().getConnection();

        String query;
        query = "INSERT INTO usuarios (cpf,nome,email,senha,nascimento,sexo) VALUES (?, ?, ?, ?, ?, ?);";

        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, user.getCpf());
        st.setString(2, user.getNome());
        st.setString(3, user.getEmail());
        st.setString(4, user.getSenha());
        st.setString(5, user.getNascimento());
        st.setString(6, user.getSexo());

        st.execute();
        st.close();
        con.close();
    }

    public List<Usuario> pesquisar(int pessoa) throws SQLException {

        Connection con = new ConexaoBanco().getConnection();

        List<Usuario> lista = new ArrayList<>();
        String query = "SELECT * FROM usuarios WHERE cpf = ?";

        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, pessoa);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Usuario user = new Usuario();

            user.setCpf(rs.getInt("cpf"));
            user.setNome(rs.getString("nome"));
            user.setEmail(rs.getString("email"));
            user.setSenha(rs.getString("senha"));
            user.setNascimento(rs.getString("nascimento"));
            user.setSexo(rs.getString("sexo"));

            lista.add(user);
        }

        return lista;
    }

    public void deletar(int pessoa) throws SQLException {
        String query = "DELETE FROM usuarios WHERE cpf = ?";

        Connection con = new ConexaoBanco().getConnection();
        PreparedStatement st = null;

        st = con.prepareStatement(query);
        st.setInt(1, pessoa);
        st.execute();
        st.close();
        con.close();
    }

    public  void alterar(Usuario user) throws SQLException {
        Connection con = new ConexaoBanco().getConnection();
        String query = "UPDATE usuarios set nome=?, email=?, senha=?, nascimento= ?, sexo =? where cpf=?";

        //Usuario user = new Usuario();
        PreparedStatement st = con.prepareStatement(query);       
        st.setString(1, user.getNome());   
        st.setString(2, user.getEmail());
        st.setString(3, user.getSenha());
        st.setString(4, user.getNascimento());
        st.setString(5, user.getSexo());
        st.setInt(6, user.getCpf());

        st.execute();
        st.close();
        con.close();
    }
}