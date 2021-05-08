package br.ufrn.ct.hefesto.model;

import dev.modulo.abstractmodel.AbstractModel;

public class HistoricoMudancaStatusChamadoModel extends AbstractModel{

	private static final long serialVersionUID = 1L;
	
	private Long idHistoricoStatus;
	private Long idStatus;
	private Long idHistorico;
	
	public HistoricoMudancaStatusChamadoModel() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdHistoricoStatus() {
		return idHistoricoStatus;
	}

	public void setIdHistoricoStatus(Long idHistoricoStatus) {
		this.idHistoricoStatus = idHistoricoStatus;
	}

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public Long getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
	}
	
}
