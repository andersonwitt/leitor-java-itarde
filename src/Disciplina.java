public class Disciplina {
    public String Codigo;
    public String CodigoDiaSemana;
    public String Nome;
    public String DiaSemana;
    public String QuantidadeProfessores;

    public Disciplina(String codigo, String codigoSemana, String quantidadeProfessores) {
        this.Codigo = codigo;
        this.CodigoDiaSemana = codigoSemana;
        this.QuantidadeProfessores = quantidadeProfessores;

        this.Nome = Listas.Disciplina.items.get(codigo);
        this.DiaSemana = Listas.DiaSemana.items.get(codigoSemana);
    }
}
