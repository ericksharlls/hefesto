package br.ufrn.ct.hefesto.model.request.tiposervico;

import dev.modulo.abstractmodel.RequestModel;

public class ListarTipoServico extends RequestModel{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Long idPessoaLogada;

	public ListarTipoServico() {
		// TODO Auto-generated constructor stub
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
