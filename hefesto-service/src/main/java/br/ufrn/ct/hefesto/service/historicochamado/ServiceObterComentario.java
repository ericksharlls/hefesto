package br.ufrn.ct.hefesto.service.historicochamado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.ComentarioModel;
import br.ufrn.ct.hefesto.model.request.historicochamado.ObterComentario;
import br.ufrn.ct.hefesto.persistence.dao.HistoricoChamadoDao;
import br.ufrn.ct.hefesto.persistence.entity.Comentario;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceObter;

@Service("ObterComentario")
public class ServiceObterComentario implements IServiceObter<ComentarioModel, ObterComentario>{

	@Autowired
	HistoricoChamadoDao historicoDao;
	
	@Transactional
	public ComentarioModel obter(ObterComentario request) throws NegocioException {
		Comentario comentario = new Comentario();
		comentario = historicoDao.findComentario(request.getIdHistoricoChamado());
		if(comentario == null)
			return null;
		ComentarioModel model = new ComentarioModel();
		model.setIdComentario(comentario.getIdComentario());
		model.setIdHistorico(comentario.getHistorico().getId());
		model.setTextoComentario(comentario.getTextoComentario());
		return model;
	}

}
