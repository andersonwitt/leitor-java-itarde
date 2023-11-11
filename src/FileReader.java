import java.io.BufferedReader;
import java.io.File;

public class FileReader {

    private File _arquivo;

    FileReader(File arquivo) {
        _arquivo = arquivo;
    }

    String GetTextFromFile() {
        BufferedReader in;

        try {
            in = new BufferedReader(new java.io.FileReader(_arquivo));
            String line = in.readLine();
            while (line != null) {
                line = in.readLine();
                System.out.println(line);
            }

        } catch (Exception e) {
            System.out.println("deu ruim");
        }

        return "";
    }

}
