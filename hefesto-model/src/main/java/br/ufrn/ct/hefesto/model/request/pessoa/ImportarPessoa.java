package br.ufrn.ct.hefesto.model.request.pessoa;

import java.util.List;

import br.ufrn.ct.hefesto.model.SetorModel;
import dev.modulo.abstractmodel.RequestModel;

public class ImportarPessoa extends RequestModel{
	private static final long serialVersionUID = 1L;
	private List<Long> idsUnidades;
	private List<Long> idsSetores;
	private List<SetorModel> setores;
	private String clientId;
	private String clientSecret;
	private List<Long> idsPessoasJaCadastrados;
	
	public ImportarPessoa() {
		// TODO Auto-generated constructor stub
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

	public List<Long> getIdsUnidades() {
		return idsUnidades;
	}

	public void setIdsUnidades(List<Long> idsUnidades) {
		this.idsUnidades = idsUnidades;
	}

	public List<Long> getIdsSetores() {
		return idsSetores;
	}

	public void setIdsSetores(List<Long> idsSetores) {
		this.idsSetores = idsSetores;
	}

	public List<SetorModel> getSetores() {
		return setores;
	}

	public void setSetores(List<SetorModel> setores) {
		this.setores = setores;
	}

	public List<Long> getIdsPessoasJaCadastrados() {
		return idsPessoasJaCadastrados;
	}

	public void setIdsPessoasJaCadastrados(List<Long> idsPessoasJaCadastrados) {
		this.idsPessoasJaCadastrados = idsPessoasJaCadastrados;
	}
	
}
