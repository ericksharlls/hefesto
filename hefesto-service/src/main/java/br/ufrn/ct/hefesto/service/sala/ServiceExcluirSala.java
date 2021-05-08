package br.ufrn.ct.hefesto.service.sala;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.request.sala.ExcluirSala;
import br.ufrn.ct.hefesto.persistence.dao.SalaDao;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("ExcluirSala")
public class ServiceExcluirSala implements IServiceVoid<ExcluirSala> {

	@Autowired
	private SalaDao salaDao;
	
	private static String ID_SALA_VAZIO = "09.7";
	
	@Transactional
	public void executar(ExcluirSala request) throws NegocioException {
		
		this.salaDao.delete(request.getId());
		
	}

	public void validar(ExcluirSala request) throws NegocioException {
		if(request.getId() == null) {
			throw new NegocioException(ID_SALA_VAZIO);
		}
	}

}
