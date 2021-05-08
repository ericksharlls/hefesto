package br.ufrn.ct.hefesto.model;

import dev.modulo.abstractmodel.AbstractModel;

public class SetorModel extends AbstractModel{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String codigo;
	private Boolean fazAtendimento;
	private Boolean isUnidadeCusto;
	private String descricao;
	private String sigla;
	private Long idPredio;
	private String nomePredio;
	private Long idSetorUfrn;
	
	public SetorModel() {

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getFazAtendimento() {
		return fazAtendimento;
	}

	public void setFazAtendimento(Boolean fazAtendimento) {
		this.fazAtendimento = fazAtendimento;
	}

	public Boolean getIsUnidadeCusto() {
		return isUnidadeCusto;
	}

	public void setIsUnidadeCusto(Boolean isUnidadeCusto) {
		this.isUnidadeCusto = isUnidadeCusto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Long getIdPredio() {
		return idPredio;
	}

	public void setIdPredio(Long idPredio) {
		this.idPredio = idPredio;
	}

	public String getNomePredio() {
		return nomePredio;
	}

	public void setNomePredio(String nomePredio) {
		this.nomePredio = nomePredio;
	}

	public Long getIdSetorUfrn() {
		return idSetorUfrn;
	}

	public void setIdSetorUfrn(Long idSetorUfrn) {
		this.idSetorUfrn = idSetorUfrn;
	}

}
