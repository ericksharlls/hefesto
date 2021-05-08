package br.ufrn.ct.hefesto.service.unidade;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.request.unidade.ExcluirSetor;
import br.ufrn.ct.hefesto.persistence.dao.UnidadeDao;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("ExcluirSetor")
public class ServiceExcluirSetor implements IServiceVoid<ExcluirSetor>{

	@Autowired
	UnidadeDao setorDao;
	
	private static String ID_SETOR_VAZIO = "03.3";
	
	@Transactional
	public void executar(ExcluirSetor request) throws NegocioException {
		this.setorDao.delete(request.getId());
	}

	public void validar(ExcluirSetor request) throws NegocioException {
		if(request.getId() == null) {
			throw new NegocioException(ID_SETOR_VAZIO);
		}
	}

}
