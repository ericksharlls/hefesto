package br.ufrn.ct.hefesto.model.request.usuario;

import java.util.HashSet;
import java.util.Set;

import br.ufrn.ct.hefesto.model.PapelModel;
import dev.modulo.abstractmodel.RequestModel;

public class EditarUsuario extends RequestModel{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String login;
	private String senha;
	private Boolean isAtivo;
	private Long idPessoa;
	private String nomePessoa;
	private String numDocumento;
	private Long idPapelUsuario;
	private String papelUsuario;
	private Set<PapelModel> papeis = new HashSet<PapelModel>(0);
	private String senhaConfirmacao;
	
	public EditarUsuario() {
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

	public Long getIdPapelUsuario() {
		return idPapelUsuario;
	}

	public void setIdPapelUsuario(Long idPapelUsuario) {
		this.idPapelUsuario = idPapelUsuario;
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

	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}

	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}
	
}
