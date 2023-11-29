import database.ConnectionFactory;
import database.dao.CursosDAO;
import database.dao.DisciplinasDAO;
import database.dao.FasesDAO;
import database.dao.ProfessoresDAO;
import database.model.Curso;
import database.model.Disciplina;
import database.model.Fase;
import database.model.Professor;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Teste {
  public static void main(String[] args) {
    try {
      Connection conexao = ConnectionFactory.getConnection("26.100.201.167", 3306, "SistemaEscolar", "root", "unesc");
      if (conexao != null) {
        System.out.println("CONECTOU !!!!");

        CursosDAO cursosDao = new CursosDAO(conexao);
        FasesDAO fasesDao = new FasesDAO(conexao);
        DisciplinasDAO disciplinasDao = new DisciplinasDAO(conexao);
        ProfessoresDAO professoresDao = new ProfessoresDAO(conexao);

        ArrayList<Curso> listaCursos = cursosDao.selectAll();
        ArrayList<Fase> listaFases = fasesDao.selectAll();
        ArrayList<Disciplina> listaDisciplinas = disciplinasDao.selectAll();
        ArrayList<Professor> listaProfessores = professoresDao.selectAll();

        System.out.println("tb_cursos\n");
        System.out.printf("%-30s %-30s %-30s %-30s %-30s%n", "nome", "data_processamento", "periodo_inicial",
            "periodo_final",
            "versao_layout");

        for (Curso c : listaCursos) {
          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
          String date = sdf.format(c.getDataProcessamento());
          System.out.printf("%-30s %-30s %-30s %-30s %-30s%n", c.getNome(), date,
              c.getPeriodoInicial(), c.getPeriodoFinal(), c.getVersaoLayout());
        }

        System.out.println("\n\ntb_fases\n");
        System.out.printf("%-30s %-30s %-30s%n", "Nome", "quantidade_disciplinas", "quantidade_professores");

        for (Fase f : listaFases) {
          System.out.printf("%-30s %-30s %-30s%n", f.getNome(), f.getQuantidadeDisciplinas(),
              f.getQuantidadeProfessores());
        }

        System.out.println("\n\ntb_disciplinas\n");
        System.out.printf("%-30s %-30s %-30s%n", "nome", "dia_semana", "quantidade_professores");

        for (Disciplina d : listaDisciplinas) {
          System.out.printf("%-30s %-30s %-30s%n", d.getNome(), d.getDiaSemana(), d.getQuantidadeProfessores());
        }

        System.out.println("\n\ntb_professores\n");
        System.out.printf("%-30s %-30s%n", "nome", "titulo_docente");

        for (Professor p : listaProfessores) {
          System.out.printf("%-30s %-30s%n", p.getNome(), p.getTitulo());
        }

      } else {
        System.out.println("NÃO CONECTOU !!!!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
