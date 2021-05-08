package br.ufrn.ct.hefesto.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.controller.RecuperaUsuarioLogado;
import br.ufrn.ct.hefesto.model.PessoaModel;
import br.ufrn.ct.hefesto.model.UsuarioModel;
import br.ufrn.ct.hefesto.model.request.obterpessoa.ObterPessoa;
import br.ufrn.ct.hefesto.model.request.obterusuario.ObterUsuario;
import br.ufrn.ct.hefesto.model.request.usuario.AlterarSenhaUsuario;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class AlterarSenhaUsuarioFormBean extends AbstractFormBeanHefesto {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextService service;
	
	private AlterarSenhaUsuario alterarSenhaUsuario = new AlterarSenhaUsuario();
	private ObterUsuario obterUsuario = new ObterUsuario();
	private ObterPessoa obterPessoa = new ObterPessoa();
	
	public String alterarSenha() {
		
		try {
			System.out.println("###### User Em edição==="+ alterarSenhaUsuario.getIdPessoaEmEdicao());
			System.out.println("###### User logado==="+ alterarSenhaUsuario.getIdPessoaLogado());
			service.processar(alterarSenhaUsuario);
			addInfoMessage("Senha alterada com Sucesso!");
		} catch (NegocioException e) {
			
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public String obter() {
		try {
			UsuarioModel usuarioModel = new UsuarioModel();
			PessoaModel pessoaModel = new PessoaModel();
			RecuperaUsuarioLogado recuperaUsuarioLogado = new RecuperaUsuarioLogado();

			this.obterUsuario.setLogin(recuperaUsuarioLogado.getLogin());
			
			pessoaModel = (PessoaModel) service.obter(this.obterPessoa);
			usuarioModel = (UsuarioModel) service.obter(this.obterUsuario);
			
			this.alterarSenhaUsuario.setIdPessoaEmEdicao(pessoaModel.getId());
			System.out.println("###### User Em edição==="+ alterarSenhaUsuario.getIdPessoaEmEdicao());
			this.alterarSenhaUsuario.setIdPessoaLogado(recuperaUsuarioLogado.getIdPessoa());
			System.out.println("###### User logado=== "+ alterarSenhaUsuario.getIdPessoaLogado());
			this.alterarSenhaUsuario.setSenha(usuarioModel.getSenha());
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}

	public AlterarSenhaUsuario getAlterarSenhaUsuario() {
		return alterarSenhaUsuario;
	}

	public void setAlterarSenhaUsuario(AlterarSenhaUsuario alterarSenhaUsuario) {
		this.alterarSenhaUsuario = alterarSenhaUsuario;
	}

	public ObterUsuario getObterUsuario() {
		return obterUsuario;
	}

	public void setObterUsuario(ObterUsuario obterUsuario) {
		this.obterUsuario = obterUsuario;
	}

	public ObterPessoa getObterPessoa() {
		return obterPessoa;
	}

	public void setObterPessoa(ObterPessoa obterPessoa) {
		this.obterPessoa = obterPessoa;
	}

	
}
