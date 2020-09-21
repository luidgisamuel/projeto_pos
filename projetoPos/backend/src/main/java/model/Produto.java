package model;

public class Produto {
  private int ProductId;  
  private String name;
  private String image;
  private String brand;
  private Double price;
  private  String category;
  private int countInStock;
  private String description;
  private double rating;
  private int numReviews; 

  public int getProductId() {
    return this.ProductId;
  }

  public void setProductId(int ProductId) {
    this.ProductId = ProductId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
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