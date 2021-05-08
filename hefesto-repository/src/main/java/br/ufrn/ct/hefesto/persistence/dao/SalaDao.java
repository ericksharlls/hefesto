package br.ufrn.ct.hefesto.persistence.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mysql.cj.util.StringUtils;

import br.ufrn.ct.hefesto.persistence.entity.Sala;
import dev.modulo.persistence.dao.AbstractDao;

@Repository
public class SalaDao extends AbstractDao<Long, Sala> {

	@SuppressWarnings("unchecked")
	public List<Sala> findByNomePredioUnidade(String nomeSala, Long idPredio, Long idUnidade, Integer startPage, Integer maxPage){
		String sql = " SELECT s "
					+ " FROM Sala s "
					+ "		INNER JOIN s.predio p "
					+ "		INNER JOIN s.unidade u "
					+ " WHERE 1=1 ";
					//if(nomeSala != null)
					if(!StringUtils.isEmptyOrWhitespaceOnly(nomeSala))
						sql += " AND s.nome LIKE '%" + nomeSala + "%' ";
					if(idPredio != 0)
						sql += " AND p.id = " + idPredio;
					if(idUnidade != 0)
						sql += " AND u.id = " + idUnidade;
		
		Query<Sala> query = getSession().createQuery(sql);
		query.setFirstResult(startPage);
		query.setMaxResults(maxPage);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public Integer countByNomePredioUnidade(String nomeSala, Long idPredio, Long idUnidade) {
		String sql = " SELECT   count(s) "
					+ " FROM Sala s "
					+ "		INNER JOIN s.predio p "
					+ "		INNER JOIN s.unidade u "
					+ " WHERE 1=1 ";
					//if(nomeSala != null)
					if(!StringUtils.isEmptyOrWhitespaceOnly(nomeSala))
						sql += " AND s.nome LIKE '%" + nomeSala + "%' ";
					if(idPredio != 0)
						sql += " AND p.id = " + idPredio;
					if(idUnidade != 0)
						sql += " AND u.id = " + idUnidade;
		
		Query<Long> query = getSession().createQuery(sql);
		Long retorno = query.uniqueResult();
		return  retorno.intValue();
	}
	
	public List<Sala> findByPredio(Long idPredio) {
		String sql = " SELECT s FROM Sala s INNER JOIN s.predio p WHERE p.id = :idPredio";
		Query<Sala> query = getSession().createQuery(sql);
		query.setParameter("idPredio", idPredio);
		return query.list();
	}
	
}
