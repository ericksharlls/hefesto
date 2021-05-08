package br.ufrn.ct.hefesto.controller.predio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.PredioModel;
import br.ufrn.ct.hefesto.model.request.predio.ConsultarPredio;
import br.ufrn.ct.hefesto.model.request.predio.EditarPredio;
import br.ufrn.ct.hefesto.model.request.predio.ExcluirPredio;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class ConsultarPredioFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContextService service;
	
	private LazyDataModel<PredioModel> lazyPredio;
	
	private ConsultarPredio consultarPredio = new ConsultarPredio();
	
	private PredioModel predioModelSelecionado = new PredioModel();
	
	
	public ConsultarPredioFormBean() {
		this.consultarPredio.setNome("");
	}
	
	
	@PostConstruct
	public void init() {
		lazyPredio = new LazyDataModel<PredioModel>() {
		
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			public List<PredioModel> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				getConsultarPredio().setStartPosition(first);
				getConsultarPredio().setMaxResult(pageSize);
				
				List<PredioModel> lista = new ArrayList<PredioModel>(0);
				try {
					lista = (List<PredioModel>) service.consultarPaginado(getConsultarPredio());
					setRowCount(service.contar(getConsultarPredio()));
				} catch (NegocioException e) {
					addErrorMessageByCodeMessage(e.getCodeErrorMessage());
					e.printStackTrace();
				}
				
				return lista;
			}
		};
	}
	
	public String consultar() {
		
		return null;
	}
	
	public void setarPredio(PredioModel predioModel) {
		this.predioModelSelecionado = predioModel;
	}

	public String editar() {
		EditarPredio editarPredio = new EditarPredio();
		editarPredio.setId(this.predioModelSelecionado.getId());
		editarPredio.setNome(this.predioModelSelecionado.getNome());
		editarPredio.setDescricao(this.predioModelSelecionado.getDescricao());
		
		try {
			service.processar(editarPredio);
			addInfoMessage("Predio atualizado com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String excluir() {
		ExcluirPredio excluirPredio = new ExcluirPredio();
		excluirPredio.setId(this.predioModelSelecionado.getId());
		
		try {
			service.processar(excluirPredio);
			addInfoMessage("Predio exclu\u00eddo com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		return null;
	}

	public ContextService getService() {
		return service;
	}


	public void setService(ContextService service) {
		this.service = service;
	}


	public LazyDataModel<PredioModel> getLazyPredio() {
		return lazyPredio;
	}


	public void setLazyPredio(LazyDataModel<PredioModel> lazyPredio) {
		this.lazyPredio = lazyPredio;
	}


	public ConsultarPredio getConsultarPredio() {
		return consultarPredio;
	}


	public void setConsultarPredio(ConsultarPredio consultarPredio) {
		this.consultarPredio = consultarPredio;
	}


	public PredioModel getPredioModelSelecionado() {
		return predioModelSelecionado;
	}


	public void setPredioModelSelecionado(PredioModel predioModelSelecionado) {
		this.predioModelSelecionado = predioModelSelecionado;
	}


	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
