package br.ufrn.ct.hefesto.service.sala;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.SalaModel;
import br.ufrn.ct.hefesto.model.request.sala.ConsultarSala;
import br.ufrn.ct.hefesto.persistence.dao.SalaDao;
import br.ufrn.ct.hefesto.persistence.entity.Sala;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultarPaginado;

@Service("ConsultarSala")
public class ServiceConsultarSala implements IServiceConsultarPaginado<SalaModel, ConsultarSala> {

	@Autowired
	private SalaDao salaDao;
	
	@Transactional(readOnly= true)
	public List<SalaModel> consultar(ConsultarSala request) throws NegocioException {
		List<SalaModel> retorno = new ArrayList<SalaModel>(0);
		for(Sala sala : this.salaDao.findByNomePredioUnidade(request.getNome(), request.getIdPredio(), request.getIdUnidade(), request.getStartPosition(), request.getMaxResult())) {
			
			SalaModel salaModel = new SalaModel();
			
			salaModel.setId(sala.getId());
			salaModel.setNome(sala.getNome());
			salaModel.setDescricao(sala.getDescricao());
			salaModel.setIdPredio(sala.getPredio().getId());
			salaModel.setIdUnidade(sala.getUnidade().getId());
			salaModel.setNomePredio(sala.getPredio().getNome());
			salaModel.setNomeUnidade(sala.getUnidade().getNome());
			
			retorno.add(salaModel);
		}
		
		return retorno;
	}

	@Transactional(readOnly= true)
	public Integer contar(ConsultarSala request) throws NegocioException {
	
		return this.salaDao.countByNomePredioUnidade(request.getNome(), request.getIdPredio(),request.getIdUnidade());
	}

	public void validar(ConsultarSala request) throws NegocioException {
	
		
	}

}
