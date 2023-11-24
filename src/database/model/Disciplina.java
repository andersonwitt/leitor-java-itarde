package database.model;

public class Disciplina {
  private int Id;
  private int CodigoDiaSemana;
  private String Nome;
  private String DiaSemana;
  private int QuantidadeProfessores;

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public int getCodigoDiaSemana() {
    return CodigoDiaSemana;
  }

  public void setCodigoDiaSemana(int codigoDiaSemana) {
    CodigoDiaSemana = codigoDiaSemana;
  }

  public String getNome() {
    return Nome;
  }

  public void setNome(int codigoDisciplina) {
    Nome = Listas.Disciplina.items.get(Integer.toString(codigoDisciplina));
  }

  public String getDiaSemana() {
    return DiaSemana;
  }

  public void setDiaSemana(int codigoDiaSemana) {
    DiaSemana = Listas.DiaSemana.items.get(Integer.toString(codigoDiaSemana));
  }

  public int getQuantidadeProfessores() {
    return QuantidadeProfessores;
  }

  public void setQuantidadeProfessores(int quantidadeProfessores) {
    QuantidadeProfessores = quantidadeProfessores;
  }
}
