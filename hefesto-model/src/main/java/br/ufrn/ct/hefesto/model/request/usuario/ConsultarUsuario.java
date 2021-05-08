package br.ufrn.ct.hefesto.model.request.usuario;

import dev.modulo.abstractmodel.PaginatedModel;

public class ConsultarUsuario extends PaginatedModel{

	private static final long serialVersionUID = 1L;
	
	private String login;
	
	public ConsultarUsuario() {
		// TODO Auto-generated constructor stub
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
