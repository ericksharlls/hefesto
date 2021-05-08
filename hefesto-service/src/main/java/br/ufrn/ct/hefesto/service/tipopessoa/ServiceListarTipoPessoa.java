package br.ufrn.ct.hefesto.service.tipopessoa;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.TipoPessoaModel;
import br.ufrn.ct.hefesto.model.request.tipopessoa.ListarTipoPessoa;
import br.ufrn.ct.hefesto.persistence.dao.TipoPessoaDao;
import br.ufrn.ct.hefesto.persistence.entity.TipoPessoa;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultar;

@Service("ListarTipoPessoa")
public class ServiceListarTipoPessoa implements IServiceConsultar<TipoPessoaModel, ListarTipoPessoa>{

	@Autowired
	private TipoPessoaDao tipoPessoaDao;
	
	@Transactional
	public List<TipoPessoaModel> consultar(ListarTipoPessoa request) throws NegocioException {
		List<TipoPessoaModel> retorno = new ArrayList<TipoPessoaModel>(0);
		
		for (TipoPessoa tipoPessoa : this.tipoPessoaDao.findAll()) {
			TipoPessoaModel model = new TipoPessoaModel();
			model.setId(tipoPessoa.getId());
			model.setNome(tipoPessoa.getNome());
			model.setDescricao(tipoPessoa.getDescricao());
			
			retorno.add(model);
		}
		return retorno;
	}

	public void validar(ListarTipoPessoa arg0) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}