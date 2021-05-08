package br.ufrn.ct.hefesto.model.request.tiposervico;

import dev.modulo.abstractmodel.RequestModel;

public class ListarTipoServicoByPai extends RequestModel{
	private static final long serialVersionUID = 1L;

	private Long idTipoServicoPai;
	private Long idPessoaLogada;
	
	public ListarTipoServicoByPai() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdTipoServicoPai() {
		return idTipoServicoPai;
	}

	public void setIdTipoServicoPai(Long idTipoServicoPai) {
		this.idTipoServicoPai = idTipoServicoPai;
	}

	public Long getIdPessoaLogada() {
		return idPessoaLogada;
	}

	public void setIdPessoaLogada(Long idPessoaLogada) {
		this.idPessoaLogada = idPessoaLogada;
	}
	
	
}
