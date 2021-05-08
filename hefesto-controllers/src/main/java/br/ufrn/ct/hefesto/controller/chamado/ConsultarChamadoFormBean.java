package br.ufrn.ct.hefesto.controller.chamado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.controller.RecuperaUsuarioLogado;
import br.ufrn.ct.hefesto.controller.tiposervico.PopularComboTipoServicoFormBean;
import br.ufrn.ct.hefesto.model.ChamadoModel;
import br.ufrn.ct.hefesto.model.PessoaModel;
import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.StatusModel;
import br.ufrn.ct.hefesto.model.TipoServicoModel;
import br.ufrn.ct.hefesto.model.request.chamado.AdicionarComentario;
import br.ufrn.ct.hefesto.model.request.chamado.ConsultarChamado;
import br.ufrn.ct.hefesto.model.request.chamado.EditarChamado;
import br.ufrn.ct.hefesto.model.request.chamado.MudarStatus;
import br.ufrn.ct.hefesto.model.request.chamado.ObterChamado;
import br.ufrn.ct.hefesto.model.request.obterpessoa.ObterPessoa;
import br.ufrn.ct.hefesto.model.request.pessoa.ListarPorNomePessoa;
import br.ufrn.ct.hefesto.model.request.pessoa.ListarPorParametrosPessoa;
import br.ufrn.ct.hefesto.model.request.status.ObterStatus;
import br.ufrn.ct.hefesto.model.request.unidade.ListarPorNomeSetor;
import br.ufrn.ct.hefesto.model.request.unidade.ObterSetor;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class ConsultarChamadoFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContextService service;
	@Autowired
	private RecuperaUsuarioLogado recuperaUsuarioLogado;
	@Autowired
	private PopularComboTipoServicoFormBean popularComboTipoServicoFormBean;
	private ConsultarChamado consultarChamado = new ConsultarChamado();
	private LazyDataModel<ChamadoModel> lazyChamado;
	private ChamadoModel chamadoModelSelecionado = new ChamadoModel();
	private List<PessoaModel> funcionariosSelecionados = new ArrayList<PessoaModel>(0);
	private List<PessoaModel> funcionarios = new ArrayList<PessoaModel>(0);
	private SetorModel setor = new SetorModel();
	private PessoaModel solicitante = new PessoaModel();
	private boolean caminhoConsultar = false;
	private boolean caminhoFinalizar = false;
	
	private List<ChamadoModel> listaChamados = new ArrayList<ChamadoModel>(0);
	
	
	public ConsultarChamadoFormBean() {
		
	}
	
	@PostConstruct
	public void init() {
		this.consultarChamado.setIdPessoaLogada(this.recuperaUsuarioLogado.getIdPessoa());
		lazyChamado = new LazyDataModel<ChamadoModel>() {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<ChamadoModel> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				getConsultarChamado().setStartPosition(first);
				getConsultarChamado().setMaxResult(pageSize);
				if(isCaminhoFinalizar())
					getConsultarChamado().setAtivo(true);
				if(isCaminhoConsultar())
					getConsultarChamado().setAtivo(false);
				
				List<ChamadoModel> lista = new ArrayList<ChamadoModel>(0);
				try {
					lista = retornarChamados(getConsultarChamado());
					setRowCount(contarChamados(getConsultarChamado()));
				} catch (NegocioException e) {
					addErrorMessageByCodeMessage(e.getCodeErrorMessage());
					e.printStackTrace();
				}
				
				return lista;
			}
		};
	}

	public String consultar() {
		getConsultarChamado().setIdSetor(getSetor() != null ? getSetor().getId() : null);
		getConsultarChamado().setIdSolicitante(getSolicitante() != null ? getSolicitante().getId() : null);
		return null;
	}
	
	public void onRowEdit(RowEditEvent event) {
		ChamadoModel chamadoModelSelecionado = (ChamadoModel) event.getObject();
		for (ChamadoModel c : this.listaChamados) {
			if (c.getId().equals(chamadoModelSelecionado.getId())) {
				c.setIdStatus(chamadoModelSelecionado.getIdStatus());
				try {
					c.setStatus(retornaNomeStatus(chamadoModelSelecionado.getIdStatus()));
				} catch (NegocioException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String retornaNomeStatus(Long idStatus) throws NegocioException {
		ObterStatus obterStatus = new ObterStatus();
		obterStatus.setId(idStatus);
		StatusModel model = (StatusModel) this.service.obter(obterStatus);
		return model.getNome();
	}
	
	@SuppressWarnings("unchecked")
	public List<ChamadoModel> retornarChamados (ConsultarChamado consultarChamado) throws NegocioException {
		this.listaChamados = (List<ChamadoModel>) this.service.consultarPaginado(consultarChamado);
		return this.listaChamados;
	}
	
	public Integer contarChamados (ConsultarChamado consultarChamado) throws NegocioException {
		return this.service.contar(consultarChamado);
	}

	public String obter() {
		ObterChamado obterChamado = new ObterChamado();
		obterChamado.setId(this.getChamadoModelSelecionado().getId());
		
		ChamadoModel chamado = new ChamadoModel();
		try {
			chamado = (ChamadoModel) this.service.obter(obterChamado);
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.getChamadoModelSelecionado().setIdTipoServicoPai(chamado.getIdTipoServicoPai());
		this.getChamadoModelSelecionado().setId(chamado.getId());
		this.getChamadoModelSelecionado().setCodigo(chamado.getCodigo());
		this.getChamadoModelSelecionado().setDescricao(chamado.getDescricao());
		this.getChamadoModelSelecionado().setDataAbertura(chamado.getDataAbertura());
		this.getChamadoModelSelecionado().setDataConclusao(chamado.getDataConclusao());
		this.getChamadoModelSelecionado().setObservacoes(chamado.getObservacoes());
		this.getChamadoModelSelecionado().setMateriais(chamado.getMateriais());
		this.getChamadoModelSelecionado().setIdTipoServico(chamado.getIdTipoServico());
		this.getChamadoModelSelecionado().setTipoServico(chamado.getTipoServico());
		this.getChamadoModelSelecionado().setIdSolicitante(chamado.getIdSolicitante());
		this.getChamadoModelSelecionado().setSolicitante(chamado.getSolicitante());
		this.getChamadoModelSelecionado().setIdStatus(chamado.getIdStatus());
		this.getChamadoModelSelecionado().setStatus(chamado.getStatus());
		this.getChamadoModelSelecionado().setIdsFuncionarios(chamado.getIdsFuncionarios());
		
		ObterSetor obterSetor = new ObterSetor();
		obterSetor.setId(chamado.getIdSetor());
		
		try {
			this.setSetor((SetorModel) this.service.obter(obterSetor));
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
		renderFuncionariosSelecionados();
		return null;
	}
	
	public List<TipoServicoModel> getTiposServicoPai() {
		return this.popularComboTipoServicoFormBean.retornaTiposServicoPai();
	}
	
	public List<TipoServicoModel> getTiposServicoFilho() {
		return this.popularComboTipoServicoFormBean.retornaTiposServicoPorIdTipoServicoPai(this.getChamadoModelSelecionado().getIdTipoServicoPai());
	}
	
	public String editar() {
		EditarChamado editarChamado = new EditarChamado();
		
		editarChamado.setIdChamado(this.chamadoModelSelecionado.getId());
		editarChamado.setDescricao(this.chamadoModelSelecionado.getDescricao());
		editarChamado.setMateriais(this.chamadoModelSelecionado.getMateriais());
		editarChamado.setObservacoes(this.chamadoModelSelecionado.getObservacoes());
		editarChamado.setIdPredio(this.getSetor().getIdPredio());
		editarChamado.setIdSetor(this.getSetor().getId());
		editarChamado.setIdTipoServico(this.chamadoModelSelecionado.getIdTipoServico());
		editarChamado.setIdSolicitante(this.chamadoModelSelecionado.getIdSolicitante());
		editarChamado.setIdPessoaLogada(this.recuperaUsuarioLogado.getIdPessoa());
		
		this.chamadoModelSelecionado.getIdsFuncionarios().clear();
		for (PessoaModel pessoaModel : this.getFuncionariosSelecionados()) {
			this.chamadoModelSelecionado.getIdsFuncionarios().add(pessoaModel.getId());
		}
		
		editarChamado.setIdsFuncionarios(this.chamadoModelSelecionado.getIdsFuncionarios());
		
		try {
			service.processar(editarChamado);
			addInfoMessage("Chamado atualizado com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String mudarStatus() {
		MudarStatus mudarStatus = new MudarStatus();
		
		Map<Long,Long> dados = new HashMap<Long, Long>();
		List<Long> idChamado = new ArrayList<Long>();
		for (ChamadoModel model : this.listaChamados) {
			idChamado.add(model.getId());
			dados.put(model.getId(), model.getIdStatus());
		}
		
		mudarStatus.setIdChamado(idChamado);
		mudarStatus.setDados(dados);
		mudarStatus.setIdPessoaLogada(this.recuperaUsuarioLogado.getIdPessoa());
			
		try {
			this.service.processar(mudarStatus);
			addInfoMessage("Alterações de status gravadas com sucesso.");
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String adicionarComentario() {
		AdicionarComentario adicionarComentario = new AdicionarComentario();
		adicionarComentario.setIdChamado(this.chamadoModelSelecionado.getId());
		adicionarComentario.setTextoComentario(this.chamadoModelSelecionado.getComentario());
		adicionarComentario.setIdPessoaLogada(this.recuperaUsuarioLogado.getIdPessoa());
		
		try {
			this.service.processar(adicionarComentario);
			addInfoMessage("Comentário adicionado com sucesso.");
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void renderFuncionariosSelecionados() {
		ObterPessoa obterPessoa = new ObterPessoa();
		for (Long funcionarioSelecionado : this.getChamadoModelSelecionado().getIdsFuncionarios()) {
			PessoaModel model = new PessoaModel();
			obterPessoa.setId(funcionarioSelecionado);
			try {
				model = (PessoaModel) this.service.obter(obterPessoa);
			} catch (NegocioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.funcionariosSelecionados.add(model);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SetorModel> completeSetores(String query) {
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
	
	@SuppressWarnings("unchecked")
	public List<PessoaModel> completePessoas(String query) {
        List<PessoaModel> filteredPessoas = new ArrayList<PessoaModel>();
        ListarPorNomePessoa listarPorNome = new ListarPorNomePessoa();
        listarPorNome.setNome(query);
  
        try {
			filteredPessoas = (List<PessoaModel>) this.service.consultar(listarPorNome);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
         
        return filteredPessoas;
    }
	
	public ContextService getService() {
		return service;
	}

	public void setService(ContextService service) {
		this.service = service;
	}

	public ConsultarChamado getConsultarChamado() {
		return consultarChamado;
	}

	public void setConsultarChamado(ConsultarChamado consultarChamado) {
		this.consultarChamado = consultarChamado;
	}

	public LazyDataModel<ChamadoModel> getLazyChamado() {
		return lazyChamado;
	}

	public void setLazyChamado(LazyDataModel<ChamadoModel> lazyChamado) {
		this.lazyChamado = lazyChamado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ChamadoModel getChamadoModelSelecionado() {
		return chamadoModelSelecionado;
	}

	public void setChamadoModelSelecionado(ChamadoModel chamadoModelSelecionado) {
		this.chamadoModelSelecionado = chamadoModelSelecionado;
	}
	
	public List<PessoaModel> getFuncionariosSelecionados() {
		return funcionariosSelecionados;
	}

	public void setFuncionariosSelecionados(List<PessoaModel> funcionariosSelecionados) {
		this.funcionariosSelecionados = funcionariosSelecionados;
	}
	
	public void setFuncionarios(List<PessoaModel> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	@SuppressWarnings("unchecked")
	public List<PessoaModel> getFuncionarios(){
		ListarPorParametrosPessoa listarPorParametros = new ListarPorParametrosPessoa();
		ObterPessoa obterPessoa = new ObterPessoa();
		obterPessoa.setId(this.recuperaUsuarioLogado.getIdPessoa());
		
		PessoaModel pessoaModel = new PessoaModel();
		try {
			pessoaModel = (PessoaModel) this.service.obter(obterPessoa);
		} catch (NegocioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		listarPorParametros.setIdSetorLocalizacao(pessoaModel.getIdSetorLocalizacao());
		
		try {
			this.funcionarios = (List<PessoaModel>) service.consultar(listarPorParametros);
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return funcionarios;
	}
	
	public void setOperacaoAtual(String operacao) {
		if(operacao.equals("CONSULTAR_CHAMADOS"))
			this.setCaminhoConsultar(true);
		if(operacao.equals("FINALIZAR_CHAMADOS"))
			this.setCaminhoFinalizar(true);
	}

	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCaminhoConsultar() {
		return caminhoConsultar;
	}

	public void setCaminhoConsultar(boolean caminhoConsultar) {
		this.caminhoConsultar = caminhoConsultar;
	}

	public boolean isCaminhoFinalizar() {
		return caminhoFinalizar;
	}

	public void setCaminhoFinalizar(boolean caminhoFinalizar) {
		this.caminhoFinalizar = caminhoFinalizar;
	}

	public SetorModel getSetor() {
		return setor;
	}

	public void setSetor(SetorModel setor) {
		this.setor = setor;
	}

	public PessoaModel getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(PessoaModel solicitante) {
		this.solicitante = solicitante;
	}
	
}
