import java.io.BufferedReader;
import java.io.File;
import java.util.Date;
import database.model.Curso;
import utils.DateUtils;
import database.model.Professor;
import database.model.Disciplina;

public class Leitor {

  private File _arquivo;

  Leitor(File arquivo) {
    _arquivo = arquivo;
  }

  void GetFase(String line, LeitorResultado resultado) {
    String fase = line.substring(1, 8);
    System.out.println("Nome Fase:");
    System.out.println(fase);

    String quantidadeDisciplina = line.substring(8, 10);
    System.out.println("Quantidade Disciplina:");
    System.out.println(quantidadeDisciplina);

    String quantidadeProfessores = line.substring(10, 12);
    System.out.println("Quantidade Professores:");
    System.out.println(quantidadeProfessores);

    // resultado.Fases.add(new Fase(fase, quantidadeDisciplina,
    // quantidadeProfessores));
  }

  Disciplina GetDisciplina(String line) {
    String codigoDisciplina = line.substring(1, 7);
    String diaSemana = line.substring(7, 9);
    String quantidadeProfessores = line.substring(9, 11);

    Disciplina disciplina = new Disciplina();

    disciplina.setNome(Integer.parseInt(codigoDisciplina));
    disciplina.setCodigoDiaSemana(Integer.parseInt(diaSemana));
    disciplina.setDiaSemana(diaSemana);
    disciplina.setQuantidadeProfessores(Integer.parseInt(quantidadeProfessores));

    return disciplina;
  }

  void GetCurso(String line, LeitorResultado resultado) {
    Curso curso = new Curso();

    int separador = line.indexOf("  ");
    String nome = line.substring(1, separador);

    int separadorFase = line.toLowerCase().indexOf("fase");
    var caracteres = line.substring(separadorFase, line.length());

    System.out.println("Inicio da Fase: ");
    System.out.println(caracteres.substring(0, 7));
    String faseInicial = caracteres.substring(0, 7);
    curso.setVersaoLayout(faseInicial);
    String faseFinal = "";

    separadorFase = caracteres.substring(7, caracteres.length()).toLowerCase().indexOf("fase");
    caracteres = caracteres.substring(7, caracteres.length());

    if (separadorFase != -1) {
      System.out.println("Final da Fase: ");
      System.out.println(caracteres.substring(0, 7));
      faseFinal = caracteres.substring(0, 7);
      curso.setVersaoLayout(faseFinal);
    }
    System.out.println("Sequencial: ");
    System.out.println(caracteres.substring(7, 14));
    curso.setSequencia(Integer.parseInt(caracteres.substring(7, 14)));

    System.out.println("Versão do layout: ");
    String sequencial = caracteres.substring(14, 17);
    curso.setVersaoLayout(sequencial);

    curso.setNome(nome);

    int separadorDate = line.indexOf("  ");

    var caracteresDate = line.substring(separadorDate, line.length()).trim().substring(0, 8);
    Date date = DateUtils.parseDate(caracteresDate);
    if (date != null) {
      curso.setDataProcessamento(date);
    }
    resultado.Cursos.add(curso);
  }

  Professor GetProfessor(String linha) {
    int separador = linha.indexOf("  ");

    String nome = linha.substring(1, separador);
    String tituloId = linha.substring(separador, linha.length()).trim();

    Professor professor = new Professor();

    professor.setNome(nome);
    professor.setTituloID(Integer.parseInt(tituloId));
    professor.setTitulo(tituloId);

    return professor;
  }

  LeitorResultado GetTextFromFile() {
    BufferedReader in;
    LeitorResultado resultado = new LeitorResultado();
    try {
      in = new BufferedReader(new java.io.FileReader(_arquivo));
      String line = in.readLine();
      while (line != null) {
        if (Consts.IsTypeOf(ConstEnum.Curso, line)) {
          GetCurso(line, resultado);
        } else if (Consts.IsTypeOf(ConstEnum.Fase, line)) {
          GetFase(line, resultado);
        } else if (Consts.IsTypeOf(ConstEnum.Disciplina, line)) {
          resultado.Disciplinas.add(GetDisciplina(line));
        } else if (Consts.IsTypeOf(ConstEnum.Professor, line)) {
          resultado.Professores.add(GetProfessor(line));
          // GetTituloProfessor(line)));
        }

        line = in.readLine();
      }

    } catch (Exception e) {
      System.out.println(e.toString());
    }

    return resultado;
  }
}
