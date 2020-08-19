package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import model.Categoria;
import util.ConexaoBanco;


public class ProdutoDao {

    public void cadastrar(Produto prod) throws SQLException {
        
        Connection con = new ConexaoBanco().getConnection();

        String query;
        query = "INSERT INTO produtos (idCategoria,preco,nome,imagem,quantidade,prodDescricao) VALUES (?, ?, ?, ?, ?, ?);";

        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, prod.getCategoria().getIdCategoria());
        st.setDouble(2, prod.getPreco());
        st.setString(3, prod.getNome());
        st.setString(4, prod.getImagem());
        st.setInt(5, prod.getQuantidade());
        st.setString(6, prod.getProdDescricao());       

        st.execute();
        st.close();
        con.close();
    }

    public List<Produto> pesquisar() throws SQLException{

        Connection con = new ConexaoBanco().getConnection();

        List<Produto> lista = new ArrayList<>();
        String query = "SELECT * FROM produtos ";

        PreparedStatement st =con.prepareStatement(query);      

        ResultSet rs = st.executeQuery();

        while(rs.next()){
            Produto prod = new Produto();
            Categoria cat = new Categoria();

            prod.setIdProduto(rs.getInt("idProduto"));
            cat.setIdCategoria(rs.getInt("idCategoria"));
            prod.setPreco(rs.getDouble("preco"));
            prod.setNome(rs.getString("nome"));
            prod.setImagem(rs.getString("imagem"));
            prod.setQuantidade(rs.getInt("quantidade"));
            prod.setProdDescricao(rs.getString("prodDescricao"));         

            prod.setCategoria(cat);

            lista.add(prod);
        }

        return lista;

    }
}