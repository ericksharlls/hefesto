package br.ufrn.ct.hefesto.controller.tiposervico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.controller.RecuperaUsuarioLogado;
import br.ufrn.ct.hefesto.model.request.tiposervico.CadastrarTipoServico;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class CadastrarTipoServicoFormBean extends AbstractFormBeanHefesto {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextService service;
	@Autowired
	private RecuperaUsuarioLogado recuperaUsuarioLogado;
	
	private CadastrarTipoServico cadastrarTipoServico = new CadastrarTipoServico();
	
	public String cadastrar() {
		try {
			this.getCadastrarTipoServico().setIdPessoaLogada(this.recuperaUsuarioLogado.getIdPessoa());
			
			service.processar(getCadastrarTipoServico());
			this.cadastrarTipoServico = new CadastrarTipoServico();
			addInfoMessage("Tipo de Servi\u00e7o cadastrado com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		return null;
	}

	public CadastrarTipoServico getCadastrarTipoServico() {
		return cadastrarTipoServico;
	}

	public void setCadastrarTipoServico(CadastrarTipoServico cadastrarTipoServico) {
		this.cadastrarTipoServico = cadastrarTipoServico;
	}

	@Override
	protected String retornaOperacaoAtual() {
		return null;
	}
}
