package br.ufrn.ct.hefesto.controller.sala;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.request.sala.CadastrarSala;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class CadastrarSalaFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextService service;
	
	private CadastrarSala cadastrarSala = new CadastrarSala();
	private SetorModel unidade = new SetorModel();
	
	public CadastrarSalaFormBean() {
		
	}
	
	public String cadastrar() {
		try {
			if (this.getUnidade() != null) {
				this.getCadastrarSala().setIdUnidade(this.getUnidade().getId());
			}
			service.processar(cadastrarSala);
			this.unidade = new SetorModel();
			this.cadastrarSala = new CadastrarSala();
			
			addInfoMessage("Sala cadastrada com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		
		return null;	
	}
	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}


	public CadastrarSala getCadastrarSala() {
		return this.cadastrarSala;
	}

	public void setCadastrarSala(CadastrarSala cadastrarSala) {
		this.cadastrarSala = cadastrarSala;
	}
	
	public SetorModel getUnidade() {
		return this.unidade;
	}
	
	public void setUnidade(SetorModel unidade) {
		this.unidade = unidade;
	}

}
