import database.ConnectionFactory;
import database.dao.CursosDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Teste {

  public static void main(String[] args) {
    try {
      Connection conexao = ConnectionFactory.getConnection("127.0.0.1", 3306, "SistemaEscolar", "root", "unesc");
      if (conexao != null) {
        System.out.println("CONECTOU !!!!");

        // CursosDAO dao = new CursosDAO(conexao);
        // ArrayList<Cursos> listaCursos = dao.selectAll();
        // for (Cursos c : listaCursos) {
        // System.out.println(c.getNomeCurso());
        // }

        // Cursos c = new Cursos();
        // c.setNomeCurso("DIREITO");
        // c.setQtdDisciplinas(4);
        // c.setQtdProfessores(4);
        // dao.insert(c);

      } else {
        System.out.println("NÃO CONECTOU !!!!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
