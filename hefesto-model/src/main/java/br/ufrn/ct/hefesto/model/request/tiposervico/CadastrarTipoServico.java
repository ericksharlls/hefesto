package br.ufrn.ct.hefesto.model.request.tiposervico;

import dev.modulo.abstractmodel.RequestModel;

public class CadastrarTipoServico extends RequestModel{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String descricao;
	private Long idTipoServicoPai;
	private Long idPessoaLogada;
	
	
	public CadastrarTipoServico() {
		
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

	public Long getIdPessoaLogada() {
		return idPessoaLogada;
	}

	public void setIdPessoaLogada(Long idPessoaLogada) {
		this.idPessoaLogada = idPessoaLogada;
	}

	public Long getIdTipoServicoPai() {
		return idTipoServicoPai;
	}

	public void setIdTipoServicoPai(Long idTipoServicoPai) {
		this.idTipoServicoPai = idTipoServicoPai;
	}
	
}
