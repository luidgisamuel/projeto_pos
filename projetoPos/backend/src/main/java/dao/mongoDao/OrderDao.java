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

import model.modelMongo.Order;
import utils.ConexaoMongo;

public class OrderDao {
  private MongoDatabase dtbase;

  public OrderDao() {
    this.dtbase = new ConexaoMongo().getDatabase();
  }

  public void cadastrar(Order ord) throws MongoException {
    MongoCollection<Document> documentos = dtbase.getCollection("Order");

    Document doc = new Document();
    doc.append("_id", ord.get_id());
    doc.append("userId", ord.getUserId());
    doc.append("qty", ord.getQty());
    doc.append("image", ord.getImage());
    doc.append("price", ord.getPrice());
    doc.append("endereco", ord.getEndereco());
    doc.append("numero", ord.getNumero());
    doc.append("bairro", ord.getBairro());
    doc.append("cep", ord.getCep());
    doc.append("cidade", ord.getCidade());
    doc.append("estado", ord.getEstado());
    doc.append("paymentMethod", ord.getPaymentMethod());
    doc.append("itemsPrice", ord.getItemsPrice());
    doc.append("totalPrice", ord.getTotalPrice());
    doc.append("isPaid", ord.getIsPaid());
    doc.append("paidAt", ord.getPaidAt());
    doc.append("isDelivered", ord.getIsDelivered());
    doc.append("deliveredAt", ord.getDeliveredAt());

    documentos.insertOne(doc);
  }

  public List<String> pesquisar() throws MongoException {
    MongoCollection<Document> collection = dtbase.getCollection("Order");

    MongoCursor<Document> mcursor = collection.find().iterator();

    List<String> lstUser = new ArrayList<String>();
    while (mcursor.hasNext()) {
      Document docAtual = mcursor.next();
      lstUser.add(docAtual.toJson());
    }
    return lstUser;
  }

  public List<String> pesquisarId(String id) throws MongoException {
    MongoCollection<Document> collection = dtbase.getCollection("Order");    
    BasicDBObject searchQuery = new BasicDBObject();      
    searchQuery.append("_id", new ObjectId(id));    

    List<String> lstOrder = new ArrayList<String>();
    MongoCursor<Document> mcursor = collection.find(searchQuery).iterator();
    Document docAtual = mcursor.next();
    lstOrder.add(docAtual.toJson());

    return  lstOrder; 
  }

  public void alterar(Order ord) throws MongoException {
    MongoCollection<Document> documentos = dtbase.getCollection("Order");

    BasicDBObject newDocument = new BasicDBObject();
    newDocument.append("$set", new BasicDBObject()
    .append("_id", ord.get_id())
    .append("userId", ord.getUserId())
    .append("qty", ord.getQty())
    .append("image", ord.getImage())
    .append("price", ord.getPrice())
    .append("endereco", ord.getEndereco())
    .append("numero", ord.getNumero())
    .append("bairro", ord.getBairro())
    .append("cep", ord.getCep())
    .append("cidade", ord.getCidade())
    .append("estado", ord.getEstado())
    .append("paymentMethod", ord.getPaymentMethod())
    .append("itemsPrice", ord.getItemsPrice())
    .append("totalPrice", ord.getTotalPrice())
    .append("isPaid", ord.getIsPaid())
    .append("paidAt", ord.getPaidAt())
    .append("isDelivered", ord.getIsDelivered())
    .append("deliveredAt", ord.getDeliveredAt()));
           
    BasicDBObject searchQuery = new BasicDBObject();      
    searchQuery.append("_id", new ObjectId(ord.get_id()));
    documentos.updateOne(searchQuery, newDocument);    
  }

  public void deletar(String id) throws MongoException {
    MongoCollection<Document> documentos = dtbase.getCollection("Order");    

    BasicDBObject searchQuery = new BasicDBObject();      
    searchQuery.append("_id", new ObjectId(id));   

    documentos.deleteOne(searchQuery);
  }
}
