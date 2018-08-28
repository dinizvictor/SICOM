package br.cefet.sicom.modelo;

import java.util.Calendar;

public class Auditoria {
	
	private int idAuditoria;
	private Object objeto;
	private Usuario usuario;
	private String acao;
	private Calendar data;
	
	public Auditoria() {
		super();
	}

	public Auditoria(int idAuditoria, Object objeto, Usuario usuario, String acao, Calendar data) {
		super();
		this.idAuditoria = idAuditoria;
		this.objeto = objeto;
		this.usuario = usuario;
		this.acao = acao;
		this.data = data;
	}

	public int getIdAuditoria() {
		return idAuditoria;
	}

	public void setIdAuditoria(int idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}
	
	
	

}
