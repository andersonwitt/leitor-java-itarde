import database.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class Teste {

  public static void main(String[] args) {
    try {
      Connection conexao = ConnectionFactory.getConnection("26.100.201.167", 3306, "SistemaEscolar", "root", "unesc");
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
