package br.ufrn.ct.hefesto.model.request.usuario;

import java.util.Date;

import dev.modulo.abstractmodel.RequestModel;

public class AtualizarDadosPessoais extends RequestModel {
	
	private static final long serialVersionUID = 1L;
	
	private Long idPessoaLogada;
	private Long idPessoaEmEdicao;
	private String nome;
	private String email;
	private String numeroDocumento;
	private String matricula;
	private String telefone;
	private Date dataNascimento;
	private String senha;
	private String senhaConfirmacao;
	
	public Long getIdPessoaLogada() {
		return idPessoaLogada;
	}
	public void setIdPessoaLogada(Long idPessoaLogada) {
		this.idPessoaLogada = idPessoaLogada;
	}
	public Long getIdPessoaEmEdicao() {
		return idPessoaEmEdicao;
	}
	public void setIdPessoaEmEdicao(Long idPessoaEmEdicao) {
		this.idPessoaEmEdicao = idPessoaEmEdicao;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}
	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao = senhaConfirmacao;
	}
	
	
	
	
}
