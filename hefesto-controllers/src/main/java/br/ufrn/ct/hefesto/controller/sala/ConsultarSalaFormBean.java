package br.ufrn.ct.hefesto.controller.sala;

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
import br.ufrn.ct.hefesto.model.SalaModel;
import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.request.sala.ConsultarSala;
import br.ufrn.ct.hefesto.model.request.sala.EditarSala;
import br.ufrn.ct.hefesto.model.request.sala.ExcluirSala;
import br.ufrn.ct.hefesto.model.request.unidade.ObterSetor;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class ConsultarSalaFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextService service;

	private LazyDataModel<SalaModel> lazySala;
	private ConsultarSala consultarSala = new ConsultarSala();
	private SetorModel unidade = new SetorModel();
	private SalaModel salaModelSelecionado = new SalaModel();

	
	public ConsultarSalaFormBean() {
		this.consultarSala.setNome("");
		this.consultarSala.setIdPredio(0L);
		this.consultarSala.setIdUnidade(0L);
		//this.unidade.setId(0L);
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		
		setLazySala(new LazyDataModel<SalaModel>() {
		
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<SalaModel> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				getConsultarSala().setStartPosition(first);
				getConsultarSala().setMaxResult(pageSize);
				
				List<SalaModel> lista = new ArrayList<SalaModel>(0);
				try {
					lista = (List<SalaModel>) service.consultarPaginado(getConsultarSala());
					setRowCount(service.contar(getConsultarSala()));
				} catch (NegocioException e) {
					addErrorMessageByCodeMessage(e.getCodeErrorMessage());
					e.printStackTrace();
				}
				
				return lista;
			}
		});
	}
	
	public String consultar() throws NegocioException {
		service.consultarPaginado(getConsultarSala());
		return null;
	}
	
	public String editar() {
		EditarSala editarSala = new EditarSala();
		
		editarSala.setId(this.salaModelSelecionado.getId());
		editarSala.setNome(this.salaModelSelecionado.getNome());
		editarSala.setDescricao(this.salaModelSelecionado.getDescricao());
		editarSala.setIdPredio(this.salaModelSelecionado.getIdPredio());
		editarSala.setIdUnidade(this.getUnidade().getId());
		
		try {
			service.processar(editarSala);
			addInfoMessage("Sala atualizada com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public String excluir() {
		ExcluirSala excluirSala = new ExcluirSala();
		excluirSala.setId(this.salaModelSelecionado.getId());
		try {
			service.processar(excluirSala);
			addInfoMessage("Sala exclu\u00edda com sucesso.");
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
	public ConsultarSala getConsultarSala() {
		return consultarSala;
	}
	public void setConsultarSala(ConsultarSala consultarSala) {
		this.consultarSala = consultarSala;
	}
	public LazyDataModel<SalaModel> getLazySala() {
		return lazySala;
	}
	public void setLazySala(LazyDataModel<SalaModel> lazySala) {
		this.lazySala = lazySala;
	}
	
	public SetorModel getUnidade() {
		return unidade;
	}

	public void setUnidade(SetorModel unidade) {
		this.unidade = unidade;
	}

	public SalaModel getSalaModelSelecionado() {
		return salaModelSelecionado;
	}
	public void setSalaModelSelecionado(SalaModel salaModelSelecionado) {
		ObterSetor obterUnidade = new ObterSetor();
		obterUnidade.setId(salaModelSelecionado.getIdUnidade());
		this.salaModelSelecionado = salaModelSelecionado;
		
		try {
			
			setUnidade((SetorModel) service.obter(obterUnidade));
		
		} catch (NegocioException e) {
		
			e.printStackTrace();
		}
	}
	

}
