package dao.mongoDao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.Document;

import model.modelMongo.Users;
import utils.ConexaoMongo;

public class UsersDao {
  private MongoDatabase dtbase;

  public UsersDao() {
    this.dtbase = new ConexaoMongo().getDatabase();
  }

  public void cadastrar(Users user) throws MongoException{
    MongoCollection<Document> documentos = dtbase.getCollection("users");

    Document doc = new Document();
    doc.append("nome", user.getNome());
    doc.append("cpf", user.getCpf());
    doc.append("cidade", user.getCidade());   

      documentos.insertOne(doc);    
  }

  public List<String> pesquisar() throws MongoException {
    MongoCollection<Document> collection = dtbase.getCollection("users");

    MongoCursor<Document> mcursor = collection.find().iterator();

   List<String> lstUser = new ArrayList<String>();
    while (mcursor.hasNext()) {
      Document docAtual = mcursor.next();
      lstUser.add(docAtual.toJson());
    }
    return lstUser;
  }

  public void alterar(Users user) throws MongoException {
    MongoCollection<Document> documentos = dtbase.getCollection("users");    

    BasicDBObject newDocument = new BasicDBObject();
    newDocument.append("$set", new BasicDBObject()
      .append("nome", user.getNome())
      .append("cidade", user.getCidade()));

    BasicDBObject searchQuery = new BasicDBObject().append("cpf",user.getCpf());

    documentos.updateOne(searchQuery, newDocument);
  }

  public void deletar(String user) throws MongoException{
    MongoCollection<Document> documentos = dtbase.getCollection("users");

    documentos.deleteOne(Filters.eq("cpf", user));     
  }
}