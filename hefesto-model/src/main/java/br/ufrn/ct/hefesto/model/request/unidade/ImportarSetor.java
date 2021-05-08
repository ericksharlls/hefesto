package br.ufrn.ct.hefesto.model.request.unidade;

import java.util.List;

import dev.modulo.abstractmodel.RequestModel;

public class ImportarSetor extends RequestModel{
	private static final long serialVersionUID = 1L;
	private Integer idCentro;
	private String clientId;
	private String clientSecret;
	private List<Long> idsSetoresJaCadastrados;
	
	public ImportarSetor() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getIdCentro() {
		return idCentro;
	}
	public void setIdCentro(Integer idCentro) {
		this.idCentro = idCentro;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public List<Long> getIdsSetoresJaCadastrados() {
		return idsSetoresJaCadastrados;
	}

	public void setIdsSetoresJaCadastrados(List<Long> idsSetoresJaCadastrados) {
		this.idsSetoresJaCadastrados = idsSetoresJaCadastrados;
	}
}
