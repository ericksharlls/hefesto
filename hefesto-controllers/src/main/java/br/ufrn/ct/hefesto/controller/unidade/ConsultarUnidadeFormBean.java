package br.ufrn.ct.hefesto.controller.unidade;

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
import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.request.unidade.ConsultarUnidade;
import br.ufrn.ct.hefesto.model.request.unidade.EditarUnidade;
import br.ufrn.ct.hefesto.model.request.unidade.ExcluirSetor;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class ConsultarUnidadeFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextService service;
	
	private LazyDataModel<SetorModel> lazySetor;
	private ConsultarUnidade consultarUnidade = new ConsultarUnidade();
	
	private SetorModel unidadeModelSelecionada = new SetorModel();
	
	
	public ConsultarUnidadeFormBean() {
		this.consultarUnidade.setNome("");
	}
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		
		lazySetor = new LazyDataModel<SetorModel>() {
		
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<SetorModel> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				getConsultarUnidade().setStartPosition(first);
				getConsultarUnidade().setMaxResult(pageSize);
				
				List<SetorModel> lista = new ArrayList<SetorModel>(0);
				try {
					lista = (List<SetorModel>) service.consultarPaginado(getConsultarUnidade());
					setRowCount(service.contar(getConsultarUnidade()));
				} catch (NegocioException e) {
					addErrorMessageByCodeMessage(e.getCodeErrorMessage());
					e.printStackTrace();
				} catch (RuntimeException e) {
					addErrorMessageByCodeMessage("1000");
					e.printStackTrace();
				}
				
				return lista;
			}
		};
	}
	
	public String consultar() {
		
		return null;
	}

	public String editar() {
		EditarUnidade editarUnidade = new EditarUnidade();
		editarUnidade.setId(this.unidadeModelSelecionada.getId());
		editarUnidade.setNome(this.unidadeModelSelecionada.getNome());
		editarUnidade.setFazAtendimento(this.unidadeModelSelecionada.getFazAtendimento());
		editarUnidade.setDescricao(this.unidadeModelSelecionada.getDescricao());
		editarUnidade.setIsUnidadeCusto(this.unidadeModelSelecionada.getIsUnidadeCusto());
		
		try {
			service.processar(editarUnidade);
			addInfoMessage("Unidade atualizada com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		} catch (RuntimeException e) {
			addErrorMessageByCodeMessage("1000");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String excluir() {
		ExcluirSetor excluirSetor = new ExcluirSetor();
		excluirSetor.setId(this.unidadeModelSelecionada.getId());
		
		try {
			service.processar(excluirSetor);
			addInfoMessage("Unidade exclu\u00edda com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		} catch (RuntimeException e) {
			addErrorMessageByCodeMessage("1000");
			e.printStackTrace();
		}
		return null;
	}

	public ConsultarUnidade getConsultarUnidade() {
		return consultarUnidade;
	}

	public void setConsultarUnidade(ConsultarUnidade consultarUnidade) {
		this.consultarUnidade = consultarUnidade;
	}

	public LazyDataModel<SetorModel> getLazySetor() {
		return lazySetor;
	}

	public void setLazySetor(LazyDataModel<SetorModel> lazySetor) {
		this.lazySetor = lazySetor;
	}

	public SetorModel getUnidadeModelSelecionada() {
		return unidadeModelSelecionada;
	}

	public void setUnidadeModelSelecionada(SetorModel unidadeModelSelecionada) {
		this.unidadeModelSelecionada = unidadeModelSelecionada;
	}
	
	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
