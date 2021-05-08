package br.ufrn.ct.hefesto.model.request.tiposervico;

import dev.modulo.abstractmodel.RequestModel;

public class ListarTiposServicoPais extends RequestModel{
	private static final long serialVersionUID = 1L;
	
	private Long idPessoaLogada;
	
	public ListarTiposServicoPais() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdPessoaLogada() {
		return idPessoaLogada;
	}

	public void setIdPessoaLogada(Long idPessoaLogada) {
		this.idPessoaLogada = idPessoaLogada;
	}
	
}
