package database.model;
import Listas.TituloDocente;

public class Professor {
    private String Nome;
    private int TituloID;
    private String Titulo;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getTituloID() {
        return TituloID;
    }

    public void setTituloID(int tituloID) {
        TituloID = tituloID;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(int tituloID) {
        
        Titulo = TituloDocente.items.get(Integer.toString(tituloID));
    }

    public Professor(int tituloID) {
        this.Titulo = TituloDocente.items.get(Integer.toString(tituloID));
    }
}