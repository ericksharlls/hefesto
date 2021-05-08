package br.ufrn.ct.hefesto.persistence.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tipo_pessoa")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "TipoPessoa")
public class TipoPessoa implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_pessoa")
	private Long id;
	
	@Column(name = "nome_tipo_pessoa")
	private String nome;
	
	@Column(name = "descricao_tipo_pessoa")
	private String descricao;
	
	public TipoPessoa() {
		// TODO Auto-generated constructor stub
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
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
