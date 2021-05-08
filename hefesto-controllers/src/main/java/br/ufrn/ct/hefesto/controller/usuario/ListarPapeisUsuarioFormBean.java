package br.ufrn.ct.hefesto.controller.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.PapelModel;
import br.ufrn.ct.hefesto.model.request.usuario.ListarPapel;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("request")
public class ListarPapeisUsuarioFormBean extends AbstractFormBeanHefesto {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContextService service;
	
	@SuppressWarnings("unchecked")
	public List<PapelModel> getPapeis() {
		List<PapelModel> retorno = new ArrayList<PapelModel>(0);
		ListarPapel listarPapel = new ListarPapel();
		try {
			retorno = (List<PapelModel>) this.service.consultar(listarPapel);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}

}
