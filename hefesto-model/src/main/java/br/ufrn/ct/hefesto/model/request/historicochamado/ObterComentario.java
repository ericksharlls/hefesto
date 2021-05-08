package br.ufrn.ct.hefesto.model.request.historicochamado;

import dev.modulo.abstractmodel.RequestModel;

public class ObterComentario extends RequestModel{

	private static final long serialVersionUID = 1L;
	
	private Long idHistoricoChamado;
	
	public ObterComentario() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdHistoricoChamado() {
		return idHistoricoChamado;
	}

	public void setIdHistoricoChamado(Long idHistoricoChamado) {
		this.idHistoricoChamado = idHistoricoChamado;
	}
}
