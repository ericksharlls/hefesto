package br.ufrn.ct.hefesto.model.request.unidade;

import dev.modulo.abstractmodel.RequestModel;

public class ExcluirSetor extends RequestModel{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	public ExcluirSetor() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
