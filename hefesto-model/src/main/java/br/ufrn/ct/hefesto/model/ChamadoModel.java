package br.ufrn.ct.hefesto.model;

import java.util.List;

import dev.modulo.abstractmodel.AbstractModel;

public class ChamadoModel extends AbstractModel{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String codigo;
	private String descricao;
	private String materiais;
	private String observacoes;
	private Float valorServico;
	private String dataAbertura;
	private String dataConclusao;
	private Long idSetor;
	private String setor;
	private Long idPredio;
	private String predio;
	private Long idTipoServico;
	private Long idTipoServicoPai;
	private String tipoServico;
	private Long idSolicitante;
	private String solicitante;
	private Long idStatus;
	private String status;
	private List<Long> idsFuncionarios;
	
	private String comentario;
	
	
	public ChamadoModel() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Float getValorServico() {
		return valorServico;
	}
	public void setValorServico(Float valorServico) {
		this.valorServico = valorServico;
	}
	public String getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public String getDataConclusao() {
		return dataConclusao;
	}
	public void setDataConclusao(String dataConclusao) {
		this.dataConclusao = dataConclusao;
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

	public Long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public List<Long> getIdsFuncionarios() {
		return idsFuncionarios;
	}
	public void setIdsFuncionarios(List<Long> idsFuncionarios) {
		this.idsFuncionarios = idsFuncionarios;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPredio() {
		return predio;
	}

	public void setPredio(String predio) {
		this.predio = predio;
	}

	public Long getIdPredio() {
		return idPredio;
	}

	public void setIdPredio(Long idPredio) {
		this.idPredio = idPredio;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Long getIdTipoServicoPai() {
		return idTipoServicoPai;
	}

	public void setIdTipoServicoPai(Long idTipoServicoPai) {
		this.idTipoServicoPai = idTipoServicoPai;
	}
	
}
