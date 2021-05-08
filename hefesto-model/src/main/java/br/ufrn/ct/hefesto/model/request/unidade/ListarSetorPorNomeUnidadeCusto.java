package br.ufrn.ct.hefesto.model.request.unidade;

import dev.modulo.abstractmodel.RequestModel;

public class ListarSetorPorNomeUnidadeCusto extends RequestModel {
	
	private static final long serialVersionUID = 1L;
	
	private String nome;

	public ListarSetorPorNomeUnidadeCusto() {
		// TODO Auto-generated constructor stub
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
