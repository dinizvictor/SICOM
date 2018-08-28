package br.cefet.sicom.modelo;

public class Solicitante {
	
	private int idSolicitante;
	private String nome;
	private String setor;
	private String ocupacao;
	private int siape;
	private boolean removido;
	
	public Solicitante() {
		
	}

	public Solicitante(int idSolicitante, String nome, String setor, String ocupacao, int siape, boolean removido) {
		this.idSolicitante = idSolicitante;
		this.nome = nome;
		this.setor = setor;
		this.ocupacao = ocupacao;
		this.siape = siape;
		this.removido = removido;
	}
	
	public int getIdSolicitante() {
		return idSolicitante;
	}
	
	public void setIdSolicitante(int idSolicitante) {
		this.idSolicitante = idSolicitante;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getOcupacao() {
		return ocupacao;
	}
	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}
	
	public int getSiape() {
		return siape;
	}

	public void setSiape(int siape) {
		this.siape = siape;
	}

	public boolean isRemovido() {
		return removido;
	}

	public void setRemovido(boolean removido) {
		this.removido = removido;
	}

	public String toString(){
		
		return this.nome +" - "+ this.setor;
		
	}
	
	

}
