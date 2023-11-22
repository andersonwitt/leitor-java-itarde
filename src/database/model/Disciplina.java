package database.model;
public class Disciplina {
    public String Codigo;
    public String CodigoDiaSemana;
    public String Nome;
    public String DiaSemana;
    public String QuantidadeProfessores;

    public Disciplina(String codigo, String codigoSemana, String quantidadeProfessores) {
        Integer codigoInt = Integer.parseInt(codigo);
        Integer quantidadeInt = Integer.parseInt(quantidadeProfessores);

        this.Codigo = codigoInt.toString();
        this.CodigoDiaSemana = codigoSemana;
        this.QuantidadeProfessores = quantidadeInt.toString();

        this.Nome = Listas.Disciplina.items.get(codigoInt.toString());
        this.DiaSemana = Listas.DiaSemana.items.get(codigoSemana);
    }
}
