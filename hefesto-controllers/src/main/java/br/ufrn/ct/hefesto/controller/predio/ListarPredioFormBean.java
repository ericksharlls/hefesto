package br.ufrn.ct.hefesto.controller.predio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.PredioModel;
import br.ufrn.ct.hefesto.model.request.predio.ListarPredio;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class ListarPredioFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextService service;
	
	@SuppressWarnings("unchecked")
	public List<PredioModel> getPredios() {
		
		ListarPredio listarPredio = new ListarPredio();
		List<PredioModel> predios = new ArrayList<PredioModel>(0);
		
		try {
			predios = (List<PredioModel>) this.service.consultar(listarPredio);
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
		return predios;
	}

	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}
}
