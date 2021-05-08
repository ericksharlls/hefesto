package br.ufrn.ct.hefesto.model.request.predio;

import dev.modulo.abstractmodel.PaginatedModel;

public class ConsultarPredio extends PaginatedModel{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	
	public ConsultarPredio() {
		
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
