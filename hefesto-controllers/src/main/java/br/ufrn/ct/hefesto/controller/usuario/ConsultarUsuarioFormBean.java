package br.ufrn.ct.hefesto.controller.usuario;

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
import br.ufrn.ct.hefesto.model.UsuarioModel;
import br.ufrn.ct.hefesto.model.request.obterusuario.ObterUsuario;
import br.ufrn.ct.hefesto.model.request.usuario.ConsultarUsuario;
import br.ufrn.ct.hefesto.model.request.usuario.ExcluirUsuario;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class ConsultarUsuarioFormBean extends AbstractFormBeanHefesto {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContextService service;
	private LazyDataModel<UsuarioModel> lazyUsuario;
	private ConsultarUsuario consultarUsuario = new ConsultarUsuario();
	private UsuarioModel usuarioModelSelecionado = new UsuarioModel();
	
	public ConsultarUsuarioFormBean() {
		getConsultarUsuario().setLogin("");
	}
	
	@PostConstruct
	public void init() {
		setLazyUsuario(new LazyDataModel<UsuarioModel>() {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unchecked")
			@Override
			public List<UsuarioModel> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				
				getConsultarUsuario().setStartPosition(first);
				getConsultarUsuario().setMaxResult(pageSize);
				
				List<UsuarioModel> lista = new ArrayList<UsuarioModel>(0);
				try {
					lista = (List<UsuarioModel>) service.consultarPaginado(getConsultarUsuario());
					setRowCount(service.contar(getConsultarUsuario()));
				} catch (NegocioException e) {
					addErrorMessageByCodeMessage(e.getCodeErrorMessage());
					e.printStackTrace();
				}
				
				return lista;
			}
		});
	}
	
	public String consultar() {
		
		return null;
	}
	
	public String obter() {
		ObterUsuario obterUsuario = new ObterUsuario();
		obterUsuario.setLogin(this.getUsuarioModelSelecionado().getLogin());
		
		UsuarioModel usuario = new UsuarioModel();
		try {
			usuario = (UsuarioModel) this.service.obter(obterUsuario);
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.getUsuarioModelSelecionado().setPapelUsuario(usuario.getPapelUsuario());
		this.getUsuarioModelSelecionado().setNomePessoa(usuario.getNomePessoa());
		this.getUsuarioModelSelecionado().setId(usuario.getId());
		this.getUsuarioModelSelecionado().setIdPapelusuario(usuario.getIdPapelusuario());
		this.getUsuarioModelSelecionado().setIdPessoa(usuario.getIdPessoa());
		this.getUsuarioModelSelecionado().setNumDocumento(usuario.getNumDocumento());
		this.getUsuarioModelSelecionado().setIsAtivo(usuario.getIsAtivo());
		this.getUsuarioModelSelecionado().setLogin(usuario.getLogin());
		
		return null;
	}
	
	public String excluir() {
		ExcluirUsuario excluirUsuario = new ExcluirUsuario();
		excluirUsuario.setLogin(this.getUsuarioModelSelecionado().getLogin());
		
		try {
			service.processar(excluirUsuario);
			addInfoMessage("Usu\\u00e1rio exclu\u00eddo com sucesso.");
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

	public ConsultarUsuario getConsultarUsuario() {
		return consultarUsuario;
	}

	public void setConsultarUsuario(ConsultarUsuario consultarUsuario) {
		this.consultarUsuario = consultarUsuario;
	}

	public LazyDataModel<UsuarioModel> getLazyUsuario() {
		return lazyUsuario;
	}

	public void setLazyUsuario(LazyDataModel<UsuarioModel> lazyUsuario) {
		this.lazyUsuario = lazyUsuario;
	}

	public UsuarioModel getUsuarioModelSelecionado() {
		return usuarioModelSelecionado;
	}

	public void setUsuarioModelSelecionado(UsuarioModel usuarioModelSelecionado) {
		this.usuarioModelSelecionado = usuarioModelSelecionado;
	}

}
