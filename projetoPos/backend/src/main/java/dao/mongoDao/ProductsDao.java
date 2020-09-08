package dao.mongoDao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;

import model.modelMongo.Product;
import utils.ConexaoMongo;

public class ProductsDao {

  private MongoDatabase dtbase;

  public ProductsDao() {
    this.dtbase = new ConexaoMongo().getDatabase();
  }

  public void cadastrar(Product prod) throws MongoException {
    MongoCollection<Document> documentos = dtbase.getCollection("Product");

    Document doc = new Document();
    doc.append("nome", prod.getNome());
    doc.append("image", prod.getImage());
    doc.append("brand", prod.getBrand());
    doc.append("price", prod.getPrice());
    doc.append("category", prod.getCategory());
    doc.append("countInStock", prod.getCountInStock());
    doc.append("description", prod.getDescription());
    doc.append("numReviews", prod.getNumReviews());

    documentos.insertOne(doc);
  }

  public List<String> pesquisar() throws MongoException {
    MongoCollection<Document> collection = dtbase.getCollection("Product");

    MongoCursor<Document> mcursor = collection.find().iterator();

    List<String> lstUser = new ArrayList<String>();
    while (mcursor.hasNext()) {
      Document docAtual = mcursor.next();
      lstUser.add(docAtual.toJson());
    }
    return lstUser;
  }

  public List<String> pesquisarId(String id) throws MongoException {
    MongoCollection<Document> collection = dtbase.getCollection("Product");    
    BasicDBObject searchQuery = new BasicDBObject();      
    searchQuery.append("_id", new ObjectId(id));    

    List<String> lstUser = new ArrayList<String>();
    MongoCursor<Document> mcursor = collection.find(searchQuery).iterator();
    Document docAtual = mcursor.next();
    lstUser.add(docAtual.toJson());

    return  lstUser; 
  }

  public void alterar(Product prod) throws MongoException {
    MongoCollection<Document> documentos = dtbase.getCollection("Product");

    BasicDBObject newDocument = new BasicDBObject();
    newDocument.append("$set", new BasicDBObject()
    .append("nome", prod.getNome())    
    .append("image", prod.getImage())
    .append("brand", prod.getBrand())
    .append("price", prod.getPrice())
    .append("category", prod.getCategory())
    .append("countInStock", prod.getCountInStock())
    .append("description", prod.getDescription())
    .append("rating", prod.getRating())
    .append("numReviews", prod.getNumReviews()));    
           
    BasicDBObject searchQuery = new BasicDBObject();      
    searchQuery.append("_id", new ObjectId(prod.get_id()));
    documentos.updateOne(searchQuery, newDocument);    
  }

  public void deletar(String id) throws MongoException {
    MongoCollection<Document> documentos = dtbase.getCollection("Product");    

    BasicDBObject searchQuery = new BasicDBObject();      
    searchQuery.append("_id", new ObjectId(id));   

    documentos.deleteOne(searchQuery);
  }
}
