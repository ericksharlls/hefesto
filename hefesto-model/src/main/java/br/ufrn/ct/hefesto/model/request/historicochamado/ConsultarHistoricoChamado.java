package br.ufrn.ct.hefesto.model.request.historicochamado;

import dev.modulo.abstractmodel.RequestModel;

public class ConsultarHistoricoChamado extends RequestModel{

	private static final long serialVersionUID = 1L;
	private Long idChamado;
	
	public ConsultarHistoricoChamado() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdChamado() {
		return idChamado;
	}

	public void setIdChamado(Long idChamado) {
		this.idChamado = idChamado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
