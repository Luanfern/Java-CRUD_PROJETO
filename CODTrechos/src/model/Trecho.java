package model;

public class Trecho {
	
	private int id;
	private String titulo;
	private String linguagem;
	private String conteudo;
	
	public String getConteudo() {
		return conteudo;
	}
	
	
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	
	public String getTitulo() {
		return titulo;
	}
	
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLinguagem() {
		return linguagem;
	}


	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

}
