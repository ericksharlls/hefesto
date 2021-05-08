package br.ufrn.ct.hefesto.controller.pessoa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.request.pessoa.CadastrarPessoa;
import br.ufrn.ct.hefesto.model.request.unidade.ListarPorNomeSetor;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class CadastrarPessoaFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContextService service;
	
	private CadastrarPessoa cadastrarPessoa = new CadastrarPessoa();
	private SetorModel setorLotacao = new SetorModel();
	private SetorModel setorLocalizacao = new SetorModel();
	
	public String cadastrar() {
		try {
			if(this.getSetorLotacao() == null) {
				this.getCadastrarPessoa().setIdSetorLotacao(0L);
			}else {
				this.getCadastrarPessoa().setIdSetorLotacao(this.getSetorLotacao().getId());
			}
			
			if(this.getSetorLocalizacao() == null) {
				this.getCadastrarPessoa().setIdSetorLocalizacao(0L);
			}else {
				this.getCadastrarPessoa().setIdSetorLocalizacao(this.getSetorLocalizacao().getId());
			}
			service.processar(cadastrarPessoa);
			this.cadastrarPessoa = new CadastrarPessoa();
			this.setorLotacao = new SetorModel();
			this.setorLocalizacao = new SetorModel();
			addInfoMessage("Pessoa cadastrada com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	public ContextService getService() {
		return service;
	}
	
	@SuppressWarnings("unchecked")
	public List<SetorModel> completeSetores(String query) {
        List<SetorModel> filteredSetores = new ArrayList<SetorModel>();
        ListarPorNomeSetor listarPorNome = new ListarPorNomeSetor();
        listarPorNome.setNome(query);
  
        try {
        	filteredSetores = (List<SetorModel>) this.service.consultar(listarPorNome);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
         
        return filteredSetores;
    }

	public void setService(ContextService service) {
		this.service = service;
	}

	public CadastrarPessoa getCadastrarPessoa() {
		return cadastrarPessoa;
	}

	public void setCadastrarPessoa(CadastrarPessoa cadastrarPessoa) {
		this.cadastrarPessoa = cadastrarPessoa;
	}

	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}

	public SetorModel getSetorLotacao() {
		return setorLotacao;
	}

	public void setSetorLotacao(SetorModel setorLotacao) {
		this.setorLotacao = setorLotacao;
	}

	public SetorModel getSetorLocalizacao() {
		return setorLocalizacao;
	}

	public void setSetorLocalizacao(SetorModel setorLocalizacao) {
		this.setorLocalizacao = setorLocalizacao;
	}
	
}
