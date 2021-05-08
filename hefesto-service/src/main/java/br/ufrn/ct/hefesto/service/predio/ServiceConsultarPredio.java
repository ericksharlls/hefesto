package br.ufrn.ct.hefesto.service.predio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.PredioModel;
import br.ufrn.ct.hefesto.model.request.predio.ConsultarPredio;
import br.ufrn.ct.hefesto.persistence.dao.PredioDao;
import br.ufrn.ct.hefesto.persistence.entity.Predio;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultarPaginado;

@Service("ConsultarPredio")
public class ServiceConsultarPredio implements IServiceConsultarPaginado<PredioModel, ConsultarPredio>{

	@Autowired
	private PredioDao predioDao;
	
	@Transactional(readOnly= true)
	public List<PredioModel> consultar(ConsultarPredio request) throws NegocioException {
		List<PredioModel> retorno = new ArrayList<PredioModel>(0);
		for (Predio predio : this.predioDao.findByNome(request.getNome(), request.getStartPosition(), request.getMaxResult())) {
			PredioModel model = new PredioModel();
			model.setId(predio.getId());
			model.setNome(predio.getNome());
			model.setDescricao(predio.getDescricao());
			retorno.add(model);
		}
		return retorno;
	}

	@Transactional(readOnly= true)
	public Integer contar(ConsultarPredio request) throws NegocioException {
		return this.predioDao.countByNome(request.getNome());
	}

	public void validar(ConsultarPredio arg0) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
