package model;

public class Produto {
  private int idProduto;
  private double preco;
  private String nome;
  private String imagem;
  private int quantidade;
  private String prodDescricao;
  private Categoria cat;

  public int getIdProduto() {
    return this.idProduto;
  }

  public void setIdProduto(int idProduto) {
    this.idProduto = idProduto;
  }

  public Categoria getCategoria() {
    return this.cat;
  }

  public void setCategoria(Categoria cat) {
    this.cat = cat;
  }

  public double getPreco() {
    return this.preco;
  }

  public void setPreco(double preco) {
    this.preco = preco;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getImagem() {
    return this.imagem;
  }

  public void setImagem(String imagem) {
    this.imagem = imagem;
  }

  public int getQuantidade() {
    return this.quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public String getProdDescricao() {
    return this.prodDescricao;
  }

  public void setProdDescricao(String prodDescricao) {
    this.prodDescricao = prodDescricao;
  }

  
}