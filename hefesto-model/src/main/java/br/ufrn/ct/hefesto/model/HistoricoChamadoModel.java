package br.ufrn.ct.hefesto.model;

import java.util.Calendar;

import dev.modulo.abstractmodel.AbstractModel;

public class HistoricoChamadoModel extends AbstractModel{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Calendar horaRealizacao;
	private String labelHora;
	private Long idOperacao;
	private String labelOperacao;
	private Long idPessoa;
	private String nomePessoa;
	private Long idChamado;
	private String comentario;
	private Boolean temComentario;
	
	public HistoricoChamadoModel() {
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

	public Long getIdOperacao() {
		return idOperacao;
	}

	public void setIdOperacao(Long idOperacao) {
		this.idOperacao = idOperacao;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Long getIdChamado() {
		return idChamado;
	}

	public void setIdChamado(Long idChamado) {
		this.idChamado = idChamado;
	}

	public String getLabelHora() {
		return labelHora;
	}

	public void setLabelHora(String labelHora) {
		this.labelHora = labelHora;
	}

	public String getLabelOperacao() {
		return labelOperacao;
	}

	public void setLabelOperacao(String labelOperacao) {
		this.labelOperacao = labelOperacao;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Boolean getTemComentario() {
		return temComentario;
	}

	public void setTemComentario(Boolean temComentario) {
		this.temComentario = temComentario;
	}
	
}
