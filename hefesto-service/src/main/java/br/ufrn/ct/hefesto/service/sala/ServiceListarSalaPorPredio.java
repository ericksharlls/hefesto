package br.ufrn.ct.hefesto.service.sala;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.SalaModel;
import br.ufrn.ct.hefesto.model.request.sala.ListarSalaPorPredio;
import br.ufrn.ct.hefesto.persistence.dao.SalaDao;
import br.ufrn.ct.hefesto.persistence.entity.Sala;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultar;

@Service("ListarSalaPorPredio")
public class ServiceListarSalaPorPredio implements IServiceConsultar<SalaModel, ListarSalaPorPredio> {

	@Autowired
	private SalaDao salaDao;
	
	@Transactional(readOnly= true)
	public List<SalaModel> consultar(ListarSalaPorPredio request) throws NegocioException {
		List<SalaModel> retorno = new ArrayList<SalaModel>(0);
		for(Sala sala : this.salaDao.findByPredio(request.getIdPredio())) {
			
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

	public void validar(ListarSalaPorPredio request) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
