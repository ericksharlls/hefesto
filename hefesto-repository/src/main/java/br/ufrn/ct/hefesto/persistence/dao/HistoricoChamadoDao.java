package br.ufrn.ct.hefesto.persistence.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import br.ufrn.ct.hefesto.persistence.entity.Comentario;
import br.ufrn.ct.hefesto.persistence.entity.HistoricoChamado;
import br.ufrn.ct.hefesto.persistence.entity.HistoricoMudancaStatusChamado;
import dev.modulo.persistence.dao.AbstractDao;

@Repository
public class HistoricoChamadoDao extends AbstractDao<Long, HistoricoChamado>{
	@SuppressWarnings("unchecked")
	public List<HistoricoChamado> findByChamado(Long idChamado) {
		String sql = "	SELECT h "
				+ "		FROM HistoricoChamado h "
				+ "		JOIN h.chamado c"
				+ "		WHERE c.id = " + idChamado;
		Query<HistoricoChamado> query = getSession().createQuery(sql);
		List<HistoricoChamado> retorno = query.list();
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public Comentario findComentario(Long idHistoricoChamado) {
		String sql = "	SELECT c "
				+ "		FROM Comentario c "
				+ "		JOIN c.historico h"
				+ "		WHERE h.id = " + idHistoricoChamado;
		Query<Comentario> query = getSession().createQuery(sql);
		Comentario retorno = query.uniqueResult();
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public HistoricoMudancaStatusChamado findHistoricoStatus(Long idHistoricoChamado) {
		String sql = "	SELECT hs "
				+ "		FROM HistoricoMudancaStatusChamado hs "
				+ "		JOIN hs.historico h "
				+ "		WHERE h.id = " + idHistoricoChamado;
		Query<HistoricoMudancaStatusChamado> query = getSession().createQuery(sql);
		HistoricoMudancaStatusChamado retorno = query.uniqueResult();
		return retorno;
	}
}
