import Listas.TituloDocente;

public class Professor {
    public String Nome;
    public String TituloID;
    public String Titulo;

    public Professor(String nome, String tituloID) {
        this.Nome = nome;
        this.TituloID = tituloID;

        this.Titulo = TituloDocente.items.get(tituloID);
    }
}
