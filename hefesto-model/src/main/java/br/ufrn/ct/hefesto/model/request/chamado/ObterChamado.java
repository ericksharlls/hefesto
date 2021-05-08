package br.ufrn.ct.hefesto.model.request.chamado;

import dev.modulo.abstractmodel.RequestModel;

public class ObterChamado extends RequestModel{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	public ObterChamado() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
