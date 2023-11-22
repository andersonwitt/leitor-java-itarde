package database.model;

public class Curso {
  private int Id;

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public String Nome;
  public String PeriodoInicial;
  public String PeriodoFinal;

  public Curso(String nome, String periodoInicial, String periodoFinal) {
    this.Nome = nome;
    this.PeriodoInicial = periodoInicial;
    this.PeriodoFinal = periodoFinal;
  }

}
