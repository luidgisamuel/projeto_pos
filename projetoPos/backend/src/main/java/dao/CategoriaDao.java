package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import util.ConexaoBanco;

public class CategoriaDao {

  public void cadastrar(Categoria categoria) throws SQLException {

    Connection con = new ConexaoBanco().getConnection();

    String query;
    query = "INSERT INTO categoria (nome,descricao) VALUES (?, ?);";

    PreparedStatement st = con.prepareStatement(query);    
    st.setString(1, categoria.getNome());
    st.setString(2, categoria.getDescricao());    

    st.execute();
    st.close();
    con.close();
  }

  public List<Categoria> pesquisar() throws SQLException {

    Connection con = new ConexaoBanco().getConnection();

    List<Categoria> lista = new ArrayList<>();
    String query = "SELECT * FROM categoria ";

    PreparedStatement st = con.prepareStatement(query);    

    ResultSet rs = st.executeQuery();

    while (rs.next()) {
      Categoria categoria = new Categoria();

      categoria.setIdCategoria(rs.getInt("idCategoria"));
      categoria.setNome(rs.getString("nome"));
      categoria.setDescricao(rs.getString("descricao"));      

      lista.add(categoria);
    }

    return lista;
  }

}