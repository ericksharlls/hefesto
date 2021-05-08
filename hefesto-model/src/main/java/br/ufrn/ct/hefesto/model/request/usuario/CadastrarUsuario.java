package br.ufrn.ct.hefesto.model.request.usuario;

import dev.modulo.abstractmodel.RequestModel;

public class CadastrarUsuario extends RequestModel{

	private static final long serialVersionUID = 1L;
	
	private String login;
	private String senha;
	private String senhaConf;
	private Long idPessoa;
	private Long idPapel;
	
	public CadastrarUsuario() {
		// TODO Auto-generated constructor stub
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

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Long getIdPapel() {
		return idPapel;
	}

	public void setIdPapel(Long idPapel) {
		this.idPapel = idPapel;
	}

	public String getSenhaConf() {
		return senhaConf;
	}

	public void setSenhaConf(String senhaConf) {
		this.senhaConf = senhaConf;
	}
	
}
