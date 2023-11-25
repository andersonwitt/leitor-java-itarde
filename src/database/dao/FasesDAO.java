package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.model.Curso;
import database.model.Fase;

public class FasesDAO {

  private String selectAll = "select * from tb_curso";
  private String selectWhere = "select * from tb_curso where id = ?";
  private String insert = "insert into tb_fases(nome, quantidade_disciplinas, quantidade_professores, id_curso) values (?, ?, ?, ?)";

  private PreparedStatement pstSelectAll;
  private PreparedStatement pstSelectWhere;
  private PreparedStatement pstInsert;

  public FasesDAO(Connection conexao) throws SQLException {
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

  public int insert(Fase fase) throws SQLException {
    pstInsert.clearParameters();
    pstInsert.setString(1, fase.getNome());
    pstInsert.setInt(2, fase.getQuantidadeDisciplinas());
    pstInsert.setInt(3, fase.getQuantidadeProfessores());
    pstInsert.setInt(4, fase.getCursoId());
    pstInsert.execute();

    ResultSet r = pstInsert.getGeneratedKeys();
    if (r.next()) {
      return r.getInt(1);
    } else {
      return -9;
    }

  }

}
