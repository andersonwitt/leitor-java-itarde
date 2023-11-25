package Listas;
import java.util.HashMap;

public class TituloDocente {
  public final static HashMap<String, String> items = new HashMap<String, String>() {
    {
      put("1", "Pós-Graduação");
      put("2", "Mestrado");
      put("3", "Doutorado");
      put("4", "Pós-Doutorado");
    }
  };
}
