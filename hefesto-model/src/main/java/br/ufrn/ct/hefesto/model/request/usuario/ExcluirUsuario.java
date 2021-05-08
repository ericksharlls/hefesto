package br.ufrn.ct.hefesto.model.request.usuario;

import dev.modulo.abstractmodel.RequestModel;

public class ExcluirUsuario extends RequestModel{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String login;
	private String senha;
	private Boolean isAtivo;
	
	public ExcluirUsuario() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}
	
}
