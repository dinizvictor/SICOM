package br.cefet.sicom.modelo;

import java.util.Calendar;

public class Solicitacao {
	
	private int idSolicitacao;
	private Solicitante solicitante;
	private Calendar data;
	private Material material;
	private int qtdFornecida;
	private Usuario usuario;	
	
	public Solicitacao() {
		
	}
	
	public Solicitacao(int idSolicitacao, Solicitante solicitante, Calendar data, Material material,
			int qtdFornecida, Usuario usuario) {
		this.idSolicitacao = idSolicitacao;
		this.solicitante = solicitante;
		this.data = data;
		this.material = material;
		this.qtdFornecida = qtdFornecida;
		this.usuario = usuario;
	}
	public int getIdSolicitacao() {
		return idSolicitacao;
	}
	public void setIdSolicitacao(int idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
	public Solicitante getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public int getQtdFornecida() {
		return qtdFornecida;
	}
	public void setQtdFornecida(int qtdFornecida) {
		this.qtdFornecida = qtdFornecida;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String toString(){
		
		return this.material.getDescricao() + " - " + this.qtdFornecida;
		
	}
	

}
