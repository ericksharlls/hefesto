package br.ufrn.ct.hefesto.controller.pessoa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.PessoaModel;
import br.ufrn.ct.hefesto.model.request.pessoa.ImportarLocalizacoesPessoa;
import br.ufrn.ct.hefesto.model.request.pessoa.ListarPessoa;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class ImportarLocalizacoesPessoaFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;
	@Autowired
	private ContextService service;
	private ImportarLocalizacoesPessoa importar = new ImportarLocalizacoesPessoa();
	
	@SuppressWarnings("unchecked")
	public String iniciarImportacao(){
		
		ListarPessoa listarPessoa = new ListarPessoa();
		List<PessoaModel> pessoasJaCadastradas = new ArrayList<PessoaModel>();
		try {
			pessoasJaCadastradas = (List<PessoaModel>) this.service.consultar(listarPessoa);
		} catch (NegocioException e1) {
			e1.printStackTrace();
		}
		
		List<Long> idsPessoasJaCadastrados = new ArrayList<Long>();
		
		for (PessoaModel model : pessoasJaCadastradas) {
			idsPessoasJaCadastrados.add(model.getIdPessoaUfrn());
		}
		
		this.importar.setIdsPessoaUfrn(idsPessoasJaCadastrados);
		this.importar.setClientId(getParameterByCodeParameter("clientId"));
		this.importar.setClientSecret(getParameterByCodeParameter("clientSecret"));
		
		try {
			this.service.processar(importar);
			addInfoMessage("Localizações importadas com sucesso.");
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

}
