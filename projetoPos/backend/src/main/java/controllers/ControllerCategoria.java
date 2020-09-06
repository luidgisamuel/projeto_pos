package controllers;

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
import utils.Json;

import java.util.ArrayList;
import java.util.List;

public class ControllerCategoria extends HttpServlet {

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

        req.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        String json = Json.getJsonFromRequestBody(req.getReader());
        Categoria categoria = Json.parseJsonToObject(json, Categoria.class);     
       
         CategoriaDao cd = new CategoriaDao();

         try{
             cd.cadastrar(categoria);
             System.out.println(" cadastrado com sucesso!");
         }catch (SQLException e){
             e.printStackTrace();
         }
     }

     @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException {
        final CategoriaDao cd = new CategoriaDao();
        int cat = Integer.parseInt(req.getParameter("idCategoria"));

        try {
            cd.deletar(cat);
            System.out.println(" exluido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException {
        CategoriaDao cd = new CategoriaDao();        
        Categoria cat = new Categoria();         
        
        cat.setNome(req.getParameter("nome"));
        cat.setDescricao(req.getParameter("descricao"));        
        cat.setIdCategoria(Integer.parseInt(req.getParameter("idCategoria")));

        try {
            cd.alterar(cat);
            System.out.println(" alterado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }       
    }
  
}