package br.ufrn.ct.hefesto.model.request.pessoa;

import java.util.List;

import dev.modulo.abstractmodel.RequestModel;

public class ImportarLocalizacoesPessoa extends RequestModel{

	private static final long serialVersionUID = 1L;
	private String clientId;
	private String clientSecret;
	private List<Long> idsPessoaUfrn;
	
	public ImportarLocalizacoesPessoa() {
		
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

	public List<Long> getIdsPessoaUfrn() {
		return idsPessoaUfrn;
	}

	public void setIdsPessoaUfrn(List<Long> idsPessoaUfrn) {
		this.idsPessoaUfrn = idsPessoaUfrn;
	}

}
