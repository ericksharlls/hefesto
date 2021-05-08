package br.ufrn.ct.hefesto.controller.tiposervico;

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
import br.ufrn.ct.hefesto.controller.RecuperaUsuarioLogado;
import br.ufrn.ct.hefesto.model.TipoServicoModel;
import br.ufrn.ct.hefesto.model.request.tiposervico.ConsultarTipoServico;
import br.ufrn.ct.hefesto.model.request.tiposervico.EditarTipoServico;
import br.ufrn.ct.hefesto.model.request.tiposervico.ExcluirTipoServico;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class ConsultarTipoServicoFormBean extends AbstractFormBeanHefesto {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextService service;
	
	@Autowired
	private RecuperaUsuarioLogado recuperaUsuarioLogado;
	
	private LazyDataModel<TipoServicoModel> lazyTipoServico;
	
	private ConsultarTipoServico consultarTipoServico = new ConsultarTipoServico();
	
	private TipoServicoModel tipoServicoModelSelecionado = new TipoServicoModel();
	
	public ConsultarTipoServicoFormBean() {
		this.consultarTipoServico.setNome("");
		
	}
	
	@PostConstruct
	public void init() {
		this.consultarTipoServico.setIdPessoaLogada(this.recuperaUsuarioLogado.getIdPessoa());
		lazyTipoServico = new LazyDataModel<TipoServicoModel>() {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unchecked")
			@Override
			public List<TipoServicoModel> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				
				getConsultarTipoServico().setStartPosition(first);
				getConsultarTipoServico().setMaxResult(pageSize);
				
				List<TipoServicoModel> lista = new ArrayList<TipoServicoModel>(0);
				try {
					lista = (List<TipoServicoModel>) service.consultarPaginado(getConsultarTipoServico());
					setRowCount(service.contar(getConsultarTipoServico()));
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
	
	public String editar() {
		EditarTipoServico editarTipoServico = new EditarTipoServico();
		editarTipoServico.setId(this.tipoServicoModelSelecionado.getId());
		editarTipoServico.setNome(this.tipoServicoModelSelecionado.getNome());
		editarTipoServico.setDescricao(this.tipoServicoModelSelecionado.getDescricao());
		editarTipoServico.setIdTipoServicoPai(this.tipoServicoModelSelecionado.getIdTipoServicoPai());
		
		try {
			service.processar(editarTipoServico);
			addInfoMessage("Tipo de Servi\u00e7o atualizado com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String excluir() {
		ExcluirTipoServico excluirTipoServico = new ExcluirTipoServico();
		excluirTipoServico.setId(this.tipoServicoModelSelecionado.getId());
		try {
			service.processar(excluirTipoServico);
			addInfoMessage("Tipo de Servi\u00e7o exclu\u00eddo com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		return null;
	}

	public LazyDataModel<TipoServicoModel> getLazyTipoServico() {
		return lazyTipoServico;
	}

	public void setLazyTipoServico(LazyDataModel<TipoServicoModel> lazyTipoServico) {
		this.lazyTipoServico = lazyTipoServico;
	}

	public ConsultarTipoServico getConsultarTipoServico() {
		return consultarTipoServico;
	}

	public void setConsultarTipoServico(ConsultarTipoServico consultarTipoServico) {
		this.consultarTipoServico = consultarTipoServico;
	}

	public TipoServicoModel getTipoServicoModelSelecionado() {
		return tipoServicoModelSelecionado;
	}

	public void setTipoServicoModelSelecionado(TipoServicoModel tipoServicoModelSelecionado) {
		this.tipoServicoModelSelecionado = tipoServicoModelSelecionado;
	}

	public RecuperaUsuarioLogado getRecuperaUsuarioLogado() {
		return recuperaUsuarioLogado;
	}

	public void setRecuperaUsuarioLogado(RecuperaUsuarioLogado recuperaUsuarioLogado) {
		this.recuperaUsuarioLogado = recuperaUsuarioLogado;
	}

	@Override
	protected String retornaOperacaoAtual() {
		return null;
	}

}
