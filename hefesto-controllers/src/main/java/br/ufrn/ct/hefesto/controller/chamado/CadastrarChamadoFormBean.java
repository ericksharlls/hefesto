package br.ufrn.ct.hefesto.controller.chamado;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.controller.RecuperaUsuarioLogado;
import br.ufrn.ct.hefesto.model.PessoaModel;
import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.request.chamado.CadastrarChamado;
import br.ufrn.ct.hefesto.model.request.obterpessoa.ObterPessoa;
import br.ufrn.ct.hefesto.model.request.pessoa.ListarPorNomePessoa;
import br.ufrn.ct.hefesto.model.request.pessoa.ListarPorParametrosPessoa;
import br.ufrn.ct.hefesto.model.request.unidade.ListarSetorPorNomeUnidadeCusto;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class CadastrarChamadoFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;
	
	RecuperaUsuarioLogado vv;
	
	@Autowired
	private ContextService service;
	@Autowired
	private RecuperaUsuarioLogado recuperaUsuarioLogado;
	private CadastrarChamado cadastrarChamado = new CadastrarChamado();
	private List<PessoaModel> funcionarios = new ArrayList<PessoaModel>(0);
	private SetorModel setor = new SetorModel();
	private PessoaModel solicitante = new PessoaModel();
	private List<PessoaModel> funcionariosSelecionados = new ArrayList<PessoaModel>(0);
	
	
	public String cadastrar() {
		try {
			
			List<Long> idsFuncionariosSelecionados = new ArrayList<Long>(0);
			for (PessoaModel pessoaModel : getFuncionariosSelecionados()) {
				idsFuncionariosSelecionados.add(pessoaModel.getId());
			}
			
			this.getCadastrarChamado().setIdSolicitante(getSolicitante() == null ? null : getSolicitante().getId());
			this.getCadastrarChamado().setIdSetor(getSetor() == null ? null : getSetor().getId());
			this.getCadastrarChamado().setIdsFuncionarios(idsFuncionariosSelecionados);
			
			this.getCadastrarChamado().setIdPessoaLogada(this.recuperaUsuarioLogado.getIdPessoa());
			
			
			this.service.processar(getCadastrarChamado());
			this.cadastrarChamado = new CadastrarChamado();
			this.setor = new SetorModel();
			this.solicitante = new PessoaModel();
			this.funcionariosSelecionados = new ArrayList<PessoaModel>();
			addInfoMessage("Chamado cadastrado com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		
		return null;
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
	
	@SuppressWarnings("unchecked")
	public List<SetorModel> completeSetores(String query) {
        List<SetorModel> filteredSetores = new ArrayList<SetorModel>();
        ListarSetorPorNomeUnidadeCusto listarSetorPorNomeUnidadeCusto = new ListarSetorPorNomeUnidadeCusto();
        listarSetorPorNomeUnidadeCusto.setNome(query);
  
        try {
        	//filteredSetores = (List<SetorModel>) this.service.consultar(listarPorNome);
        	filteredSetores = (List<SetorModel>) this.service.consultar(listarSetorPorNomeUnidadeCusto);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
         
        return filteredSetores;
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
			e1.printStackTrace();
		}
		
		listarPorParametros.setIdSetorLocalizacao(pessoaModel.getIdSetorLocalizacao());
		
		try {
			this.funcionarios = (List<PessoaModel>) service.consultar(listarPorParametros);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
		return funcionarios;
	}
	
	public ContextService getService() {
		return service;
	}

	public void setService(ContextService service) {
		this.service = service;
	}

	public CadastrarChamado getCadastrarChamado() {
		return cadastrarChamado;
	}

	public void setCadastrarChamado(CadastrarChamado cadastrarChamado) {
		this.cadastrarChamado = cadastrarChamado;
	}

	public PessoaModel getSolicitante() {
		return solicitante;
	}


	public void setSolicitante(PessoaModel solicitante) {
		this.solicitante = solicitante;
	}


	public List<PessoaModel> getFuncionariosSelecionados() {
		return funcionariosSelecionados;
	}


	public void setFuncionariosSelecionados(List<PessoaModel> funcionariosSelecionados) {
		this.funcionariosSelecionados = funcionariosSelecionados;
	}

	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}

	public SetorModel getSetor() {
		return setor;
	}

	public void setSetor(SetorModel setor) {
		this.setor = setor;
	}
}
