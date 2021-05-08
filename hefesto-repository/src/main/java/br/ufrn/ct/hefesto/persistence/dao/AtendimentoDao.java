package br.ufrn.ct.hefesto.persistence.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import br.ufrn.ct.hefesto.persistence.entity.Atendimento;
import dev.modulo.persistence.dao.AbstractDao;

@Repository
public class AtendimentoDao extends AbstractDao<Long, Atendimento>{
	@SuppressWarnings("unchecked")
	public List<Atendimento> findByChamado(Long idChamado) {
		String sql = "	SELECT a "
				+ "		FROM Atendimento a "
				+ " 	JOIN a.chamado chamado "
				+ "		WHERE chamado.id = :idChamado ";
		Query<Atendimento> query = getSession().createQuery(sql);
		query.setParameter("idChamado", idChamado);
		List<Atendimento> retorno = query.list();
		return retorno;
	}
}
