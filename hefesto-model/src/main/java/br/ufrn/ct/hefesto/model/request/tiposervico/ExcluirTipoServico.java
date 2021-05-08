package br.ufrn.ct.hefesto.model.request.tiposervico;

import dev.modulo.abstractmodel.RequestModel;

public class ExcluirTipoServico extends RequestModel{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	public ExcluirTipoServico() {
		
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
