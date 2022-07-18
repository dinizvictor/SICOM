package br.cefet.sicom.modelo;

import java.util.Calendar;

public class Entrada {
	
	private int idEntrada;
	private Material material;
	private int qtd;
	private String empenho;
	private String fornecedor;
	private Calendar data;
	
	public Entrada(int idEntrada, Material material, int qtd, String empenho, String fornecedor, Calendar data) {
		this.idEntrada = idEntrada;
		this.material = material;
		this.qtd = qtd;
		this.empenho = empenho;
		this.fornecedor = fornecedor;
		this.data = data;
	}

	public Entrada() {
		super();
	}

	public int getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public String getEmpenho() {
		return empenho;
	}

	public void setEmpenho(String empenho) {
		this.empenho = empenho;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}
	
	
	
	

}
