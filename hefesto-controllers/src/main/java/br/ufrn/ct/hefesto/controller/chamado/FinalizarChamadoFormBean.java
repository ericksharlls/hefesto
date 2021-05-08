package br.ufrn.ct.hefesto.controller.chamado;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.controller.RecuperaUsuarioLogado;
import br.ufrn.ct.hefesto.model.ChamadoModel;
import br.ufrn.ct.hefesto.model.PessoaModel;
import br.ufrn.ct.hefesto.model.request.chamado.FinalizarChamado;
import br.ufrn.ct.hefesto.model.request.chamado.ObterChamado;
import br.ufrn.ct.hefesto.model.request.obterpessoa.ObterPessoa;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class FinalizarChamadoFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextService service;
	@Autowired
	private RecuperaUsuarioLogado recuperaUsuarioLogado;
	private ChamadoModel chamadoModelSelecionado = new ChamadoModel();
	private List<PessoaModel> funcionarios = new ArrayList<PessoaModel>(0);
	
	public FinalizarChamadoFormBean() {
		
	}
	
	public String finalizar() {
		FinalizarChamado finalizarChamado = new FinalizarChamado();
		finalizarChamado.setIdChamado(this.getChamadoModelSelecionado().getId());
		finalizarChamado.setObservacoes(this.getChamadoModelSelecionado().getObservacoes());
		finalizarChamado.setValor(this.getChamadoModelSelecionado().getValorServico());
		finalizarChamado.setIdPessoaLogada(this.getRecuperaUsuarioLogado().getIdPessoa());
		
		try {
			this.service.processar(finalizarChamado);
			addInfoMessage("Chamado finalizado com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		
		return "/chamado/atendimento/finalizar/";
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
		
		this.getChamadoModelSelecionado().setId(chamado.getId());
		this.getChamadoModelSelecionado().setCodigo(chamado.getCodigo());
		this.getChamadoModelSelecionado().setDescricao(chamado.getDescricao());
		this.getChamadoModelSelecionado().setDataAbertura(chamado.getDataAbertura());
		this.getChamadoModelSelecionado().setDataConclusao(chamado.getDataConclusao());
		this.getChamadoModelSelecionado().setObservacoes(chamado.getObservacoes());
		this.getChamadoModelSelecionado().setMateriais(chamado.getMateriais());
		this.getChamadoModelSelecionado().setIdPredio(chamado.getIdPredio());
		this.getChamadoModelSelecionado().setIdSetor(chamado.getIdSetor());
		this.getChamadoModelSelecionado().setSetor(chamado.getSetor());
		this.getChamadoModelSelecionado().setIdTipoServico(chamado.getIdTipoServico());
		this.getChamadoModelSelecionado().setTipoServico(chamado.getTipoServico());
		this.getChamadoModelSelecionado().setIdSolicitante(chamado.getIdSolicitante());
		this.getChamadoModelSelecionado().setSolicitante(chamado.getSolicitante());
		this.getChamadoModelSelecionado().setIdStatus(chamado.getIdStatus());
		this.getChamadoModelSelecionado().setStatus(chamado.getStatus());
		this.getChamadoModelSelecionado().setPredio(chamado.getPredio());
		this.getChamadoModelSelecionado().setIdsFuncionarios(chamado.getIdsFuncionarios());
		this.getChamadoModelSelecionado().setValorServico(chamado.getValorServico() == null ? 0F : chamado.getValorServico());
		
		ObterPessoa obterPessoa = new ObterPessoa();
		
		for (Long idFunc : this.getChamadoModelSelecionado().getIdsFuncionarios()) {
			obterPessoa.setId(idFunc);
			PessoaModel func = new PessoaModel();
			
			try {
				func = (PessoaModel) this.service.obter(obterPessoa);
			} catch (NegocioException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			this.getFuncionarios().add(func);
		}
		
		return null;
	}
	
	
	
	public ContextService getService() {
		return service;
	}
	public void setService(ContextService service) {
		this.service = service;
	}
	public RecuperaUsuarioLogado getRecuperaUsuarioLogado() {
		return recuperaUsuarioLogado;
	}
	public void setRecuperaUsuarioLogado(RecuperaUsuarioLogado recuperaUsuarioLogado) {
		this.recuperaUsuarioLogado = recuperaUsuarioLogado;
	}
	public ChamadoModel getChamadoModelSelecionado() {
		return chamadoModelSelecionado;
	}
	public void setChamadoModelSelecionado(ChamadoModel chamadoModelSelecionado) {
		this.chamadoModelSelecionado = chamadoModelSelecionado;
	}

	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PessoaModel> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<PessoaModel> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
