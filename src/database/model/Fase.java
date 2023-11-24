package database.model;

public class Fase {
  private int Id;
  private String Nome;
  private int QuantidadeDisciplinas;
  private int QuantidadeProfessores;

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String getNome() {
    return Nome;
  }

  public void setNome(String nome) {
    Nome = nome;
  }

  public int getQuantidadeDisciplinas() {
    return QuantidadeDisciplinas;
  }

  public void setQuantidadeDisciplinas(int quantidadeDisciplinas) {
    QuantidadeDisciplinas = quantidadeDisciplinas;
  }

  public int getQuantidadeProfessores() {
    return QuantidadeProfessores;
  }

  public void setQuantidadeProfessores(int quantidadeProfessores) {
    QuantidadeProfessores = quantidadeProfessores;
  }
}
