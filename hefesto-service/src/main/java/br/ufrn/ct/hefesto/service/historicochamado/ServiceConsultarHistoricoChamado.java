package br.ufrn.ct.hefesto.service.historicochamado;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.HistoricoChamadoModel;
import br.ufrn.ct.hefesto.model.request.historicochamado.ConsultarHistoricoChamado;
import br.ufrn.ct.hefesto.persistence.dao.HistoricoChamadoDao;
import br.ufrn.ct.hefesto.persistence.entity.HistoricoChamado;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultar;

@Service("ConsultarHistoricoChamado")
public class ServiceConsultarHistoricoChamado implements IServiceConsultar<HistoricoChamadoModel, ConsultarHistoricoChamado>{

	@Autowired
	HistoricoChamadoDao historicoChamadoDao;
	
	@Transactional
	public List<HistoricoChamadoModel> consultar(ConsultarHistoricoChamado request) throws NegocioException {
		List<HistoricoChamadoModel> retorno = new ArrayList<HistoricoChamadoModel>(0);
		for (HistoricoChamado historicoChamado : historicoChamadoDao.findByChamado(request.getIdChamado())) {
			HistoricoChamadoModel model = new HistoricoChamadoModel();
			model.setId(historicoChamado.getId());
			model.setHoraRealizacao(historicoChamado.getHoraRealizacao());
			model.setIdChamado(historicoChamado.getChamado().getId());
			model.setIdOperacao(historicoChamado.getOperacao().getId());
			model.setIdPessoa(historicoChamado.getPessoa().getId());
			
			retorno.add(model);
		}
		return retorno;
	}

	public void validar(ConsultarHistoricoChamado arg0) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
