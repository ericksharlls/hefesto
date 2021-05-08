package br.ufrn.ct.hefesto.model;

import dev.modulo.abstractmodel.AbstractModel;

public class AtendimentoModel extends AbstractModel{

	private static final long serialVersionUID = 1L;
	
	private Long idPessoa;
	private Long idChamado;
	
	public AtendimentoModel() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
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
