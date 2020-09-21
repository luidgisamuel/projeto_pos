package controllers.mongoControllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.mongoDao.OrderDao;
import model.modelMongo.Order;
import utils.Json;

public class ControllerOrder extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setHeader("Access-Control-Allow-Origin", "*");
      resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
      resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
  }

  @Override
  protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    resp.setHeader("Access-Control-Allow-Origin", "*");

    final PrintWriter saida = resp.getWriter();
    String usuarioJsonStr = "";

    final OrderDao dao = new OrderDao();
    List<String> lstOrder = new ArrayList<>();

    if (req.getParameter("_id") == null) {
      try {
        lstOrder = dao.pesquisar();
        System.out.println("listado com sucesso!");

      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("erro ao pesquisar!");
      }
    } else {
      try {        
        String id = req.getParameter("_id");
        lstOrder = dao.pesquisarId(id);
        System.out.println("orduto listado com sucesso!");
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Erro ao listar orduto!");
      }
    }

    for (String Order : lstOrder) {
      usuarioJsonStr += (Order);
    }

    saida.println(usuarioJsonStr);

    saida.flush();
    saida.close();    
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    resp.setHeader("Access-Control-Allow-Origin", "*");
    req.setCharacterEncoding("UTF-8");    

    String json = Json.getJsonFromRequestBody(req.getReader());
    Order ord = Json.parseJsonToObject(json, Order.class);    
    OrderDao dao = new OrderDao();

    try {   
      dao.cadastrar(ord);
      System.out.println("cadastrado com sucesso!");  

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("erro ao cadastrar!");
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final OrderDao dao = new OrderDao();
    resp.setHeader("Access-Control-Allow-Origin", "*");
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
    resp.setHeader("Access-Control-Allow-Origin", "*");
    OrderDao dao = new OrderDao();
    Order ord = new Order();    

    ord.set_id(req.getParameter("_id"));
    ord.setUserId(Integer.parseInt(req.getParameter("userId")));
    ord.setQty(Integer.parseInt(req.getParameter("qty")));
    ord.setImage(req.getParameter("image"));
    ord.setPrice(Double.parseDouble(req.getParameter("price")));
    ord.setEndereco(req.getParameter("endereco"));
    ord.setNumero(Integer.parseInt(req.getParameter("numero")));
    ord.setBairro(req.getParameter("bairro"));
    ord.setCep(req.getParameter("cep"));
    ord.setCidade(req.getParameter("cidade"));
    ord.setEstado(req.getParameter("estado"));
    ord.setPaymentMethod(req.getParameter("paymentMethod"));
    ord.setItemsPrice(Double.parseDouble(req.getParameter("itemsPrice")));
    ord.setTotalPrice(Double.parseDouble(req.getParameter("totalPrice")));
    ord.setIsPaid(Boolean.parseBoolean(req.getParameter("isPaid")));
    ord.setPaidAt(req.getParameter("paidAt"));
    ord.setIsDelivered(Boolean.parseBoolean(req.getParameter("isDelivered")));
    ord.setDeliveredAt(req.getParameter("deliveredAt"));

    try {       
      dao.alterar(ord);
      System.out.println("alterado com sucesso!");      
    } catch (Exception e) {
      System.out.println("Erro ao alterar!");
      e.printStackTrace();      
    }
  }
  
}
