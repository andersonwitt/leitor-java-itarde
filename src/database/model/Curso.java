package database.model;

import java.util.Date;

public class Curso {
  private int Id;
  private String Nome;
  private int PeriodoInicial;
  private int PeriodoFinal;
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

  public int getPeriodoInicial() {
    return PeriodoInicial;
  }

  public void setPeriodoInicial(int periodoInicial) {
    PeriodoInicial = periodoInicial;
  }

  public int getPeriodoFinal() {
    return PeriodoFinal;
  }

  public void setPeriodoFinal(int periodoFinal) {
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
