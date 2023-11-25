package database.model;

public class Disciplina {
  private String Id;
  private int CodigoDiaSemana;
  private String Nome;
  private String DiaSemana;
  private int QuantidadeProfessores;
  private int CursoId;

  public String getId() {
    return Id;
  }
  
  public void setId(String id) {
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
    Nome = Listas.Disciplinas.items.get(Integer.toString(codigoDisciplina));
  }
  
  public String getDiaSemana() {
    return DiaSemana;
  }
  
  public void setDiaSemana(String codigoDiaSemana) {
    DiaSemana = Listas.DiaSemana.items.get(codigoDiaSemana);
  }
  
  public int getQuantidadeProfessores() {
    return QuantidadeProfessores;
  }

  public void setQuantidadeProfessores(int quantidadeProfessores) {
    QuantidadeProfessores = quantidadeProfessores;
  }

  public int getCursoId() {
    return CursoId;
  }
  
  public void setCursoId(int cursoId) {
    CursoId = cursoId;
  }
}
