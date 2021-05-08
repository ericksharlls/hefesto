package br.ufrn.ct.hefesto.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.UsuarioModel;
import br.ufrn.ct.hefesto.model.request.obterusuario.ObterUsuario;
import br.ufrn.ct.hefesto.model.request.usuario.EditarUsuario;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class EditarUsuarioFormBean extends AbstractFormBeanHefesto {

	private static final long serialVersionUID = 1L;
	
	private EditarUsuario editarUsuario = new EditarUsuario();
	
	@Autowired
	private ContextService service;
	
	public String editar() {
	
		try {
			service.processar(this.editarUsuario);
			addInfoMessage("Usu\u00e1rio atualizado com sucesso.");
		} catch (NegocioException e) {
			addErrorMessageByCodeMessage(e.getCodeErrorMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public String obter() {
		ObterUsuario obterUsuario = new ObterUsuario();
		obterUsuario.setLogin(this.getEditarUsuario().getLogin());
		
		UsuarioModel usuario = new UsuarioModel();
		try {
			usuario = (UsuarioModel) this.service.obter(obterUsuario);
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.getEditarUsuario().setPapelUsuario(usuario.getPapelUsuario());
		this.getEditarUsuario().setNomePessoa(usuario.getNomePessoa());
		this.getEditarUsuario().setId(usuario.getId());
		this.getEditarUsuario().setIdPapelUsuario(usuario.getIdPapelusuario());
		this.getEditarUsuario().setIdPessoa(usuario.getIdPessoa());
		this.getEditarUsuario().setNumDocumento(usuario.getNumDocumento());
		this.getEditarUsuario().setIsAtivo(usuario.getIsAtivo());
		this.getEditarUsuario().setLogin(usuario.getLogin());
		
		return null;
	}

	@Override
	protected String retornaOperacaoAtual() {
		return null;
	}

	public EditarUsuario getEditarUsuario() {
		return editarUsuario;
	}

	public void setEditarUsuario(EditarUsuario editarUsuario) {
		this.editarUsuario = editarUsuario;
	}

}
