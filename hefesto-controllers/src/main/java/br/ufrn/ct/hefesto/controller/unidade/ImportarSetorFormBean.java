package br.ufrn.ct.hefesto.controller.unidade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.request.unidade.ImportarSetor;
import br.ufrn.ct.hefesto.model.request.unidade.ListarSetor;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class ImportarSetorFormBean extends AbstractFormBeanHefesto {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextService service;
	private ImportarSetor importar = new ImportarSetor();

	public ImportarSetorFormBean() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public String iniciarImportacao() {

		ListarSetor listarSetor = new ListarSetor();

		try {
			List<SetorModel> setoresJaCadastrados = (List<SetorModel>) this.service.consultar(listarSetor);
			List<Long> idsSetoresJaCadastrados = new ArrayList<Long>(0);

			for (SetorModel setorModel : setoresJaCadastrados) {
				idsSetoresJaCadastrados.add(setorModel.getIdSetorUfrn());
			}

			this.importar.setIdsSetoresJaCadastrados(idsSetoresJaCadastrados);
			this.importar.setIdCentro(Integer.parseInt(getParameterByCodeParameter("doisDigitosCodigoCentro")));
			this.importar.setClientId(getParameterByCodeParameter("clientId"));
			this.importar.setClientSecret(getParameterByCodeParameter("clientSecret"));

			this.service.processar(importar);
			addInfoMessage("Setores importados com sucesso.");
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

	public ContextService getService() {
		return service;
	}

	public void setService(ContextService service) {
		this.service = service;
	}

	public ImportarSetor getImportar() {
		return importar;
	}

	public void setImportar(ImportarSetor importar) {
		this.importar = importar;
	}

}
