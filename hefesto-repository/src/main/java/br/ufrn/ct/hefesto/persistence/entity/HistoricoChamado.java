package br.ufrn.ct.hefesto.persistence.entity;

import java.io.Serializable;
import java.util.Calendar;

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
@Table (name = "historico_chamado")
public class HistoricoChamado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_historico_chamado")
	private Long id;
	
	@Column(name = "hora_realizacao")
	private Calendar horaRealizacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_operacao_chamado")
	private OperacaoChamado operacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_chamado")
	private Chamado chamado;
	
	
	public HistoricoChamado() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getHoraRealizacao() {
		return horaRealizacao;
	}

	public void setHoraRealizacao(Calendar horaRealizacao) {
		this.horaRealizacao = horaRealizacao;
	}

	public OperacaoChamado getOperacao() {
		return operacao;
	}

	public void setOperacao(OperacaoChamado operacao) {
		this.operacao = operacao;
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
