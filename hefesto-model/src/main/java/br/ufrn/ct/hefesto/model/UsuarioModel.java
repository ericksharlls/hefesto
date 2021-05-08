package br.ufrn.ct.hefesto.model;

import java.util.HashSet;
import java.util.Set;

import dev.modulo.abstractmodel.AbstractModel;

public class UsuarioModel extends AbstractModel {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String login;
	private String senha;
	private Boolean isAtivo;
	private Long idFuncionario;
	private Long idPessoa;
	private String nomePessoa;
	private String numDocumento;
	private Long idPapelusuario;
	private String papelUsuario;
	private Set<PapelModel> papeis = new HashSet<PapelModel>(0);
	
	public UsuarioModel() {
		
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

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
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

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public Long getIdPapelusuario() {
		return idPapelusuario;
	}

	public void setIdPapelusuario(Long idPapelusuario) {
		this.idPapelusuario = idPapelusuario;
	}

	public String getPapelUsuario() {
		return papelUsuario;
	}

	public void setPapelUsuario(String papelUsuario) {
		this.papelUsuario = papelUsuario;
	}

	public Set<PapelModel> getPapeis() {
		return papeis;
	}

	public void setPapeis(Set<PapelModel> papeis) {
		this.papeis = papeis;
	}
	
}
