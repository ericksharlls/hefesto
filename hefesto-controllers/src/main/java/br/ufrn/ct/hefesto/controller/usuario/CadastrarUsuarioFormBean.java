package br.ufrn.ct.hefesto.controller.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.PapelModel;
import br.ufrn.ct.hefesto.model.PessoaModel;
import br.ufrn.ct.hefesto.model.request.pessoa.ListarPorNomePessoa;
import br.ufrn.ct.hefesto.model.request.usuario.CadastrarUsuario;
import br.ufrn.ct.hefesto.model.request.usuario.ListarPapel;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class CadastrarUsuarioFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContextService service;
	
	private PessoaModel pessoa = new PessoaModel();
	private List<PapelModel> papeis = new ArrayList<PapelModel>(0);
	private CadastrarUsuario cadastrarUsuario = new CadastrarUsuario();
	
	public String cadastrar() {
		try {
			
			getCadastrarUsuario().setIdPessoa(getPessoa() != null ? getPessoa().getId() : null);
			getCadastrarUsuario().setSenha(getCadastrarUsuario().getSenha());
			
			service.processar(getCadastrarUsuario());
			this.cadastrarUsuario = new CadastrarUsuario();
			addInfoMessage("Usuario cadastrado com sucesso.");
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
	public List<PapelModel> getPapeis() {
		ListarPapel listarPapel = new ListarPapel();
		try {
			this.papeis = (List<PapelModel>) this.service.consultar(listarPapel);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		return papeis;
	}

	public void setPapeis(List<PapelModel> papeis) {
		this.papeis = papeis;
	}
	
	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}
	public CadastrarUsuario getCadastrarUsuario() {
		return cadastrarUsuario;
	}
	public void setCadastrarUsuario(CadastrarUsuario cadastrarUsuario) {
		this.cadastrarUsuario = cadastrarUsuario;
	}

	public PessoaModel getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaModel pessoa) {
		this.pessoa = pessoa;
	}


}
