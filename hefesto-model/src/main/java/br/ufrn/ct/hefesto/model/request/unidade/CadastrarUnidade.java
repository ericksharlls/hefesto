package br.ufrn.ct.hefesto.model.request.unidade;

import dev.modulo.abstractmodel.RequestModel;

public class CadastrarUnidade extends RequestModel{

	private static final long serialVersionUID = 1L;

	private String nome;
	private String descricao;
	private Boolean isUnidadeCusto;
	
	public CadastrarUnidade() {
		// TODO Auto-generated constructor stub
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

	public Boolean getIsUnidadeCusto() {
		return isUnidadeCusto;
	}

	public void setIsUnidadeCusto(Boolean isUnidadeCusto) {
		this.isUnidadeCusto = isUnidadeCusto;
	}
	
}
