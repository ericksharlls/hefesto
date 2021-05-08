package br.ufrn.ct.hefesto;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufrn.ct.hefesto.controller.RecuperaUsuarioLogado;
import dev.modulo.web.AbstractFormBean;

public abstract class AbstractFormBeanHefesto extends AbstractFormBean {

	private static final long serialVersionUID = 1L;
	
	private RecuperaUsuarioLogado recuperaUsuarioLogado;
	
	public String getUsuarioLogado() {
		return this.recuperaUsuarioLogado.getLogin();
	}

	public String getUtimaOperacao() {
		return this.recuperaUsuarioLogado.getUltimaOperacao();
	}
	
	public String getOperacaoAtual() {
		return this.recuperaUsuarioLogado.getOperacaoAtual();
	}

	@Override
	protected String getMessagesPropertiesFilePath() {
		return "br/ufrn/ct/hefesto/messages/ErrorMessages";
	}
	
	@Override
	protected String getParametersPropertiesFilePath() {
		return "br/ufrn/ct/hefesto/parameters/Parameters";
	}

	@Autowired
	public void setRecuperaUsuarioLogado(RecuperaUsuarioLogado recuperaUsuarioLogado) {
		this.recuperaUsuarioLogado = recuperaUsuarioLogado;
	}
	
	protected void redefinirOperacoesAtivas() {
		this.recuperaUsuarioLogado.setUltimaOperacao(this.recuperaUsuarioLogado.getOperacaoAtual());
		this.recuperaUsuarioLogado.setOperacaoAtual(retornaOperacaoAtual());
	}
	
	protected abstract String retornaOperacaoAtual();

}
