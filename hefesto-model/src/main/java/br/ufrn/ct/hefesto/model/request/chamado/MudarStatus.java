package br.ufrn.ct.hefesto.model.request.chamado;

import java.util.List;
import java.util.Map;

import dev.modulo.abstractmodel.RequestModel;

public class MudarStatus extends RequestModel{

	private static final long serialVersionUID = 1L;
	private List<Long> idChamado;
	private Map<Long,Long> dados;
	private Long idPessoaLogada;
	
	public MudarStatus() {
		// TODO Auto-generated constructor stub
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdPessoaLogada() {
		return idPessoaLogada;
	}

	public void setIdPessoaLogada(Long idPessoaLogada) {
		this.idPessoaLogada = idPessoaLogada;
	}

	public Map<Long,Long> getDados() {
		return dados;
	}

	public void setDados(Map<Long,Long> dados) {
		this.dados = dados;
	}

	public void setIdChamado(List<Long> idChamado) {
		this.idChamado = idChamado;
	}
	
	public List<Long> getIdChamado(){
		return this.idChamado;
	}
	
}
