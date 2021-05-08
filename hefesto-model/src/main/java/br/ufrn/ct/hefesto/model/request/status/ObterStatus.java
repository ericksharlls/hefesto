package br.ufrn.ct.hefesto.model.request.status;

import dev.modulo.abstractmodel.RequestModel;

public class ObterStatus extends RequestModel {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	public ObterStatus() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
