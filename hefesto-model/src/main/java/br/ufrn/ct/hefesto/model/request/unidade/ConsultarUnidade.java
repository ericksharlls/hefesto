package br.ufrn.ct.hefesto.model.request.unidade;

import dev.modulo.abstractmodel.PaginatedModel;

public class ConsultarUnidade extends PaginatedModel {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String codigo;
	private Boolean isUnidadeCusto;
	
	public ConsultarUnidade() {
		// TODO Auto-generated constructor stub
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

	public Boolean getIsUnidadeCusto() {
		return isUnidadeCusto;
	}

	public void setIsUnidadeCusto(Boolean isUnidadeCusto) {
		this.isUnidadeCusto = isUnidadeCusto;
	}

}
