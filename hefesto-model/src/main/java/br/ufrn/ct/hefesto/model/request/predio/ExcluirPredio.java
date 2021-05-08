package br.ufrn.ct.hefesto.model.request.predio;

import dev.modulo.abstractmodel.RequestModel;

public class ExcluirPredio extends RequestModel{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	
	public ExcluirPredio() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
