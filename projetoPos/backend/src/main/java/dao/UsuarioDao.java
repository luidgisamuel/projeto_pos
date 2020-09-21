package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import utils.ConexaoBanco;

public class UsuarioDao {

    public void cadastrar(Usuario user) throws SQLException {

        Connection con = new ConexaoBanco().getConnection();

        String query;
        query = "INSERT INTO usuarios (name, email, password) VALUES (?, ?, ?);";

        PreparedStatement st = con.prepareStatement(query);        
        st.setString(1, user.getName());
        st.setString(2, user.getEmail());
        st.setString(3, user.getPassword());                

        st.execute();
        st.close();
        con.close();
    }

    public List<Usuario> pesquisar(String email, String password) throws SQLException {

        Connection con = new ConexaoBanco().getConnection();

        List<Usuario> lista = new ArrayList<>();
        String query = "SELECT * FROM usuarios WHERE email = ? AND password=?";

        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, email);
        st.setString(2, password);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Usuario user = new Usuario();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));            

            lista.add(user);
        }

        return lista;
    }

    public void deletar(int pessoa) throws SQLException {
        String query = "DELETE FROM usuarios WHERE id = ?";

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
        String query = "UPDATE usuarios set name=?, email=?, password=? where id=?";
        
        PreparedStatement st = con.prepareStatement(query);       
        st.setString(1, user.getName());   
        st.setString(2, user.getEmail());
        st.setString(3, user.getPassword());           
        st.setInt(4, user.getId());

        st.execute();
        st.close();
        con.close();
    }
}