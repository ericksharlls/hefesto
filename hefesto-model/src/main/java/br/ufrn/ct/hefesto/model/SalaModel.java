package br.ufrn.ct.hefesto.model;

import dev.modulo.abstractmodel.AbstractModel;

public class SalaModel extends AbstractModel {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String descricao;
	private Long idPredio;
	private Long idUnidade;
	private String nomePredio;
	private String nomeUnidade;
	
	
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
	public Long getIdPredio() {
		return idPredio;
	}
	public void setIdPredio(Long idPredio) {
		this.idPredio = idPredio;
	}
	public Long getIdUnidade() {
		return idUnidade;
	}
	public void setIdUnidade(Long idSetor) {
		this.idUnidade = idSetor;
	}
	public String getNomePredio() {
		return nomePredio;
	}
	public void setNomePredio(String nomePredio) {
		this.nomePredio = nomePredio;
	}
	public String getNomeUnidade() {
		return nomeUnidade;
	}
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}
	
}
