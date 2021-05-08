package br.ufrn.ct.hefesto.model.request.sala;

import dev.modulo.abstractmodel.RequestModel;

public class ListarSalaPorPredio extends RequestModel {

	private static final long serialVersionUID = 1L;
	
	private Long idPredio;
	
	public ListarSalaPorPredio() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getIdPredio() {
		return idPredio;
	}
	
	public void setIdPredio(Long idPredio) {
		this.idPredio = idPredio;
	}
	
}
