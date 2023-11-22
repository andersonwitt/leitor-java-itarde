package database.model;
public class Fase {
    public String Nome;
    public String QuantidadeDisciplinas;
    public String QuantidadeProfessores;

    public Fase(String nome, String quantidadeDisciplinas, String quantidadeProfessores) {
        this.Nome = nome;
        this.QuantidadeDisciplinas = quantidadeDisciplinas;
        this.QuantidadeProfessores = quantidadeProfessores;
    }
}
