package br.ufrn.ct.hefesto.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="atendimento")
public class Atendimento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_atendimento")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_chamado")
	private Chamado chamado;
	
	public Atendimento() {
		// TODO Auto-generated constructor stub
	}

	

	public Pessoa getPessoa() {
		return pessoa;
	}



	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}



	public Chamado getChamado() {
		return chamado;
	}



	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
