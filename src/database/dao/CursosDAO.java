package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import database.model.Curso;

public class CursosDAO {

  private String selectAll = "select * from tb_curso";
  private String selectWhere = "select * from tb_curso where id = ?";
  private String insert = "insert into tb_cursos(nome, data_processamento, periodo_inicial, periodo_final, sequencial, versao_layout) values (?, ?, ?, ?, ?, ?)";

  private PreparedStatement pstSelectAll;
  private PreparedStatement pstSelectWhere;
  private PreparedStatement pstInsert;

  public CursosDAO(Connection conexao) throws SQLException {
    pstSelectAll = conexao.prepareStatement(selectAll);
    pstSelectWhere = conexao.prepareStatement(selectWhere);
    pstInsert = conexao.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
  }

  public ArrayList<Curso> selectAll() throws SQLException {

    ArrayList<Curso> listaCurso = new ArrayList<Curso>();

    ResultSet resultado = pstSelectAll.executeQuery();
    while (resultado.next()) {

      Curso c = new Curso();

      c.setId(resultado.getInt("id"));
      c.setNome(resultado.getString("nome"));
      c.setPeriodoInicial(resultado.getInt("periodo_inicial"));
      c.setPeriodoFinal(resultado.getInt("periodo_final"));
      c.setDataProcessamento(resultado.getDate("data_processamento"));
      c.setSequencia(resultado.getInt("sequencial"));
      c.setVersaoLayout(resultado.getString("versao_layout"));

      listaCurso.add(c);
    }

    return listaCurso;
  }

  public int insert(Curso curso) throws SQLException {
    pstInsert.clearParameters();

    pstInsert.setInt(1, curso.getId());
    pstInsert.setString(2, curso.getNome());
    pstInsert.setInt(3, curso.getPeriodoInicial());
    pstInsert.setInt(4, curso.getPeriodoFinal());
    pstInsert.setString(5, curso.getDataProcessamento().toString());
    pstInsert.setInt(6, curso.getSequencia());
    pstInsert.setString(7, curso.getVersaoLayout());

    pstInsert.execute();

    ResultSet r = pstInsert.getGeneratedKeys();
    if (r.next()) {
      return r.getInt(1);
    } else {
      return -9;
    }

  }

}
