import database.ConnectionFactory;
import database.dao.CursosDAO;
import database.dao.FasesDAO;
import database.model.Curso;
import database.model.Fase;

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
        ArrayList<Curso> listaCursos = cursosDao.selectAll();
        ArrayList<Fase> listaFases = fasesDao.selectAll();

        System.out.printf("%-30s %-30s %-30s %-30s %-30s%n", "nome", "data_processamento", "periodo_inicial",
            "periodo_final",
            "versao_layout");

        for (Curso c : listaCursos) {
          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
          String date = sdf.format(c.getDataProcessamento());
          System.out.printf("%-30s %-30s %-30s %-30s %-30s%n", c.getNome(), date,
              c.getPeriodoInicial(), c.getPeriodoFinal(), c.getVersaoLayout());
        }

        System.out.printf("%-30s %-30s %-30s%n", "Nome", "quantidade_disciplinas", "quantidade_professores");

        for (Fase f : listaFases) {
          System.out.printf("%-30s %-30s %-30s%n", f.getNome(), f.getQuantidadeDisciplinas(),
              f.getQuantidadeProfessores());
        }

      } else {
        System.out.println("NÃO CONECTOU !!!!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
