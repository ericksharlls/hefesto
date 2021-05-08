package br.ufrn.ct.hefesto.model.request.sala;

import dev.modulo.abstractmodel.RequestModel;

public class CadastrarSala extends RequestModel {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String descricao;
	private Long idPredio;
	private Long idUnidade;
	
	public CadastrarSala() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdPredio() {
		return idPredio;
	}

	public void setIdPredio(Long idPredio) {
		this.idPredio = idPredio;
	}

	public Long getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}
	
	

}
