package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UsuarioDao;
import model.Usuario;
import utils.Json;

import java.util.ArrayList;
import java.util.List;

public class ControllerUsuario extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        final PrintWriter saida = resp.getWriter();
        final UsuarioDao dao = new UsuarioDao();
        final Gson gson = new Gson();
        String usuarioJsonStr = "";

        List<Usuario> lstUser = new ArrayList<>();

        try {
            int pessoa = Integer.parseInt(req.getParameter("cpf"));
            lstUser = dao.pesquisar(pessoa);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Usuario user : lstUser) {
            usuarioJsonStr += gson.toJson(user);
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
        Usuario user = Json.parseJsonToObject(json, Usuario.class);

        UsuarioDao ud = new UsuarioDao();

        try {
            ud.cadastrar(user);
            System.out.println(" sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final UsuarioDao ud = new UsuarioDao();
        int pessoa = Integer.parseInt(req.getParameter("cpf"));

        try {
            ud.deletar(pessoa);
            System.out.println(" exluido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsuarioDao ud = new UsuarioDao();
        Usuario user = new Usuario();

        user.setNome(req.getParameter("nome"));
        user.setEmail(req.getParameter("email"));
        user.setSenha(req.getParameter("senha"));
        user.setNascimento(req.getParameter("nascimento"));
        user.setSexo(req.getParameter("sexo"));
        user.setCpf(Integer.parseInt(req.getParameter("cpf")));

        try {
            ud.alterar(user);
            System.out.println(" alterado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
