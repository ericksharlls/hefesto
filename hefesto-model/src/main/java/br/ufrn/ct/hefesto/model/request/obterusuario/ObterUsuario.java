package br.ufrn.ct.hefesto.model.request.obterusuario;

import dev.modulo.abstractmodel.RequestModel;

public class ObterUsuario extends RequestModel{

	private static final long serialVersionUID = 1L;
	
	private String login;
	
	public ObterUsuario() {
		
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
