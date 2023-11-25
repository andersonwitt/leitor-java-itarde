package database.model;

import java.util.Date;

public class Curso {
  private int Id;
  private String Nome;
  private String PeriodoInicial;
  private String PeriodoFinal;
  private Date DataProcessamento;
  private int Sequencia;
  private String VersaoLayout;

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

  public String getPeriodoInicial() {
    return PeriodoInicial;
  }

  public void setPeriodoInicial(String periodoInicial) {
    PeriodoInicial = periodoInicial;
  }

  public String getPeriodoFinal() {
    return PeriodoFinal;
  }

  public void setPeriodoFinal(String periodoFinal) {
    PeriodoFinal = periodoFinal;
  }

  public Date getDataProcessamento() {
    return DataProcessamento;
  }

  public void setDataProcessamento(Date dataProcessamento) {
    DataProcessamento = dataProcessamento;
  }

  public int getSequencia() {
    return Sequencia;
  }

  public void setSequencia(int sequencia) {
    Sequencia = sequencia;
  }

  public String getVersaoLayout() {
    return VersaoLayout;
  }

  public void setVersaoLayout(String versaoLayout) {
    VersaoLayout = versaoLayout;
  }
}
