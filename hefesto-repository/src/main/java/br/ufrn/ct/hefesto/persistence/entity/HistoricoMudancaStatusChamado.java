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
@Table(name = "historico_mudanca_status_chamado")
public class HistoricoMudancaStatusChamado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_historico_mudanca_status")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_historico_chamado")
	private HistoricoChamado historico;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_status")
	private Status status;
	
	
	public HistoricoMudancaStatusChamado() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HistoricoChamado getHistorico() {
		return historico;
	}

	public void setHistorico(HistoricoChamado historico) {
		this.historico = historico;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
