package br.cefet.sicom.modelo;

public class Usuario {
		
		private int idUsuario;
		private String nome;
		private int siape;
		private String senha;
		private TipoUsuario tipo;
		
		public Usuario(){
			
		}
		
		public Usuario(int idUsuario, String nome, int siape, String senha, TipoUsuario tipo) {
			
			this.idUsuario = idUsuario;
			this.nome = nome;
			this.siape = siape;
			this.senha = senha;
			this.tipo = tipo;
			
		}

		public int getIdUsuario() {
			return idUsuario;
		}
		public void setIdUsuario(int idUsuario) {
			this.idUsuario = idUsuario;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public int getSiape() {
			return siape;
		}
		public void setSiape(int siape) {
			this.siape = siape;
		}
		public String getSenha() {
			return senha;
		}
		public void setSenha(String senha) {
			this.senha = senha;
		}
		public TipoUsuario getTipo() {
			return tipo;
		}
		public void setTipo(TipoUsuario tipo) {
			this.tipo = tipo;
		}
		
		public String toString(){
			
			return this.nome;
			
		}
		
		

}
