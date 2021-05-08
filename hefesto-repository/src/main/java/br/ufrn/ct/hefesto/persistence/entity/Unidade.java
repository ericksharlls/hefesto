package br.ufrn.ct.hefesto.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unidade")
public class Unidade implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_unidade")
	private Long id;
	
	@Column(name = "codigo_unidade")
	private String codigo;
	
	@Column(name = "nome_unidade")
	private String nome;
	
	@Column(name = "descricao_unidade")
	private String descricao;
	
	@Column(name = "sigla_unidade")
	private String sigla;
	
	@Column(name = "faz_atendimento")
	private Boolean fazAtendimento;
	
	@Column(name = "unidade_custo")
	private Boolean unidadeCusto;
	
	@Column(name = "id_unidade_ufrn")
	private Long idUnidadeUfrn;
	
	public Unidade() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Boolean getFazAtendimento() {
		return fazAtendimento;
	}

	public void setFazAtendimento(Boolean fazAtendimento) {
		this.fazAtendimento = fazAtendimento;
	}

	public Boolean getUnidadeCusto() {
		return unidadeCusto;
	}

	public void setUnidadeCusto(Boolean unidadeCusto) {
		this.unidadeCusto = unidadeCusto;
	}

	public Long getIdUnidadeUfrn() {
		return idUnidadeUfrn;
	}

	public void setIdUnidadeUfrn(Long idUnidadeUfrn) {
		this.idUnidadeUfrn = idUnidadeUfrn;
	}

}
