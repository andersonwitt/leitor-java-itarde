import java.util.HashMap;

public class Consts {  
    public final static HashMap<String, String> constantes = new HashMap<String, String>() {{
        put("curso", "0");
        put("fase", "1");
        put("disciplina", "2");
        put("professor", "3");
        put("trailer", "9");
    }};
    
    public static boolean IsTypeOf(ConstEnum tipoRegistroEnum, String line) {
        String tipoRegistro = constantes.get(tipoRegistroEnum.getValue());
        return line.startsWith(tipoRegistro);
    };
}
