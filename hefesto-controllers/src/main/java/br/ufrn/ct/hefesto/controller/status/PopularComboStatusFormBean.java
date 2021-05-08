package br.ufrn.ct.hefesto.controller.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.StatusModel;
import br.ufrn.ct.hefesto.model.request.status.ListarStatus;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;


@Controller
@Scope("view")
public class PopularComboStatusFormBean extends AbstractFormBeanHefesto {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContextService service;
	private List<StatusModel> status = new ArrayList<StatusModel>(0);
	
	
	@SuppressWarnings("unchecked")
	public List<StatusModel> retornaStatus() {
		ListarStatus listarStatus = new ListarStatus();
		try {
			this.status = (List<StatusModel>) this.service.consultar(listarStatus);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	@Override
	protected String retornaOperacaoAtual() {
		return null;
	}
}
