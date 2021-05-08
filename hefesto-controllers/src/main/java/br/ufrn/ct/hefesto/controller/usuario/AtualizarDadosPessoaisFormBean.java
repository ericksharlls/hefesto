package br.ufrn.ct.hefesto.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.controller.RecuperaUsuarioLogado;
import br.ufrn.ct.hefesto.model.PessoaModel;
import br.ufrn.ct.hefesto.model.request.obterpessoa.ObterPessoa;
import br.ufrn.ct.hefesto.model.request.usuario.AtualizarDadosPessoais;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;


@Controller
@Scope("view")
public class AtualizarDadosPessoaisFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextService service;
	
	private AtualizarDadosPessoais atualizarDadosPessoais = new AtualizarDadosPessoais();
	private ObterPessoa obterPessoa = new ObterPessoa();

	public String atualizarDados() {
		
		try {
				service.processar(this.atualizarDadosPessoais);
				addInfoMessage("Dados pessoais atualizados com sucesso.");
			} catch (NegocioException e) {
				
				addErrorMessageByCodeMessage(e.getCodeErrorMessage());
				e.printStackTrace();
			}
		
		return null;
		
	}
	
	public String obter() {
		
		try {
			PessoaModel pessoaModel = new PessoaModel();
			RecuperaUsuarioLogado recuperaUsuarioLogado = new RecuperaUsuarioLogado();
			pessoaModel = (PessoaModel) service.obter(this.obterPessoa);
			this.atualizarDadosPessoais.setIdPessoaLogada(recuperaUsuarioLogado.getIdPessoa());
			this.atualizarDadosPessoais.setIdPessoaEmEdicao(pessoaModel.getId());
			this.atualizarDadosPessoais.setNome(pessoaModel.getNome());
			this.atualizarDadosPessoais.setEmail(pessoaModel.getEmail());
			this.atualizarDadosPessoais.setDataNascimento(pessoaModel.getDataNascimento());
			this.atualizarDadosPessoais.setTelefone(pessoaModel.getTelefone());
			this.atualizarDadosPessoais.setMatricula(pessoaModel.getMatricula());
			this.atualizarDadosPessoais.setNumeroDocumento(pessoaModel.getNumeroDocumento());
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	@Override
	protected String retornaOperacaoAtual() {
		
		return null;
	}
	
	
	public AtualizarDadosPessoais getAtualizarDadosPessoais() {
		return atualizarDadosPessoais;
	}

	public void setAtualizarDadosPessoais(AtualizarDadosPessoais atualizarDadosPessoais) {
		this.atualizarDadosPessoais = atualizarDadosPessoais;
	}
	
	public ObterPessoa getObterPessoa() {
		return obterPessoa;
	}

	public void setObterPessoa(ObterPessoa obterPessoa) {
		this.obterPessoa = obterPessoa;
	}

	

}
