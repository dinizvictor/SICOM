package br.cefet.sicom.modelo;

public class Material {
	
	private int idMaterial;
	private String descricao;
	private String tipoUnid;
	private int saldo;
	private String categoria;
	private String localizacao;
	private boolean removido;
	private int saldoTeto;
	
	public Material() {
	
	}
	
	public Material(int idMaterial, String descricao, String tipoUnid,
			int saldo, String categoria, String localizacao, boolean removido, int tetoSaldo) {
		
		this.idMaterial = idMaterial;
		this.descricao = descricao;
		this.tipoUnid = tipoUnid;
		this.saldo = saldo;
		this.categoria = categoria;
		this.localizacao = localizacao;
		this.removido = removido;
		this.saldoTeto = tetoSaldo;
		
	}
	public int getIdMaterial() {
		return idMaterial;
	}
	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipoUnid() {
		return tipoUnid;
	}
	public void setTipoUnid(String tipoUnid) {
		this.tipoUnid = tipoUnid;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public boolean isRemovido() {
		return removido;
	}

	public void setRemovido(boolean removido) {
		this.removido = removido;
	}

	public int getSaldoTeto() {
		return saldoTeto;
	}

	public void setSaldoTeto(int saldoTeto) {
		this.saldoTeto = saldoTeto;
	}

	public String toString(){
		
		return this.descricao + " - " + this.saldo; 
	
	}
	
	

}
