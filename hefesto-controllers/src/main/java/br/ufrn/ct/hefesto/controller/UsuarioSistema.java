package br.ufrn.ct.hefesto.controller;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Long idPessoa;
	
	public UsuarioSistema(String nome, Long idPessoa, String username, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, true, true, true, authorities);
		this.nome = nome;
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}
}
