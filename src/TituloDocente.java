import java.util.HashMap;

public class TituloDocente {
  public final static HashMap<String, String> tituloDocente = new HashMap<>() {
    {
      put("01", "Pós-Graduação");
      put("02", "Mestrado");
      put("03", "Doutorado");
      put("04", "Pós-Doutorado");
    }
  };
}
