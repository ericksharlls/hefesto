package br.ufrn.ct.hefesto.controller.tipopessoa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.TipoPessoaModel;
import br.ufrn.ct.hefesto.model.request.tipopessoa.ListarTipoPessoa;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class PopularComboTiposPessoaFormBean extends AbstractFormBeanHefesto{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContextService service;
	
	private List<TipoPessoaModel> tiposPessoa = new ArrayList<TipoPessoaModel>(0);

	@SuppressWarnings("unchecked")
	public List<TipoPessoaModel> retornaTiposPessoa() {
		ListarTipoPessoa listarTipoPessoa = new ListarTipoPessoa();
		try {
			this.tiposPessoa = (List<TipoPessoaModel>) this.service.consultar(listarTipoPessoa);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		return tiposPessoa;
	}
	
	
	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}
}
