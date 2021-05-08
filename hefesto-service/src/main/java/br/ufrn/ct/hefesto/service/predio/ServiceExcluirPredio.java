package br.ufrn.ct.hefesto.service.predio;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.request.predio.ExcluirPredio;
import br.ufrn.ct.hefesto.persistence.dao.PredioDao;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("ExcluirPredio")
public class ServiceExcluirPredio implements IServiceVoid<ExcluirPredio>{

	@Autowired
	PredioDao predioDao;
	
	private static String ID_PREDIO_VAZIO = "01.3";
	
	@Transactional
	public void executar(ExcluirPredio request) throws NegocioException {
		this.predioDao.delete(request.getId());
	}

	public void validar(ExcluirPredio request) throws NegocioException {
		if(request.getId() == null) {
			throw new NegocioException(ID_PREDIO_VAZIO);
		}
	}

}
