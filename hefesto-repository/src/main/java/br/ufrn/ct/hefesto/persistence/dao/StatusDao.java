package br.ufrn.ct.hefesto.persistence.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import br.ufrn.ct.hefesto.persistence.entity.Status;
import dev.modulo.persistence.dao.AbstractDao;

@Repository
public class StatusDao extends AbstractDao<Long, Status> {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Status> findAll() {
		String sql = "SELECT s FROM Status s";
		Query<Status> query = getSession().createQuery(sql);
		query.setCacheable(true);
		query.setCacheRegion("StatusDao.findAll");
		return query.list();
	}

}
