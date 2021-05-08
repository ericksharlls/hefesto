package br.ufrn.ct.hefesto.model;

import dev.modulo.abstractmodel.AbstractModel;

public class PredioModel extends AbstractModel{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String descricao;
	
	public PredioModel() {
		
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
	
}
