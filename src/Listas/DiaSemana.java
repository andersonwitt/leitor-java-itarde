package Listas;
import java.util.HashMap;

public class DiaSemana {
  public final static HashMap<String, String> items = new HashMap<String, String>() {
    {
      put("1", "Domingo");
      put("2", "Segunda-Feira");
      put("3", "Terça-Feira");
      put("4", "Quarta-Feira");
      put("5", "Quinta-Feira");
      put("6", "Sexta-Feira");
      put("7", "Sábado");
    }
  };
}
