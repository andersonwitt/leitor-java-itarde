package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import database.model.Curso;
import database.model.Disciplina;

public class DisciplinasDAO {

  private String selectAll = "select * from tb_curso";
  private String selectWhere = "select * from tb_disciplinas where id = ?";
  private String insert = "insert into tb_disciplinas(id, dia_semana, quantidade_professores, id_curso) values (?, ?, ?, ?)";

  private PreparedStatement pstSelectAll;
  private PreparedStatement pstSelectWhere;
  private PreparedStatement pstInsert;

  public DisciplinasDAO(Connection conexao) throws SQLException {
    pstSelectAll = conexao.prepareStatement(selectAll);
    pstSelectWhere = conexao.prepareStatement(selectWhere);
    pstInsert = conexao.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
  }

  public ArrayList<Curso> selectAll() throws SQLException {

    ArrayList<Curso> listaCurso = new ArrayList<Curso>();

    ResultSet resultado = pstSelectAll.executeQuery();
    while (resultado.next()) {

      Curso c = new Curso();

      int Id = resultado.getInt("id");
      String Nome = resultado.getString("nomeCurso");
      String PeriodoInicial = resultado.getString("qtdDisciplinas");
      String PeriodoFinal = resultado.getString("qtdProfessores");

      listaCurso.add(c);
    }

    return listaCurso;
  }

  public int insert(Disciplina disciplina) throws SQLException {
    if (idDeveSerUnico(Integer.parseInt(disciplina.getId()))) {
      JOptionPane.showMessageDialog(null, "A disciplina " + disciplina.getNome() + " já existe no banco de dados!");
      return Integer.parseInt(disciplina.getId());
    }

    pstInsert.clearParameters();
    pstInsert.setString(1, Integer.toString(Integer.parseInt(disciplina.getId())));
    pstInsert.setInt(2, disciplina.getCodigoDiaSemana());
    pstInsert.setInt(3, disciplina.getQuantidadeProfessores());
    pstInsert.setInt(4, disciplina.getCursoId());
    pstInsert.execute();

    ResultSet r = pstInsert.getGeneratedKeys();
    if (r.next()) {
      return r.getInt(1);
    } else {
      return -9;
    }
  }

  private boolean idDeveSerUnico(int id) throws SQLException {
    pstSelectWhere.clearParameters();
    pstSelectWhere.setInt(1, id);
    ResultSet resultado = pstSelectWhere.executeQuery();
    return resultado.next();
  }
}
