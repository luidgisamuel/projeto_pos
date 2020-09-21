package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProdutoDao;
import model.Produto;
import utils.Json;

import java.util.ArrayList;
import java.util.List;

public class ControllerProduto extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {                

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        final PrintWriter saida = resp.getWriter();
        final ProdutoDao dao = new ProdutoDao();
        final Gson gson = new Gson();
        String produtoJsonStr = "";

        List<Produto> lstProd = new ArrayList<>();

      

        if ( req.getParameter("ProductId") != null) {
            try {
                int id = Integer.parseInt(req.getParameter("ProductId"));  
                lstProd = dao.pesquisar(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
          } if ((req.getParameter("category") != null))  {
            try {        
              String cat = req.getParameter("category");                         
              lstProd = dao.pesquisarId(cat);              
              System.out.println("Produto listado com sucesso!");
            } catch (Exception e) {
              e.printStackTrace();
              System.out.println("Erro ao listar produto!");
            }
          }

        for (Produto prod : lstProd) {
            produtoJsonStr += gson.toJson(prod);
        }
        saida.println(produtoJsonStr);

        saida.flush();
        saida.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        String json = Json.getJsonFromRequestBody(req.getReader());
        Produto prod = Json.parseJsonToObject(json, Produto.class);        

        ProdutoDao pd = new ProdutoDao();

        try {
            pd.cadastrar(prod);
            System.out.println("Produto cadastrado com Sucesso!!!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar produto!!!");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ProdutoDao pd = new ProdutoDao();
        resp.setHeader("Access-Control-Allow-Origin", "*");
        int prod = Integer.parseInt(req.getParameter("ProductId"));

        try {
            pd.deletar(prod);
            System.out.println(" excluido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Produto prod = new Produto();       
        resp.setHeader("Access-Control-Allow-Origin", "*");
        
        prod.setProductId(Integer.parseInt(req.getParameter("ProductId")));
        prod.setName(req.getParameter("name"));
        prod.setImage(req.getParameter("image"));
        prod.setBrand(req.getParameter("brand"));
        prod.setPrice(Integer.parseInt(req.getParameter("price")));
        prod.setCategory(req.getParameter("category"));
        prod.setCountInStock(Integer.parseInt(req.getParameter("countInStock")));
        prod.setDescription(req.getParameter("description"));
        prod.setRating(Double.parseDouble(req.getParameter("rating")));
        prod.setNumReviews(Integer.parseInt(req.getParameter("numReviews")));

        ProdutoDao pd = new ProdutoDao();

        try {
            pd.alterar(prod);
            System.out.println("alterado com Sucesso!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}