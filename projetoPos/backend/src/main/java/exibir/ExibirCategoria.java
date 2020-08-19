package exibir;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CategoriaDao;
import model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class ExibirCategoria extends HttpServlet {

  @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        final PrintWriter saida = resp.getWriter();
        final CategoriaDao dao = new CategoriaDao();
        final Gson gson = new Gson();
        String categoriaJsonStr = "";

        List<Categoria> lstCategoria = new ArrayList<>();

        try {            
            lstCategoria = dao.pesquisar();
        } catch (SQLException e) {            
            e.printStackTrace();
        }
        
        for (Categoria categoria : lstCategoria) 
        {
           categoriaJsonStr += gson.toJson(categoria);
        }

        saida.println(categoriaJsonStr);

        saida.flush();
        saida.close();
    }
    
    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp)
     throws ServletException, IOException {
         Categoria categoria = new Categoria();

        categoria.setNome(req.getParameter("nome"));
        categoria.setDescricao(req.getParameter("descricao"));      
       
         CategoriaDao cd = new CategoriaDao();

         try{
             cd.cadastrar(categoria);
             System.out.println(" cadastrado com sucesso!");
         }catch (SQLException e){
             e.printStackTrace();
         }
     }
  
}