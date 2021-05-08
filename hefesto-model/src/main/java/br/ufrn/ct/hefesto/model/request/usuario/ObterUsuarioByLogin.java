package br.ufrn.ct.hefesto.model.request.usuario;

import dev.modulo.abstractmodel.RequestModel;

public class ObterUsuarioByLogin extends RequestModel {

	private static final long serialVersionUID = 1L;
	
	private String login;
	
	public ObterUsuarioByLogin() {
		
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
