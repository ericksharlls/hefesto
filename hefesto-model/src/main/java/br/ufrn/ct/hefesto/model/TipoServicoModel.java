package br.ufrn.ct.hefesto.model;

import dev.modulo.abstractmodel.AbstractModel;

public class TipoServicoModel extends AbstractModel{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String codigo;
	private String descricao;
	private Long idTipoServicoPai;
	private String tipoServicoPai;

	
	public TipoServicoModel() {
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getTipoServicoPai() {
		return tipoServicoPai;
	}


	public void setTipoServicoPai(String tipoServicoPai) {
		this.tipoServicoPai = tipoServicoPai;
	}


	public Long getIdTipoServicoPai() {
		return idTipoServicoPai;
	}


	public void setIdTipoServicoPai(Long idTipoServicoPai) {
		this.idTipoServicoPai = idTipoServicoPai;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
