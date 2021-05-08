package br.ufrn.ct.hefesto.service.chamado;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.request.chamado.FinalizarChamado;
import br.ufrn.ct.hefesto.persistence.dao.ChamadoDao;
import br.ufrn.ct.hefesto.persistence.dao.HistoricoChamadoDao;
import br.ufrn.ct.hefesto.persistence.entity.Chamado;
import br.ufrn.ct.hefesto.persistence.entity.HistoricoChamado;
import br.ufrn.ct.hefesto.persistence.entity.OperacaoChamado;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import br.ufrn.ct.hefesto.persistence.entity.Status;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("FinalizarChamado")
public class ServiceFinalizarChamado implements IServiceVoid<FinalizarChamado>{
	
	private static String VALOR_VAZIO = "05.6";

	@Autowired
	private ChamadoDao chamadoDao;
	
	@Autowired
	private HistoricoChamadoDao historicoChamadoDao;
	
	@Transactional
	public void executar(FinalizarChamado request) throws NegocioException {
		Chamado chamado = new Chamado();
		chamado = chamadoDao.getById(request.getIdChamado());
		chamado.setObservacoes(request.getObservacoes());
		chamado.setValorServico(request.getValor());
		
		Status status = new Status();
		status.setId(4L);
		chamado.setStatus(status);
		
		chamadoDao.update(chamado);
		
		
		HistoricoChamado historicoChamado = new HistoricoChamado();
		OperacaoChamado operacaoChamado = new OperacaoChamado();
		Calendar cal = Calendar.getInstance();
		Pessoa pessoaLogada = new Pessoa();
		pessoaLogada.setId(request.getIdPessoaLogada());
		
		operacaoChamado.setId(5L);
		
		historicoChamado.setHoraRealizacao(cal);
		historicoChamado.setChamado(chamado);
		historicoChamado.setOperacao(operacaoChamado);
		historicoChamado.setPessoa(pessoaLogada);
		
		historicoChamadoDao.persist(historicoChamado);
		
	}

	public void validar(FinalizarChamado request) throws NegocioException {
		if (request.getValor() == null) {
			throw new NegocioException(VALOR_VAZIO);
		}
	}

}
