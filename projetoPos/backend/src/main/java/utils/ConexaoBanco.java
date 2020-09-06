package utils;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;

public class ConexaoBanco {
  private Connection conexao = null;
  private final String BANCO = "projeto";
  private final String LOGIN = "root";
  private final String SENHA = "";
  private final String HOST = "localhost";

  public Connection getConnection() {
    try {
      MysqlDataSource ds = new MysqlDataSource();
      ds.setServerName(HOST);
      ds.setDatabaseName(BANCO);
      ds.setUser(LOGIN);
      ds.setPassword(SENHA);
      ds.setServerTimezone("UTC");
      ds.setConnectTimeout(2000);

      conexao = ds.getConnection();       
      System.out.println("sucesso na conexão!!!!!!");

    } catch (SQLException e) {
      System.out.println("falha na conexão!!!!!!"+e.getMessage());
    }

    return conexao;
   
  }
}