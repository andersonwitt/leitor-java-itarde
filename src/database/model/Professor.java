package database.model;

import Listas.TituloDocente;

public class Professor {
  private int Id;
  private String Nome;
  private int TituloID;
  private String Titulo;
  private int CursoId;

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

  public int getTituloId() {
    return TituloID;
  }

  public void setTituloID(int tituloID) {
    TituloID = tituloID;
  }

  public String getTitulo() {
    return Titulo;
  }

  public void setTitulo(String tituloID) {
    Titulo = TituloDocente.items.get(tituloID);
  }

  public int getCursoId() {
    return CursoId;
  }

  public void setCursoId(int cursoId) {
    CursoId = cursoId;
  }
}
