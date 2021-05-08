package br.ufrn.ct.hefesto.controller;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
public class RecuperaUsuarioLogado implements Serializable {

	private static final long serialVersionUID = 1L;
	private String login;
	private String nome;
	private Long idPessoa;
	private String ultimaOperacao;
	private String operacaoAtual;
	
	public RecuperaUsuarioLogado() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.nome = ((UsuarioSistema) principal).getNome();
		this.login = ((UsuarioSistema) principal).getUsername();
		this.idPessoa = ((UsuarioSistema) principal).getIdPessoa();
		this.ultimaOperacao = "";
		this.operacaoAtual = "";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getUltimaOperacao() {
		return ultimaOperacao;
	}

	public void setUltimaOperacao(String ultimaOperacao) {
		this.ultimaOperacao = ultimaOperacao;
	}

	public String getOperacaoAtual() {
		return operacaoAtual;
	}

	public void setOperacaoAtual(String operacaoAtual) {
		this.operacaoAtual = operacaoAtual;
	}

}
