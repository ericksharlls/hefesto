package br.ufrn.ct.hefesto.service.predio;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.PredioModel;
import br.ufrn.ct.hefesto.model.request.predio.ListarPredio;
import br.ufrn.ct.hefesto.persistence.dao.PredioDao;
import br.ufrn.ct.hefesto.persistence.entity.Predio;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultar;

@Service("ListarPredio")
public class ServiceListarPredio implements IServiceConsultar<PredioModel, ListarPredio>{

	@Autowired
	private PredioDao predioDao;
	
	@Transactional
	public List<PredioModel> consultar(ListarPredio request) throws NegocioException {
		List<PredioModel> retorno = new ArrayList<PredioModel>(0);
		
		for (Predio predio : this.predioDao.findAll()) {
			PredioModel model = new PredioModel();
			model.setId(predio.getId());
			model.setNome(predio.getNome());
			model.setDescricao(predio.getDescricao());
			
			retorno.add(model);
		}
		return retorno;
	}

	public void validar(ListarPredio arg0) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
