package br.ufrn.ct.hefesto.service.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.StatusModel;
import br.ufrn.ct.hefesto.model.request.status.ListarStatus;
import br.ufrn.ct.hefesto.persistence.dao.StatusDao;
import br.ufrn.ct.hefesto.persistence.entity.Status;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultar;

@Service("ListarStatus")
public class ServiceListarStatus implements IServiceConsultar<StatusModel, ListarStatus>{

	@Autowired
	StatusDao statusDao;
	
	@Transactional
	public List<StatusModel> consultar(ListarStatus request) throws NegocioException {
		List<StatusModel> retorno = new ArrayList<StatusModel>(0);
		for (Status status : statusDao.findAll()) {
			StatusModel model = new StatusModel();
			
			model.setId(status.getId());
			model.setNome(status.getNome());
			
			retorno.add(model);
		}
		return retorno;
	}

	public void validar(ListarStatus arg0) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
