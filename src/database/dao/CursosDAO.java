package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import database.model.Cursos;

public class CursosDAO {
	
	private String selectAll = "select * from tb_cursos";
	private String selectWhere = "select * from tb_cursos where id = ?";
	private String insert = "insert into tb_cursos(nomeCurso, qtdDisciplinas, qtdProfessores) values (?, ?, ?)";
	
	private PreparedStatement pstSelectAll;
	private PreparedStatement pstSelectWhere;
	private PreparedStatement pstInsert;
	
	public CursosDAO(Connection conexao) throws SQLException {
		pstSelectAll = conexao.prepareStatement(selectAll);
		pstSelectWhere = conexao.prepareStatement(selectWhere);
		pstInsert = conexao.prepareStatement(insert);
	}
	
	public ArrayList<Cursos> selectAll() throws SQLException {
		
		ArrayList<Cursos> listaCursos = new ArrayList<Cursos>();
		
		ResultSet resultado = pstSelectAll.executeQuery();
		while (resultado.next()) {
			Cursos c = new Cursos();
			c.setId(resultado.getInt("id"));
			c.setNomeCurso(resultado.getString("nomeCurso"));
			c.setQtdDisciplinas(resultado.getInt("qtdDisciplinas"));
			c.setQtdProfessores(resultado.getInt("qtdProfessores"));
			listaCursos.add(c);
		}
		
		return listaCursos;
	}
	
	public void insert(Cursos cursos) throws SQLException {
		
		pstInsert.clearParameters();
		pstInsert.setString(1, cursos.getNomeCurso());
		pstInsert.setInt(2, cursos.getQtdDisciplinas());
		pstInsert.setInt(3, cursos.getQtdProfessores());
		pstInsert.execute();
		
	}
	
}























