import database.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

public class Teste {

	public static void main(String[] args) {
		
		// JFileChooser chooser = new JFileChooser();
		// int retorno = chooser.showOpenDialog(null);
		// if (retorno == JFileChooser.APPROVE_OPTION) {
		// 	String linha = "0CIENCIA DA COMPUTACAO                              20231107FASE-01FASE-020000001001";
		// 	if (linha.substring(0, 1).equals("0")) {
		// 		String curso = linha.substring(1, 50).trim();
		// 		System.out.println(curso);
		// 	}
		// }
		// else {
			
		// }
		
		try {
			Connection conexao = ConnectionFactory.getConnection("localhost", 3306, "sistema", "root", "@Root");
			if (conexao != null) {
				System.out.println("CONECTOU !!!!");
				
				// CursosDAO dao = new CursosDAO(conexao);
				// ArrayList<Cursos> listaCursos = dao.selectAll();
				// for (Cursos c : listaCursos) {
				// 	System.out.println(c.getNomeCurso());
				// }
				
//				Cursos c = new Cursos();
//				c.setNomeCurso("DIREITO");
//				c.setQtdDisciplinas(4);
//				c.setQtdProfessores(4);
//				dao.insert(c);
				
			}
			else {
				System.out.println("NÃO CONECTOU !!!!");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
