package br.ufrn.ct.hefesto.model.request.unidade;

import dev.modulo.abstractmodel.RequestModel;

public class EditarUnidade extends RequestModel{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private Boolean fazAtendimento;
	private String descricao;
	private Boolean isUnidadeCusto;
	
	public EditarUnidade() {
		// TODO Auto-generated constructor stub
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

	public Boolean getFazAtendimento() {
		return fazAtendimento;
	}

	public void setFazAtendimento(Boolean fazAtendimento) {
		this.fazAtendimento = fazAtendimento;
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
