package br.ufrn.ct.hefesto.model.request.pessoa;

import dev.modulo.abstractmodel.RequestModel;

public class ListarPorNomePessoa extends RequestModel{

	private static final long serialVersionUID = 1L;
	private String nome;
	
	public ListarPorNomePessoa() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
