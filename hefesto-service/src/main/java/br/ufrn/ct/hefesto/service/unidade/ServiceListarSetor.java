package br.ufrn.ct.hefesto.service.unidade;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.request.unidade.ListarSetor;
import br.ufrn.ct.hefesto.persistence.dao.UnidadeDao;
import br.ufrn.ct.hefesto.persistence.entity.Unidade;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultar;

@Service("ListarSetor")
public class ServiceListarSetor implements IServiceConsultar<SetorModel, ListarSetor>{

	@Autowired
	UnidadeDao setorDao;
	
	@Transactional
	public List<SetorModel> consultar(ListarSetor request) throws NegocioException {
		List<SetorModel> retorno = new ArrayList<SetorModel>(0);
		
		for (Unidade setor : this.setorDao.findAll()) {
			SetorModel model = new SetorModel();
			model.setId(setor.getId());
			model.setNome(setor.getNome());
			//model.setIdPredio(setor.getPredio().getId());
			model.setIdSetorUfrn(setor.getIdUnidadeUfrn());
			
			retorno.add(model);
		}
		
		return retorno;
	}

	public void validar(ListarSetor arg0) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
