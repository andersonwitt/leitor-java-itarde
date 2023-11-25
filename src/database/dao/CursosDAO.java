package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import database.model.Curso;
import utils.DateUtils;

public class CursosDAO {
  private String selectWhere = "select * from tb_cursos where nome = ?";
  private String selectAll = "select * from tb_cursos";
  private String insert = "insert into tb_cursos(nome, periodo_inicial, periodo_final,  data_processamento, sequencial, versao_layout) values (?, ?, ?, ?, ?, ?)";

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

      Curso curso = new Curso();

      curso.setId(resultado.getInt("id"));
      curso.setNome(resultado.getString("nome"));
      curso.setPeriodoInicial(resultado.getString("periodo_inicial"));
      curso.setPeriodoFinal(resultado.getString("periodo_final"));
      curso.setDataProcessamento(DateUtils.getDateFormated(resultado.getDate("data_processamento")));
      curso.setSequencia(resultado.getInt("sequencial"));
      curso.setVersaoLayout(resultado.getString("versao_layout"));

      listaCurso.add(curso);
    }

    return listaCurso;
  }

  public int insert(Curso curso) throws SQLException {
    var cursoExistente = CursoJaExiste(curso);
    if (cursoExistente != -1) {
      JOptionPane.showMessageDialog(null,
          "O curso " + curso.getNome() + " já existe no banco de dados!");
      return cursoExistente;
    }

    pstInsert.clearParameters();
    pstInsert.setString(1, curso.getNome());
    pstInsert.setString(2, curso.getPeriodoInicial());
    pstInsert.setString(3, curso.getPeriodoFinal());
    pstInsert.setString(4, curso.getDataProcessamento().toString());
    pstInsert.setInt(5, curso.getSequencia());
    pstInsert.setString(6, curso.getVersaoLayout());

    pstInsert.execute();

    ResultSet r = pstInsert.getGeneratedKeys();
    if (r.next()) {
      return r.getInt(1);
    } else {
      return -9;
    }
  }
  private int CursoJaExiste(Curso curso) throws SQLException {
    pstSelectWhere.clearParameters();
    pstSelectWhere.setString(1, curso.getNome().trim().toUpperCase());

    ResultSet resultado = pstSelectWhere.executeQuery();
    if (resultado.next()) {
      String nomeBanco = resultado.getString("nome").trim().toUpperCase();
      int sequencialBanco = resultado.getInt("sequencial");
      String versaoLayoutBanco = resultado.getString("versao_layout").trim().toUpperCase();

      if (nomeBanco.equals(curso.getNome().trim().toUpperCase()) &&
          sequencialBanco == curso.getSequencia() &&
          versaoLayoutBanco.equals(curso.getVersaoLayout().trim().toUpperCase())) {
        return resultado.getInt("id");
      }
    }
    return -1;
  }
}
