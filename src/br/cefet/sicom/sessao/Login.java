package br.cefet.sicom.sessao;
import java.util.Calendar;

import br.cefet.sicom.modelo.Usuario;

public class Login {
	
	private Usuario usuario;
	private Calendar dataHora;
	
	public Login() {
		
	}
	
	public Login(Usuario usuario, Calendar dataHora) {
		this.usuario = usuario;
		this.dataHora = dataHora;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Calendar getDataHora() {
		return dataHora;
	}
	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}
	

}
