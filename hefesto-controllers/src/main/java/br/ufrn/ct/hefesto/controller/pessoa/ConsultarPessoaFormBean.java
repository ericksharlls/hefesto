package br.ufrn.ct.hefesto.controller.pessoa;

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
import br.ufrn.ct.hefesto.model.PessoaModel;
import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.request.obterpessoa.ObterPessoa;
import br.ufrn.ct.hefesto.model.request.pessoa.ConsultarPessoa;
import br.ufrn.ct.hefesto.model.request.pessoa.EditarPessoa;
import br.ufrn.ct.hefesto.model.request.pessoa.ExcluirPessoa;
import br.ufrn.ct.hefesto.model.request.unidade.ListarPorNomeSetor;
import br.ufrn.ct.hefesto.model.request.unidade.ObterSetor;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class ConsultarPessoaFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContextService service;
	private ConsultarPessoa consultarPessoa = new ConsultarPessoa();
	private LazyDataModel<PessoaModel> lazyPessoa;
	private PessoaModel pessoaModelSelecionado = new PessoaModel();
	private SetorModel setorLocalizacao = new SetorModel();
	private SetorModel setorLotacao = new SetorModel();
	
	public ConsultarPessoaFormBean() {
		this.consultarPessoa.setNome("");
		this.consultarPessoa.setNumeroDocumento("");
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		
		lazyPessoa = new LazyDataModel<PessoaModel>() {
		
			private static final long serialVersionUID = 1L;
			
			@Override
			public List<PessoaModel> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				getConsultarPessoa().setStartPosition(first);
				getConsultarPessoa().setMaxResult(pageSize);
				
				List<PessoaModel> lista = new ArrayList<PessoaModel>(0);
				
				try {
					lista = (List<PessoaModel>) service.consultarPaginado(getConsultarPessoa());
					setRowCount(service.contar(getConsultarPessoa()));
				} catch (NegocioException e) {
					addErrorMessageByCodeMessage(e.getCodeErrorMessage());
					e.printStackTrace();
				}
				
				return lista;
			}
		};
	}
	
	public String consultar() {
		getConsultarPessoa().setIdSetorLocalizacao(getSetorLocalizacao() != null ? getSetorLocalizacao().getId() : null);
		getConsultarPessoa().setIdSetorLotacao(getSetorLotacao() != null ? getSetorLotacao().getId() : null);
		return null;
	}
	
	public String obter() {
		ObterPessoa obterPessoa = new ObterPessoa();
		obterPessoa.setId(this.getPessoaModelSelecionado().getId());
		
		PessoaModel pessoa = new PessoaModel();
		try {
			pessoa = (PessoaModel) this.service.obter(obterPessoa);
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.getPessoaModelSelecionado().setNome(pessoa.getNome());
		this.getPessoaModelSelecionado().setEmail(pessoa.getEmail());
		this.getPessoaModelSelecionado().setDataNascimento(pessoa.getDataNascimento());
		this.getPessoaModelSelecionado().setIdTipoPessoa(pessoa.getIdTipoPessoa());
		this.getPessoaModelSelecionado().setNumeroDocumento(pessoa.getNumeroDocumento());
		this.getPessoaModelSelecionado().setMatricula(pessoa.getMatricula());
		this.getPessoaModelSelecionado().setTelefone(pessoa.getTelefone());
		this.getPessoaModelSelecionado().setTipoPessoa(pessoa.getTipoPessoa());
		
		ObterSetor obterSetor = new ObterSetor();
		obterSetor.setId(pessoa.getIdSetorLocalizacao());
		
		try {
			this.setSetorLocalizacao((SetorModel) this.service.obter(obterSetor));
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
		obterSetor.setId(pessoa.getIdSetorLotacao());
		
		try {
			this.setSetorLotacao((SetorModel) this.service.obter(obterSetor));
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	public String editar() {
		EditarPessoa editarPessoa = new EditarPessoa();
		editarPessoa.setId(this.pessoaModelSelecionado.getId());
		editarPessoa.setNome(this.pessoaModelSelecionado.getNome());
		editarPessoa.setEmail(this.pessoaModelSelecionado.getEmail());
		editarPessoa.setTelefone(this.pessoaModelSelecionado.getTelefone());
		editarPessoa.setNumeroDocumento(this.pessoaModelSelecionado.getNumeroDocumento());
		editarPessoa.setMatricula(this.pessoaModelSelecionado.getMatricula());
		editarPessoa.setDataNascimento(this.pessoaModelSelecionado.getDataNascimento());
		editarPessoa.setIdTipoPessoa(this.pessoaModelSelecionado.getIdTipoPessoa());
		editarPessoa.setIdSetorLocalizacao(this.getSetorLocalizacao().getId());
		editarPessoa.setIdSetorLotacao(this.getSetorLotacao().getId());
		
		try {
			service.processar(editarPessoa);
			addInfoMessage("Pessoa atualizada com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String excluir() {
		ExcluirPessoa excluirPessoa = new ExcluirPessoa();
		excluirPessoa.setId(this.pessoaModelSelecionado.getId());
		
		try {
			service.processar(excluirPessoa);
			addInfoMessage("Pessoa exclu\u00edda com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		return null;
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
	
	public ConsultarPessoa getConsultarPessoa() {
		return consultarPessoa;
	}

	public void setConsultarPessoa(ConsultarPessoa consultarPessoa) {
		this.consultarPessoa = consultarPessoa;
	}


	public LazyDataModel<PessoaModel> getLazyPessoa() {
		return lazyPessoa;
	}


	public void setLazyPessoa(LazyDataModel<PessoaModel> lazyPessoa) {
		this.lazyPessoa = lazyPessoa;
	}


	public PessoaModel getPessoaModelSelecionado() {
		return pessoaModelSelecionado;
	}


	public void setPessoaModelSelecionado(PessoaModel pessoaModelSelecionado) {
		this.pessoaModelSelecionado = pessoaModelSelecionado;
	}

	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}

	public SetorModel getSetorLocalizacao() {
		return setorLocalizacao;
	}

	public void setSetorLocalizacao(SetorModel setorLocalizacao) {
		this.setorLocalizacao = setorLocalizacao;
	}

	public SetorModel getSetorLotacao() {
		return setorLotacao;
	}

	public void setSetorLotacao(SetorModel setorLotacao) {
		this.setorLotacao = setorLotacao;
	}
	
}
