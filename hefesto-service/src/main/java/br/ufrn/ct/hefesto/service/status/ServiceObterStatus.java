package br.ufrn.ct.hefesto.service.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.StatusModel;
import br.ufrn.ct.hefesto.model.request.status.ObterStatus;
import br.ufrn.ct.hefesto.persistence.dao.StatusDao;
import br.ufrn.ct.hefesto.persistence.entity.Status;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceObter;

@Service("ObterStatus")
public class ServiceObterStatus implements IServiceObter<StatusModel, ObterStatus>{

	@Autowired
	private StatusDao statusDao;
	
	@Transactional(readOnly = true)
	public StatusModel obter(ObterStatus obterStatus) throws NegocioException {
		StatusModel model = new StatusModel();
		Status status = this.statusDao.getById(obterStatus.getId());
		
		model.setId(status.getId());
		model.setNome(status.getNome());
		
		return model;
	}

}
