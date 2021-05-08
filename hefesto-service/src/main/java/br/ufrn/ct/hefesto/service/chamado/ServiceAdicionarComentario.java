package br.ufrn.ct.hefesto.service.chamado;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.request.chamado.AdicionarComentario;
import br.ufrn.ct.hefesto.persistence.dao.ComentarioDao;
import br.ufrn.ct.hefesto.persistence.dao.HistoricoChamadoDao;
import br.ufrn.ct.hefesto.persistence.entity.Chamado;
import br.ufrn.ct.hefesto.persistence.entity.Comentario;
import br.ufrn.ct.hefesto.persistence.entity.HistoricoChamado;
import br.ufrn.ct.hefesto.persistence.entity.OperacaoChamado;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("AdicionarComentario")
public class ServiceAdicionarComentario implements IServiceVoid<AdicionarComentario>{
	
	@Autowired
	ComentarioDao comentarioDao;
	
	@Autowired
	HistoricoChamadoDao historicoChamadoDao;
	
	@Transactional
	public void executar(AdicionarComentario request) throws NegocioException {
		HistoricoChamado historicoChamado = new HistoricoChamado();
		Calendar cal = Calendar.getInstance();
		
		OperacaoChamado operacaoChamado = new OperacaoChamado();
		operacaoChamado.setId(3L);
		Chamado chamado = new Chamado();
		chamado.setId(request.getIdChamado());
		Pessoa pessoaLogada = new Pessoa();
		pessoaLogada.setId(request.getIdPessoaLogada());
		
		historicoChamado.setHoraRealizacao(cal);
		historicoChamado.setChamado(chamado);
		historicoChamado.setOperacao(operacaoChamado);
		historicoChamado.setPessoa(pessoaLogada);
		
		historicoChamadoDao.persist(historicoChamado);
		
		
		Comentario comentario = new Comentario();
		comentario.setTextoComentario(request.getTextoComentario());
		comentario.setHistorico(historicoChamado);
		
		comentarioDao.persist(comentario);
	}

	public void validar(AdicionarComentario arg0) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
