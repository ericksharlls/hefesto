package br.ufrn.ct.hefesto.model.request.chamado;

import java.util.List;

import dev.modulo.abstractmodel.RequestModel;

public class CadastrarChamado extends RequestModel{

	private static final long serialVersionUID = 1L;
	
	private String descricao;
	private String materiais;
	private String observacoes;
	private Long idSala;
	private Long idSetor;
	private Long idTipoServico;
	private Long idSolicitante;
	private Long idPessoaLogada;
	
	private List<Long> idsFuncionarios;
	
	public CadastrarChamado() {
		// TODO Auto-generated constructor stub
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMateriais() {
		return materiais;
	}

	public void setMateriais(String materiais) {
		this.materiais = materiais;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Long getIdSala() {
		return idSala;
	}

	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}

	public Long getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(Long idSetor) {
		this.idSetor = idSetor;
	}

	public Long getIdTipoServico() {
		return idTipoServico;
	}

	public void setIdTipoServico(Long idTipoServico) {
		this.idTipoServico = idTipoServico;
	}

	public Long getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(Long idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	public Long getIdPessoaLogada() {
		return idPessoaLogada;
	}

	public void setIdPessoaLogada(Long idPessoaLogada) {
		this.idPessoaLogada = idPessoaLogada;
	}

	public List<Long> getIdsFuncionarios() {
		return idsFuncionarios;
	}

	public void setIdsFuncionarios(List<Long> idsFuncionarios) {
		this.idsFuncionarios = idsFuncionarios;
	}

}
