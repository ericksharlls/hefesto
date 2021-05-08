                                                                                                                                               package br.ufrn.ct.hefesto.controller.tiposervico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.controller.RecuperaUsuarioLogado;
import br.ufrn.ct.hefesto.model.TipoServicoModel;
import br.ufrn.ct.hefesto.model.request.tiposervico.ListarTipoServicoByPai;
import br.ufrn.ct.hefesto.model.request.tiposervico.ListarTiposServicoPais;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class PopularComboTipoServicoFormBean extends AbstractFormBeanHefesto {

	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextService service;
	
	@Autowired
	private RecuperaUsuarioLogado recuperaUsuarioLogado;
	private Long idTipoServicoPai;
	
	private List<TipoServicoModel> tiposServicoPai = new ArrayList<TipoServicoModel>(0);
	private List<TipoServicoModel> tiposServicoFilho = new ArrayList<TipoServicoModel>(0);
	
	@SuppressWarnings("unchecked")
	public List<TipoServicoModel> retornaTiposServicoPai() {
		ListarTiposServicoPais listarTipoServico = new ListarTiposServicoPais();
		listarTipoServico.setIdPessoaLogada(this.recuperaUsuarioLogado.getIdPessoa());
		try {
			this.tiposServicoPai = (List<TipoServicoModel>) this.service.consultar(listarTipoServico);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		return tiposServicoPai;
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoServicoModel> retornaTiposServicoFilho() {
		if(getIdTipoServicoPai() != null && getIdTipoServicoPai() != 0) {
			ListarTipoServicoByPai listarTipoServico = new ListarTipoServicoByPai();
			listarTipoServico.setIdPessoaLogada(this.recuperaUsuarioLogado.getIdPessoa());
			listarTipoServico.setIdTipoServicoPai(getIdTipoServicoPai());
			try {
				this.setTiposServicoFilho((List<TipoServicoModel>) this.service.consultar(listarTipoServico));
			} catch (NegocioException e) {
				e.printStackTrace();
			}
			return getTiposServicoFilho();
		}else {
			this.tiposServicoFilho = new ArrayList<TipoServicoModel>(0);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoServicoModel> retornaTiposServicoPorIdTipoServicoPai(Long idTipoServicoPai) {
		if(idTipoServicoPai != null && idTipoServicoPai != 0) {
			ListarTipoServicoByPai listarTipoServico = new ListarTipoServicoByPai();
			listarTipoServico.setIdPessoaLogada(this.recuperaUsuarioLogado.getIdPessoa());
			listarTipoServico.setIdTipoServicoPai(idTipoServicoPai);
			try {
				this.setTiposServicoFilho((List<TipoServicoModel>) this.service.consultar(listarTipoServico));
			} catch (NegocioException e) {
				e.printStackTrace();
			}
			
			return getTiposServicoFilho();
		} else {
			this.tiposServicoFilho = new ArrayList<TipoServicoModel>(0);
			return null;
		}
		
	}

	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TipoServicoModel> getTiposServicoPai() {
		return tiposServicoPai;
	}

	public void setTiposServicoPai(List<TipoServicoModel> tiposServicoPai) {
		this.tiposServicoPai = tiposServicoPai;
	}

	public List<TipoServicoModel> getTiposServicoFilho() {
		return tiposServicoFilho;
	}

	public void setTiposServicoFilho(List<TipoServicoModel> tiposServicoFilho) {
		this.tiposServicoFilho = tiposServicoFilho;
	}

	public Long getIdTipoServicoPai() {
		return idTipoServicoPai;
	}

	public void setIdTipoServicoPai(Long idTipoServicoPai) {
		this.idTipoServicoPai = idTipoServicoPai;
	}


}
