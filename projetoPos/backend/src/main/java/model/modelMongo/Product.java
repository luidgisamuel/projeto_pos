package model.modelMongo;

public class Product {
  private String _id;
  private String nome;
  private String image;
  private String brand;
  private Double price;
  private  String category;
  private int countInStock;
  private String description;
  private double rating;
  private int numReviews; 

  public String get_id() {
    return this._id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getBrand() {
    return this.brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public Double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getCountInStock() {
    return this.countInStock;
  }

  public void setCountInStock(int countInStock) {
    this.countInStock = countInStock;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getRating() {
    return this.rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public int getNumReviews() {
    return this.numReviews;
  }

  public void setNumReviews(int numReviews) {
    this.numReviews = numReviews;
  }

}
