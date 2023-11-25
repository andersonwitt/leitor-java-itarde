package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.model.Curso;
import database.model.Disciplina;

public class DisciplinasDAO {

  private String selectAll = "select * from tb_disciplinas";
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

  public ArrayList<Disciplina> selectAll() throws SQLException {

    ArrayList<Disciplina> listaDisciplina = new ArrayList<Disciplina>();

    ResultSet resultado = pstSelectAll.executeQuery();
    while (resultado.next()) {

      Disciplina c = new Disciplina();

      c.setId(resultado.getString("id"));
      c.setNome(Integer.parseInt(resultado.getString("id")));
      c.setCursoId(resultado.getInt("id_curso"));
      c.setDiaSemana(Integer.toString(resultado.getInt("dia_semana")));
      c.setCodigoDiaSemana(resultado.getInt("dia_semana"));
      c.setQuantidadeProfessores(resultado.getInt("quantidade_professores"));

      listaDisciplina.add(c);
    }

    return listaDisciplina;
  }

  public int insert(Disciplina disciplina) throws SQLException {
    pstInsert.clearParameters();
    pstInsert.setString(1, disciplina.getId());
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

}
