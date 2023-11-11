package database.model;

public class Cursos {
	
	private int id;
	private String nomeCurso;
	private int qtdDisciplinas;
	private int qtdProfessores;

	public Cursos() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public int getQtdDisciplinas() {
		return qtdDisciplinas;
	}

	public void setQtdDisciplinas(int qtdDisciplinas) {
		this.qtdDisciplinas = qtdDisciplinas;
	}

	public int getQtdProfessores() {
		return qtdProfessores;
	}

	public void setQtdProfessores(int qtdProfessores) {
		this.qtdProfessores = qtdProfessores;
	}
	
	
	
}
