package model;

public class Usuario {
  private int cpf;
  private String nome;
  private String email;
  private String senha;
  private String nascimento;
  private String sexo;

  public int getCpf() {
    return this.cpf;
  }

  public void setCpf(int cpf) {
    this.cpf = cpf;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return this.senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getNascimento() {
    return this.nascimento;
  }

  public void setNascimento(String nascimento) {
    this.nascimento = nascimento;
  }

  public String getSexo() {
    return this.sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }
 
  
}