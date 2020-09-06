package model.modelMongo;

public class Users {
  private String _id;
  private String nome;
  private String cpf;
  private String cidade;


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

  public String getCpf() {
    return this.cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getCidade() {
    return this.cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

}