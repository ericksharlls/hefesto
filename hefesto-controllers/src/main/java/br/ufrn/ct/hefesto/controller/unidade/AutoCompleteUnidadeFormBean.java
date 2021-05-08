package br.ufrn.ct.hefesto.controller.unidade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.request.unidade.ListarPorNomeSetor;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class AutoCompleteUnidadeFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextService service;
	
	
	@SuppressWarnings("unchecked")
	public List<SetorModel> completeUnidades(String query) {
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
	
	
	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}

}
