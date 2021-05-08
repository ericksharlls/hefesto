package br.ufrn.ct.hefesto.service.unidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.request.unidade.ObterSetor;
import br.ufrn.ct.hefesto.persistence.dao.UnidadeDao;
import br.ufrn.ct.hefesto.persistence.entity.Unidade;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceObter;

@Service("ObterSetor")
public class ServiceObterSetor implements IServiceObter<SetorModel, ObterSetor>{

	@Autowired
	private UnidadeDao setorDao;
	
	@Transactional(readOnly= true)
	public SetorModel obter(ObterSetor request) throws NegocioException {
		Unidade setor = this.setorDao.getById(request.getId());
		SetorModel retorno = null;
		
		if (setor != null) {
			retorno = new SetorModel();
			retorno.setId(setor.getId());
			retorno.setNome(setor.getNome());	
			//retorno.setIdPredio(setor.getPredio().getId());
		}
		
		return retorno;
	}

}
