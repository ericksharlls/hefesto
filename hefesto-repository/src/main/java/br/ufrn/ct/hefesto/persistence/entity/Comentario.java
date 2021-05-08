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
@Table(name = "comentario_chamado")
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comentario_chamado")
	private Long idComentario;
	
	@Column(name = "texto_comentario_chamado")
	private String textoComentario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_historico_chamado")
	private HistoricoChamado historico;
	
	public Comentario() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Long idComentario) {
		this.idComentario = idComentario;
	}

	public String getTextoComentario() {
		return textoComentario;
	}

	public void setTextoComentario(String textoComentario) {
		this.textoComentario = textoComentario;
	}

	public HistoricoChamado getHistorico() {
		return historico;
	}

	public void setHistorico(HistoricoChamado historico) {
		this.historico = historico;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
