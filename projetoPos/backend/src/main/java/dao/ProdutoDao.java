package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import utils.ConexaoBanco;


public class ProdutoDao {

    public void cadastrar(Produto prod) throws SQLException {
        
        Connection con = new ConexaoBanco().getConnection();

        String query;
        query = "INSERT INTO produtos (name, image, brand, price, category, countInStock, description, rating, numReviews) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement st = con.prepareStatement(query);        
        st.setString(1, prod.getName());
        st.setString(2, prod.getImage());
        st.setString(3, prod.getBrand());
        st.setDouble(4, prod.getPrice());
        st.setString(5, prod.getCategory());     
        st.setInt(6, prod.getCountInStock());  
        st.setString(7, prod.getDescription());
        st.setDouble(8, prod.getRating());
        st.setInt(9, prod.getNumReviews());

        st.execute();
        st.close();
        con.close();
    }

    public List<Produto> pesquisar(int id) throws SQLException{

        Connection con = new ConexaoBanco().getConnection();

        List<Produto> lista = new ArrayList<>();
        String query = "SELECT * FROM produtos WHERE  ProductId = ?";

        PreparedStatement st =con.prepareStatement(query);      
        st.setInt(1, id); 
        ResultSet rs = st.executeQuery();

        while(rs.next()){
            Produto prod = new Produto();            

            prod.setProductId(rs.getInt("ProductId"));           
            prod.setName(rs.getString("name"));
            prod.setImage(rs.getString("image"));
            prod.setBrand(rs.getString("brand"));
            prod.setPrice(rs.getDouble("price"));
            prod.setCategory(rs.getString("category")); 
            prod.setCountInStock(rs.getInt("countInStock"));  
            prod.setDescription(rs.getString("description"));   
            prod.setRating(rs.getDouble("rating"));           
            prod.setNumReviews(rs.getInt("numReviews"));  

            lista.add(prod);
        }

        return lista;
    }

    public List<Produto> pesquisarId(String cat) throws SQLException {
        Connection con = new ConexaoBanco().getConnection();

        List<Produto> lista = new ArrayList<>();
        String query = "SELECT * FROM produtos WHERE  category= ?  ";

        PreparedStatement st =con.prepareStatement(query);      
        st.setString(1, cat);        
        ResultSet rs = st.executeQuery();    
       
        while(rs.next()){
            Produto prod = new Produto();            

            prod.setProductId(rs.getInt("ProductId"));           
            prod.setName(rs.getString("name"));
            prod.setImage(rs.getString("image"));
            prod.setBrand(rs.getString("brand"));
            prod.setPrice(rs.getDouble("price"));
            prod.setCategory(rs.getString("category")); 
            prod.setCountInStock(rs.getInt("countInStock"));  
            prod.setDescription(rs.getString("description"));   
            prod.setRating(rs.getDouble("rating"));           
            prod.setNumReviews(rs.getInt("numReviews"));  

            lista.add(prod);
        }
        return lista;            
      }

    public void deletar(int prod) throws SQLException {
        String query = "DELETE FROM produtos WHERE idProduto= ?";

        Connection con = new ConexaoBanco().getConnection();
        PreparedStatement st = null;

        st = con.prepareStatement(query);
        st.setInt(1, prod);
        st.execute();
        st.close();
        con.close();
    }

    public  void alterar(Produto prod) throws SQLException {
        Connection con = new ConexaoBanco().getConnection();
        String query = "UPDATE produtos set  name=?, image=?, brand= ?, price =?, category=?, countInStock=?, description=?, rating=?, numReviews=?  where ProductId=?";        
        PreparedStatement st = con.prepareStatement(query);
        
        st.setString(1, prod.getName());
        st.setString(2, prod.getImage());
        st.setString(3, prod.getBrand());
        st.setDouble(4, prod.getPrice());
        st.setString(5, prod.getCategory());     
        st.setInt(6, prod.getCountInStock());  
        st.setString(7, prod.getDescription());
        st.setDouble(8, prod.getRating());
        st.setInt(9, prod.getNumReviews());
        st.setInt(10, prod.getProductId());

        st.execute();
        st.close();
        con.close();
    }
}