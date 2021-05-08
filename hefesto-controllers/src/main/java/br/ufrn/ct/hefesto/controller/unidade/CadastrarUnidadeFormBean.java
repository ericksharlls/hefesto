package br.ufrn.ct.hefesto.controller.unidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.request.unidade.CadastrarUnidade;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class CadastrarUnidadeFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContextService service;
	
	private CadastrarUnidade cadastrarUnidade = new CadastrarUnidade();

	public CadastrarUnidadeFormBean() {
		this.cadastrarUnidade.setIsUnidadeCusto(false);
	}
	
	public String cadastrar() {
		try {
			service.processar(getCadastrarUnidade());
			this.cadastrarUnidade = new CadastrarUnidade();
			addInfoMessage("Unidade cadastrada com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		} catch (RuntimeException e) {
			addErrorMessageByCodeMessage("1000");
			e.printStackTrace();
		}
		return null;
	}
	
	public CadastrarUnidade getCadastrarUnidade() {
		return cadastrarUnidade;
	}

	public void setCadastrarSetor(CadastrarUnidade cadastrarUnidade) {
		this.cadastrarUnidade = cadastrarUnidade;
	}

	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
