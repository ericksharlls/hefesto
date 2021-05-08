package br.ufrn.ct.hefesto.persistence.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import br.ufrn.ct.hefesto.persistence.entity.Predio;
import dev.modulo.persistence.dao.AbstractDao;

@Repository
public class PredioDao extends AbstractDao<Long, Predio>{

	@SuppressWarnings("unchecked")
	@Override
	public List<Predio> findAll() {
		String sql = "SELECT p FROM Predio p";
		Query<Predio> query = getSession().createQuery(sql);
		query.setCacheable(true);
		query.setCacheRegion("PredioDao.findAll");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Predio> findByNome(String nomePredio, Integer startPage, Integer maxPage) {
		String sql = "select p from Predio p WHERE p.nome LIKE :nomePredio";
		Query<Predio> query = getSession().createQuery(sql);
		query.setParameter("nomePredio", "%" + nomePredio + "%");
		query.setFirstResult(startPage);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public Integer countByNome(String nomePredio) {
		String sql = "select count(p) from Predio p WHERE p.nome LIKE :nomePredio";
		Query<Long> query = getSession().createQuery(sql);
		query.setParameter("nomePredio", "%" + nomePredio + "%");
		Long retorno = query.uniqueResult();
		return retorno.intValue();
	}
}
