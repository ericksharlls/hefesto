package br.ufrn.ct.hefesto.service.chamado;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.request.chamado.MudarStatus;
import br.ufrn.ct.hefesto.persistence.dao.ChamadoDao;
import br.ufrn.ct.hefesto.persistence.dao.HistoricoChamadoDao;
import br.ufrn.ct.hefesto.persistence.dao.HistoricoMudancaStatusChamadoDao;
import br.ufrn.ct.hefesto.persistence.entity.Chamado;
import br.ufrn.ct.hefesto.persistence.entity.HistoricoChamado;
import br.ufrn.ct.hefesto.persistence.entity.HistoricoMudancaStatusChamado;
import br.ufrn.ct.hefesto.persistence.entity.OperacaoChamado;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import br.ufrn.ct.hefesto.persistence.entity.Status;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("MudarStatus")
public class ServiceMudarStatus implements IServiceVoid<MudarStatus>{
	@Autowired
	private ChamadoDao chamadoDao;
	
	@Autowired
	private HistoricoChamadoDao historicoChamadoDao;
	
	@Autowired
	private HistoricoMudancaStatusChamadoDao historicoMudancaStatusChamadoDao;
	
	@Transactional
	public void executar(MudarStatus request) throws NegocioException {
		
		for (Long idChamado : request.getIdChamado()) {
			Chamado chamado = chamadoDao.getById(idChamado);
			
			if(chamado.getStatus().getId() == request.getDados().get(idChamado))
				continue;
			
			Status status = new Status();
			status.setId(request.getDados().get(idChamado));
			chamado.setStatus(status);
			
			chamadoDao.update(chamado);
			
			// ------------- historico --------------
			
			HistoricoChamado historicoChamado = new HistoricoChamado();
			OperacaoChamado operacaoChamado = new OperacaoChamado();
			Calendar cal = Calendar.getInstance();
			Pessoa pessoaLogada = new Pessoa();
			pessoaLogada.setId(request.getIdPessoaLogada());
			
			operacaoChamado.setId(4L);
			
			historicoChamado.setHoraRealizacao(cal);
			historicoChamado.setChamado(chamado);
			historicoChamado.setOperacao(operacaoChamado);
			historicoChamado.setPessoa(pessoaLogada);
			
			historicoChamadoDao.persist(historicoChamado);
			
			HistoricoMudancaStatusChamado historicoStatus = new HistoricoMudancaStatusChamado();
			historicoStatus.setHistorico(historicoChamado);
			historicoStatus.setStatus(status);
			
			historicoMudancaStatusChamadoDao.persist(historicoStatus);
			
		}
		
	}

	public void validar(MudarStatus arg0) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
