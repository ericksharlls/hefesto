package br.ufrn.ct.hefesto.controller.pessoa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.PessoaModel;
import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.request.pessoa.ImportarPessoa;
import br.ufrn.ct.hefesto.model.request.pessoa.ListarPessoa;
import br.ufrn.ct.hefesto.model.request.unidade.ListarUnidade;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class ImportarPessoaFormBean extends AbstractFormBeanHefesto{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContextService service;
	private ImportarPessoa importar = new ImportarPessoa();
	
	public ImportarPessoaFormBean() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public String iniciarImportacao() {
		List<SetorModel> setores = new ArrayList<SetorModel>(0);
		
		ListarUnidade listarPorPredio = new ListarUnidade();
		
		try {
			setores = (List<SetorModel>) service.consultar(listarPorPredio);
		} catch (NegocioException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			addErrorMessageByCodeMessage("1000");
			e.printStackTrace();
		}
		
		ListarPessoa listarPessoa = new ListarPessoa();
		List<PessoaModel> pessoasJaCadastradas = new ArrayList<PessoaModel>();
		try {
			pessoasJaCadastradas = (List<PessoaModel>) this.service.consultar(listarPessoa);
		} catch (NegocioException e1) {
			e1.printStackTrace();
		} catch (RuntimeException e) {
			addErrorMessageByCodeMessage("1000");
			e.printStackTrace();
		}
		
		List<Long> idsPessoasJaCadastrados = new ArrayList<Long>();
		
		for (PessoaModel model : pessoasJaCadastradas) {
			idsPessoasJaCadastrados.add(model.getIdPessoaUfrn());
		}

		this.importar.setIdsPessoasJaCadastrados(idsPessoasJaCadastrados);
		this.importar.setSetores(setores);
		this.importar.setClientId(getParameterByCodeParameter("clientId"));
		this.importar.setClientSecret(getParameterByCodeParameter("clientSecret"));
		
		try {
			this.service.processar(importar);
			addInfoMessage("Pessoas importados com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		} catch (RuntimeException e) {
			addErrorMessageByCodeMessage("1000");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}

	public ContextService getService() {
		return service;
	}

	public void setService(ContextService service) {
		this.service = service;
	}

}
