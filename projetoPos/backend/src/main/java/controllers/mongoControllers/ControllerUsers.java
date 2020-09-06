package controllers.mongoControllers;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.mongoDao.UsersDao;
import model.modelMongo.Users;
import utils.Json;

public class ControllerUsers extends HttpServlet {
  @Override
  protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");

    final PrintWriter saida = resp.getWriter();
    String usuarioJsonStr = "";

    final UsersDao dao = new UsersDao();
    List<String> lstUser = new ArrayList<>();
    try {
      lstUser = dao.pesquisar();
      System.out.println("listado com sucesso!");

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("erro ao pesquisar!");
    }

    for (String user : lstUser) {
      usuarioJsonStr += (user);
    }

    saida.println(usuarioJsonStr);

    saida.flush();
    saida.close();
    System.out.println("listado com  sucesso!");

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    req.setCharacterEncoding("UTF-8");
    resp.setHeader("Access-Control-Allow-Origin", "*");

    String json = Json.getJsonFromRequestBody(req.getReader());
    Users user = Json.parseJsonToObject(json, Users.class);
    UsersDao ud = new UsersDao();

    try {
      ud.cadastrar(user);
      System.out.println("cadastrado com sucesso!");

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("erro ao cadastrar!");
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    final UsersDao ud = new UsersDao();
    String person = (req.getParameter("cpf"));

    try {
      ud.deletar(person);
      System.out.println("excluido com sucesso!");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Erro ao deletar!");
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UsersDao ud = new UsersDao();
    Users user = new Users();

    user.setCpf(req.getParameter("cpf"));
    user.setNome(req.getParameter("nome"));
    user.setCidade(req.getParameter("cidade"));

    try {
      ud.alterar(user);
      System.out.println("alterado com sucesso!");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Erro ao alterar!");
    }
  }
}
