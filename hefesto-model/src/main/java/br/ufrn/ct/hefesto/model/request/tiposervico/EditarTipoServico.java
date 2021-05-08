package br.ufrn.ct.hefesto.model.request.tiposervico;

import dev.modulo.abstractmodel.RequestModel;

public class EditarTipoServico extends RequestModel{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String descricao;
	private Long idTipoServicoPai;
	
	
	public EditarTipoServico() {
		
	}
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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




	public Long getIdTipoServicoPai() {
		return idTipoServicoPai;
	}




	public void setIdTipoServicoPai(Long idTipoServicoPai) {
		this.idTipoServicoPai = idTipoServicoPai;
	}
}
