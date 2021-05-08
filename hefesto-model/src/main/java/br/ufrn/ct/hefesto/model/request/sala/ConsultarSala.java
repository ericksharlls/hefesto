package br.ufrn.ct.hefesto.model.request.sala;

import dev.modulo.abstractmodel.PaginatedModel;

public class ConsultarSala extends PaginatedModel {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Long idPredio;
	private Long idUnidade;
	
	public ConsultarSala() {
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
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
