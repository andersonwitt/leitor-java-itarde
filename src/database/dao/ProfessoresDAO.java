package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import database.model.Curso;
import database.model.Professor;

public class ProfessoresDAO {
  private String selectWhere = "select * from tb_professores where nome = ?";
  private String selectAll = "select * from tb_professores";
  private String insert = "insert into tb_professores(nome, titulo_docente, id_curso) values (?, ?, ?)";

  private PreparedStatement pstSelectAll;
  private PreparedStatement pstSelectWhere;
  private PreparedStatement pstInsert;

  public ProfessoresDAO(Connection conexao) throws SQLException {
    pstSelectAll = conexao.prepareStatement(selectAll);
    pstSelectWhere = conexao.prepareStatement(selectWhere);
    pstInsert = conexao.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
  }

  public ArrayList<Professor> selectAll() throws SQLException {

    ArrayList<Professor> listaProfessor = new ArrayList<Professor>();

    ResultSet resultado = pstSelectAll.executeQuery();
    while (resultado.next()) {

      Professor p = new Professor();

      p.setId(resultado.getInt("id"));
      p.setNome(resultado.getString("nome"));
      p.setTituloID(resultado.getInt("titulo_docente"));
      p.setTitulo(Integer.toString(resultado.getInt("titulo_docente")));

      listaProfessor.add(p);
    }

    return listaProfessor;
  }

  public int insert(Professor professor) throws SQLException {
    if (NomeDeveSerUnico(professor.getNome())) {
      JOptionPane.showMessageDialog(null, "O professor " + professor.getNome() + " já existe no banco de dados.");
      return -1;
    }

    pstInsert.clearParameters();
    pstInsert.setString(1, professor.getNome());
    pstInsert.setInt(2, professor.getTituloId());
    pstInsert.setInt(3, professor.getCursoId());
    pstInsert.execute();

    ResultSet r = pstInsert.getGeneratedKeys();
    if (r.next()) {
      return r.getInt(1);
    } else {
      return -9;
    }
  }

  private boolean NomeDeveSerUnico(String nome) throws SQLException {
    pstSelectWhere.clearParameters();
    pstSelectWhere.setString(1, nome.trim().toUpperCase());
    ResultSet resultado = pstSelectWhere.executeQuery();
    if (resultado.next()) {
      String nomeBanco = resultado.getString("nome").trim().toUpperCase();
      return nomeBanco.equals(nome.trim().toUpperCase());
    }

    return false;
  }

}
