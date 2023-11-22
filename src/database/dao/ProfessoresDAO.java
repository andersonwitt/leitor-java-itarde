package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.model.Curso;

public class ProfessoresDAO {

  private String selectAll = "select * from tb_curso";
  private String selectWhere = "select * from tb_curso where id = ?";
  private String insert = "insert into tb_curso(nomeCurso, qtdDisciplinas, qtdProfessores) values (?, ?, ?)";

  private PreparedStatement pstSelectAll;
  private PreparedStatement pstSelectWhere;
  private PreparedStatement pstInsert;

  public ProfessoresDAO(Connection conexao) throws SQLException {
    pstSelectAll = conexao.prepareStatement(selectAll);
    pstSelectWhere = conexao.prepareStatement(selectWhere);
    pstInsert = conexao.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
  }

  public ArrayList<Curso> selectAll() throws SQLException {

    ArrayList<Curso> listaCurso = new ArrayList<Curso>();

    ResultSet resultado = pstSelectAll.executeQuery();
    while (resultado.next()) {

      int Id = resultado.getInt("id");
      String Nome = resultado.getString("nomeCurso");
      String PeriodoInicial = resultado.getString("qtdDisciplinas");
      String PeriodoFinal = resultado.getString("qtdProfessores");

      Curso c = new Curso(Nome, PeriodoInicial, PeriodoFinal);
      c.setId(Id);
      listaCurso.add(c);
    }

    return listaCurso;
  }

  public int insert(Curso curso) throws SQLException {
    pstInsert.clearParameters();
    pstInsert.setString(1, curso.Nome);
    pstInsert.setString(2, curso.PeriodoInicial);
    pstInsert.setString(3, curso.PeriodoFinal);
    pstInsert.execute();

    ResultSet r = pstInsert.getGeneratedKeys();
    if (r.next()) {
      return r.getInt(1);
    } else {
      return -9;
    }

  }

}
