package model.modelMongo;

public class Order {
  private String _id;
  private int userId;
  private int qty;
  private String image;
  private double price;  
  private String endereco;
  private int numero;
  private String bairro;
  private String cep;
  private String cidade;
  private String estado;
  private String paymentMethod;
  private double itemsPrice;
  private double totalPrice;
  private boolean isPaid;
  private String paidAt;
  private boolean isDelivered;
  private String deliveredAt;

  public String get_id() {
    return this._id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public int getUserId() {
    return this.userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getQty() {
    return this.qty;
  }

  public void setQty(int qty) {
    this.qty = qty;
  }

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getEndereco() {
    return this.endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public int getNumero() {
    return this.numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public String getBairro() {
    return this.bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCep() {
    return this.cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getCidade() {
    return this.cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return this.estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getPaymentMethod() {
    return this.paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public double getItemsPrice() {
    return this.itemsPrice;
  }

  public void setItemsPrice(double itemsPrice) {
    this.itemsPrice = itemsPrice;
  }

  public double getTotalPrice() {
    return this.totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public boolean isIsPaid() {
    return this.isPaid;
  }

  public boolean getIsPaid() {
    return this.isPaid;
  }

  public void setIsPaid(boolean isPaid) {
    this.isPaid = isPaid;
  }

  public String getPaidAt() {
    return this.paidAt;
  }

  public void setPaidAt(String paidAt) {
    this.paidAt = paidAt;
  }

  public boolean isIsDelivered() {
    return this.isDelivered;
  }

  public boolean getIsDelivered() {
    return this.isDelivered;
  }

  public void setIsDelivered(boolean isDelivered) {
    this.isDelivered = isDelivered;
  }

  public String getDeliveredAt() {
    return this.deliveredAt;
  }

  public void setDeliveredAt(String deliveredAt) {
    this.deliveredAt = deliveredAt;
  }
}
