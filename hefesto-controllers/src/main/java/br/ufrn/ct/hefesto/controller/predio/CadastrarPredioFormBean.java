package br.ufrn.ct.hefesto.controller.predio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.request.predio.CadastrarPredio;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class CadastrarPredioFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContextService service;
	
	private CadastrarPredio cadastrarPredio = new CadastrarPredio();

	public String cadastrar() {
		try {
			service.processar(getCadastrarPredio());
			this.cadastrarPredio = new CadastrarPredio();
			addInfoMessage("Predio cadastrado com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public CadastrarPredio getCadastrarPredio() {
		return cadastrarPredio;
	}

	public void setCadastrarPredio(CadastrarPredio cadastrarPredio) {
		this.cadastrarPredio = cadastrarPredio;
	}

	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}
}
