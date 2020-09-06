package utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class ConexaoMongo {
  private MongoClient conexao = null;  
  private final String BANCO =  "ecommerce";
  private final String HOST = "localhost";
  private final int PORT = 27017;

  public MongoDatabase getDatabase() {
    conexao = new MongoClient(HOST, PORT);
    return conexao.getDatabase(BANCO);
  }
}