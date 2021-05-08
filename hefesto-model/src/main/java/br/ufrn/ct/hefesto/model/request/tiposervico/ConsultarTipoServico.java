package br.ufrn.ct.hefesto.model.request.tiposervico;

import dev.modulo.abstractmodel.PaginatedModel;

public class ConsultarTipoServico extends PaginatedModel {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Long idPessoaLogada;
	
	
	public ConsultarTipoServico() {
		
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public Long getIdPessoaLogada() {
		return idPessoaLogada;
	}


	public void setIdPessoaLogada(Long idPessoaLogada) {
		this.idPessoaLogada = idPessoaLogada;
	}

}
