package br.ufrn.ct.hefesto.controller.sala;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.SalaModel;
import br.ufrn.ct.hefesto.model.request.sala.ListarSalaPorPredio;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class ListarSalaFormBeanPorPredio extends AbstractFormBeanHefesto {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextService service;
	
	private Long idPredio;
	private List<SalaModel> salas = new ArrayList<SalaModel>(0);
	
	
	@SuppressWarnings("unchecked")
	public void popularSalas() {
		if(getIdPredio() != null && getIdPredio() != 0) {
			ListarSalaPorPredio listarSalaPorPredio = new ListarSalaPorPredio();
			listarSalaPorPredio.setIdPredio(this.idPredio);
			try {
				this.salas = ((List<SalaModel>) this.service.consultar(listarSalaPorPredio));
			} catch (NegocioException e) {
				e.printStackTrace();
			}
		} else {
			this.salas = new ArrayList<SalaModel>(0);
		}
	}

	public Long getIdPredio() {
		return idPredio;
	}

	public void setIdPredio(Long idPredio) {
		this.idPredio = idPredio;
	}

	public List<SalaModel> getSalas() {
		return salas;
	}

	public void setSalas(List<SalaModel> salas) {
		this.salas = salas;
	}
	
	@Override
	protected String retornaOperacaoAtual() {
		return null;
	}

}
