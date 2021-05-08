package br.ufrn.ct.hefesto.service.historicochamado;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.HistoricoMudancaStatusChamadoModel;
import br.ufrn.ct.hefesto.model.request.historicochamado.ObterHistoricoMudancaStatusChamado;
import br.ufrn.ct.hefesto.persistence.dao.HistoricoChamadoDao;
import br.ufrn.ct.hefesto.persistence.entity.HistoricoMudancaStatusChamado;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceObter;

@Service("ObterHistoricoMudancaStatusChamado")
public class ServiceObterHistoricoMudancaStatusChamado implements IServiceObter<HistoricoMudancaStatusChamadoModel, ObterHistoricoMudancaStatusChamado>{

	@Autowired
	HistoricoChamadoDao historicoChamadoDao;
	
	@Transactional
	public HistoricoMudancaStatusChamadoModel obter(ObterHistoricoMudancaStatusChamado request) throws NegocioException {
		HistoricoMudancaStatusChamado historicoStatus = new HistoricoMudancaStatusChamado();
		historicoStatus = historicoChamadoDao.findHistoricoStatus(request.getIdHistoricoChamado());
		
		if(historicoStatus == null)
			return null;
		
		HistoricoMudancaStatusChamadoModel model = new HistoricoMudancaStatusChamadoModel();
		model.setIdHistoricoStatus(historicoStatus.getId());
		model.setIdHistorico(historicoStatus.getHistorico().getId());
		model.setIdStatus(historicoStatus.getStatus().getId());
		
		return model;
	}

}
