package br.ufrn.ct.hefesto.model.request.usuario;

import dev.modulo.abstractmodel.RequestModel;

public class AlterarSenhaUsuario extends RequestModel{

	private static final long serialVersionUID = 1L;

	private Long idPessoaEmEdicao;
	private Long idPessoaLogado;
	private String senha;
	private String senhaConfirmacao;
	
	
	public Long getIdPessoaEmEdicao() {
		return idPessoaEmEdicao;
	}
	public void setIdPessoaEmEdicao(Long idPessoaEmEdicao) {
		this.idPessoaEmEdicao = idPessoaEmEdicao;
	}
	public Long getIdPessoaLogado() {
		return idPessoaLogado;
	}
	public void setIdPessoaLogado(Long idPessoaLogado) {
		this.idPessoaLogado = idPessoaLogado;
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
