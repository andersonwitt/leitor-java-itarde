import java.io.BufferedReader;
import java.io.File;
import java.util.Date;
import database.model.Curso;
import utils.DateUtils;
import database.model.Professor;
import database.model.Disciplina;
import database.model.Fase;

public class Leitor {

  private File _arquivo;

  Leitor(File arquivo) {
    _arquivo = arquivo;
  }

  Fase GetFase(String line) {
    Fase fase = new Fase();

    String nome = line.substring(1, 8);
    String quantidadeDisciplina = line.substring(8, 10);
    String quantidadeProfessores = line.substring(10, 12);
    fase.setNome(nome);
    fase.setQuantidadeDisciplinas(Integer.parseInt(quantidadeDisciplina));
    fase.setQuantidadeProfessores(Integer.parseInt(quantidadeProfessores));

    return fase;
  }

  Disciplina GetDisciplina(String line) {
    String codigoDisciplina = line.substring(1, 7);
    String diaSemana = line.substring(7, 9);
    String quantidadeProfessores = line.substring(9, 11);
    Integer cod_dia = Integer.parseInt(diaSemana);

    Disciplina disciplina = new Disciplina();
    
    disciplina.setId(codigoDisciplina);
    disciplina.setNome(Integer.parseInt(codigoDisciplina));
    disciplina.setCodigoDiaSemana(Integer.parseInt(diaSemana));
    disciplina.setDiaSemana(cod_dia.toString());
    disciplina.setQuantidadeProfessores(Integer.parseInt(quantidadeProfessores));

    return disciplina;
  }

  void GetCurso(String line, LeitorResultado resultado) {
    Curso curso = new Curso();

    int separador = line.indexOf("  ");
    String nome = line.substring(1, separador);

    int separadorFase = line.toLowerCase().indexOf("fase");
    var caracteres = line.substring(separadorFase, line.length());

    String faseInicial = caracteres.substring(0, 7);
    // curso.setPeriodoInicial(Integer.parseInt(faseInicial.substring(5, 7)));
    curso.setPeriodoInicial(faseInicial);
    String faseFinal = "";

    separadorFase = caracteres.substring(7, caracteres.length()).toLowerCase().indexOf("fase");
    caracteres = caracteres.substring(7, caracteres.length());

    if (separadorFase != -1) {
      faseFinal = caracteres.substring(0, 7);
      // curso.setPeriodoFinal(Integer.parseInt(faseFinal.substring(5, 7)));
      curso.setPeriodoFinal(faseFinal);
    }

    curso.setSequencia(Integer.parseInt(caracteres.substring(7, 14)));

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
    professor.setTitulo(Integer.toString(Integer.parseInt(tituloId)));

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
          resultado.Fases.add(GetFase(line));
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
