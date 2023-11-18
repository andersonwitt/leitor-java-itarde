import java.io.BufferedReader;
import java.io.File;
import utils.DateUtils;
import utils.NumberUtils;

public class Leitor {

    private File _arquivo;

    Leitor(File arquivo) {
        _arquivo = arquivo;
    }

    void GetCursoDate(String line) {
        int separador = line.indexOf("  ");

        var caracteres = line.substring(separador, line.length()).trim().substring(0, 8);
        if (DateUtils.IsDate(caracteres)) {
            System.out.println("Data: ");
            System.out.println(caracteres);
        }
    }

    void GetFase(String line) {
        var fase = line.substring(1, 8);
        System.out.println("Nome Fase:");
        System.out.println(fase);

        var quantidadeDisciplina = line.substring(8, 10);
        System.out.println("Quantidade Disciplina:");
        System.out.println(quantidadeDisciplina);

        var quantidadeProfessores = line.substring(10, 12);
        System.out.println("Quantidade Professores:");
        System.out.println(quantidadeProfessores);

    }

    void GetDisciplina(String line) {
        var disciplina = line.substring(1, 7);
        System.out.println("Código Disciplina:");
        System.out.println(disciplina);

        var diaSemana = line.substring(7, 9);
        System.out.println("Semana:");
        System.out.println(diaSemana);

        var quantidadeProfessores = line.substring(9, 11);
        System.out.println("Quantidade de Professores:");
        System.out.println(quantidadeProfessores);
    }

    void GetCursoFase(String line) {
        int separador = line.toLowerCase().indexOf("fase");
        var caracteres = line.substring(separador, line.length());

        System.out.println("Inicio da Fase: ");
        System.out.println(caracteres.substring(0, 7));

        separador = caracteres.substring(7, caracteres.length()).toLowerCase().indexOf("fase");
        caracteres = caracteres.substring(7, caracteres.length());

        if (separador != -1) {
            System.out.println("Final da Fase: ");
            System.out.println(caracteres.substring(0, 7));
        }

        System.out.println("Sequencial: ");
        System.out.println(caracteres.substring(7, 14));

        System.out.println("Versão do layout: ");
        System.out.println(caracteres.substring(14, 17));
    }

    void GetCurso(String line) {
        int separador = line.indexOf("  ");
        System.out.println(line.substring(1, separador));
    }

    void GetProfessor(String linha) {
        int separador = linha.indexOf("  ");
        System.out.println(linha.substring(1, separador));
    }

    void GetTituloProfessor(String linha) {
        int separador = linha.indexOf("  ");

        var caracteres = linha.substring(separador, linha.length()).trim();
        if (NumberUtils.IsNumber(caracteres)) {
            System.out.println("Titulo:");
            System.out.println(caracteres);
        }
    }

    void GetTrailer(String linha) {
        int separador = linha.indexOf("  ");

        var caracteres = linha.substring(separador, linha.length()).trim();
        if (NumberUtils.IsNumber(caracteres)) {
            System.out.println("Titulo:");
            System.out.println(caracteres);
        }
    }

    String GetTextFromFile() {
        BufferedReader in;

        try {
            in = new BufferedReader(new java.io.FileReader(_arquivo));
            String line = in.readLine();
            while (line != null) {
                if (Consts.IsTypeOf(ConstEnum.Curso, line)) {
                    System.out.println("************INICIO DA IMPORTAÇÃO DO CURSO!****************");
                    GetCurso(line);
                    GetCursoDate(line);
                    GetCursoFase(line);
                    System.out.println("(************FIM DA IMPORTAÇÃO DO CURSO!****************");
                } else if (Consts.IsTypeOf(ConstEnum.Fase, line)) {
                    System.out.println("************INICIO DA IMPORTAÇÃO DO FASE!****************");
                    GetFase(line);
                    System.out.println("(************FIM DA IMPORTAÇÃO DO FASE!****************");
                } else if (Consts.IsTypeOf(ConstEnum.Disciplina, line)) {
                    System.out.println("************INICIO DA IMPORTAÇÃO DO DISCIPLINA!****************");
                    GetDisciplina(line);
                    System.out.println("(************FIM DA IMPORTAÇÃO DO DISCIPLINA!****************");
                } else if (Consts.IsTypeOf(ConstEnum.Professor, line)) {
                    System.out.println("************INICIO DA IMPORTAÇÃO DO PROFESSOR!****************");
                    GetProfessor(line);
                    GetTituloProfessor(line);
                    System.out.println("(************FIM DA IMPORTAÇÃO DO PROFESSOR!****************");
                } else if (Consts.IsTypeOf(ConstEnum.Trailer, line)) {
                    System.out.println("************INICIO DA IMPORTAÇÃO DO TRAILER!****************");
                    GetTrailer(line);
                    System.out.println("(************FIM DA IMPORTAÇÃO DO TRAILER!****************");
                }

                line = in.readLine();
            }

        } catch (Exception e) {
            System.out.println("deu ruim");
        }

        return "";
    }

}