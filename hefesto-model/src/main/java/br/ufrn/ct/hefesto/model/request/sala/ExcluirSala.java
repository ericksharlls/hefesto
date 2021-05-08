package br.ufrn.ct.hefesto.model.request.sala;

import dev.modulo.abstractmodel.RequestModel;

public class ExcluirSala extends RequestModel{

	private static final long serialVersionUID = 1L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
