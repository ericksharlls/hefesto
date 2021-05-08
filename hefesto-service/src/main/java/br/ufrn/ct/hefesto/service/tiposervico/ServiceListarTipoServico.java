package br.ufrn.ct.hefesto.service.tiposervico;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.TipoServicoModel;
import br.ufrn.ct.hefesto.model.request.tiposervico.ListarTipoServico;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import br.ufrn.ct.hefesto.persistence.dao.TipoServicoDao;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import br.ufrn.ct.hefesto.persistence.entity.TipoServico;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultar;

@Service("ListarTipoServico")
public class ServiceListarTipoServico implements IServiceConsultar<TipoServicoModel, ListarTipoServico>{

	@Autowired
	TipoServicoDao tipoServicoDao;
	@Autowired
	private PessoaDao pessoaDao;
	
	@Transactional
	public List<TipoServicoModel> consultar(ListarTipoServico request) throws NegocioException {
		List<TipoServicoModel> retorno = new ArrayList<TipoServicoModel>(0);
		Pessoa pessoaLogada = new Pessoa();
		pessoaLogada = pessoaDao.getById(request.getIdPessoaLogada());
		
		for (TipoServico tipoServico : this.tipoServicoDao.findBySetorNome(pessoaLogada.getUnidadeLocalizacao().getId(), request.getNome())) {
			TipoServicoModel model = new TipoServicoModel();
			
			model.setId(tipoServico.getId());
			model.setNome(tipoServico.getNome());
			
			retorno.add(model);
		}
		
		
		return retorno;
	}

	
	public void validar(ListarTipoServico arg0) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
