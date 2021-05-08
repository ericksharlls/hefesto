package br.ufrn.ct.hefesto.model.request.chamado;

import dev.modulo.abstractmodel.RequestModel;

public class FinalizarChamado extends RequestModel{

	private static final long serialVersionUID = 1L;
	
	private Long idChamado;
	private String observacoes;
	private Float valor;
	private Long idPessoaLogada;
	
	public FinalizarChamado() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdChamado() {
		return idChamado;
	}

	public void setIdChamado(Long idChamado) {
		this.idChamado = idChamado;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Long getIdPessoaLogada() {
		return idPessoaLogada;
	}

	public void setIdPessoaLogada(Long idPessoaLogada) {
		this.idPessoaLogada = idPessoaLogada;
	}
	
}
