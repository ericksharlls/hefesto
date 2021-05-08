package br.ufrn.ct.hefesto.persistence.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import br.ufrn.ct.hefesto.persistence.entity.TipoPessoa;
import dev.modulo.persistence.dao.AbstractDao;

@Repository
public class TipoPessoaDao extends AbstractDao<Long, TipoPessoa>{
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoPessoa> findAll() {
		String sql = "SELECT tp FROM TipoPessoa tp";
		Query<TipoPessoa> query = getSession().createQuery(sql);
		query.setCacheable(true);
		query.setCacheRegion("TipoPessoaDao.findAll");
		return query.list();
	}
}
