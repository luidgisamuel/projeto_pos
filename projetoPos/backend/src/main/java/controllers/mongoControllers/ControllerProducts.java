package controllers.mongoControllers;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;

import dao.mongoDao.ProductsDao;
import model.modelMongo.Product;
import utils.Json;

public class ControllerProducts extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");

    final PrintWriter saida = resp.getWriter();
    String usuarioJsonStr = "";

    final ProductsDao dao = new ProductsDao();
    List<String> lstUser = new ArrayList<>();

    if (req.getParameter("_id") == null) {
      try {
        lstUser = dao.pesquisar();
        System.out.println("listado com sucesso!");

      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("erro ao pesquisar!");
      }
    } else {
      try {        
        String id = req.getParameter("_id");
        lstUser = dao.pesquisarId(id);
        System.out.println("Produto listado com sucesso!");
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Erro ao listar produto!");
      }
    }

    for (String user : lstUser) {
      usuarioJsonStr += (user);
    }

    saida.println(usuarioJsonStr);

    saida.flush();
    saida.close();    
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    req.setCharacterEncoding("UTF-8");
    resp.setHeader("Access-Control-Allow-Origin", "*");

    String json = Json.getJsonFromRequestBody(req.getReader());
    Product prod = Json.parseJsonToObject(json, Product.class);
    ProductsDao dao = new ProductsDao();

    try {
      dao.cadastrar(prod);
      System.out.println("cadastrado com sucesso!");

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("erro ao cadastrar!");
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final ProductsDao dao = new ProductsDao();
    String id = req.getParameter("_id"); 

    try {
      dao.deletar(id);
      System.out.println("excluido com sucesso!");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Erro ao deletar!");
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ProductsDao dao = new ProductsDao();
    Product prod = new Product();    

    prod.set_id(req.getParameter("_id"));
    prod.setNome(req.getParameter("name"));
    prod.setImage(req.getParameter("image"));
    prod.setBrand(req.getParameter("brand"));
    prod.setPrice(Double.parseDouble(req.getParameter("price")));
    prod.setCategory(req.getParameter("category"));
    prod.setCountInStock(Integer.parseInt(req.getParameter("countInStock")));
    prod.setDescription(req.getParameter("description"));
    prod.setRating(Double.parseDouble(req.getParameter("rating")));
    prod.setNumReviews(Integer.parseInt(req.getParameter("numReviews")));

    try {
      dao.alterar(prod);
      System.out.println("alterado com sucesso!");
    } catch (Exception e) {
      System.out.println("Erro ao alterar!");
      e.printStackTrace();      
    }
  }
}
