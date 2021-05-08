package br.ufrn.ct.hefesto.model.request.predio;

import dev.modulo.abstractmodel.RequestModel;

public class CadastrarPredio extends RequestModel{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String descricao;
	
	
	public CadastrarPredio() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
