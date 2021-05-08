package br.ufrn.ct.hefesto.model.request.obterpessoa;

import dev.modulo.abstractmodel.RequestModel;

public class ObterPessoa extends RequestModel {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	public ObterPessoa() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
