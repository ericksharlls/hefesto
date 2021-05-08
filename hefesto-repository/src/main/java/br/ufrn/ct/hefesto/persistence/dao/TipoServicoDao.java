package br.ufrn.ct.hefesto.persistence.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import br.ufrn.ct.hefesto.persistence.entity.TipoServico;
import dev.modulo.persistence.dao.AbstractDao;

@Repository
public class TipoServicoDao extends AbstractDao<Long, TipoServico> {
	
	@SuppressWarnings("unchecked")
	public List<TipoServico> findByNome(String nomeServico, Integer startPage, Integer maxPage) {
		String sql = "select t from TipoServico t WHERE t.nome LIKE :nomeServico";
		Query<TipoServico> query = getSession().createQuery(sql);
		query.setParameter("nomeServico", "%" + nomeServico + "%");
		query.setFirstResult(startPage);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoServico> findBySetorNome(Long idSetor, String nomeServico, Integer startPage, Integer maxPage) {
		String sql = "select t from TipoServico t JOIN t.unidade s WHERE s.id = :idSetor AND t.nome LIKE :nomeServico ORDER BY t.nome";
		Query<TipoServico> query = getSession().createQuery(sql);
		query.setParameter("idSetor", idSetor);
		query.setParameter("nomeServico", "%" + nomeServico + "%");
		query.setFirstResult(startPage);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoServico> findBySetorNome(Long idSetor, String nomeServico) {
		String sql = "select t from TipoServico t JOIN t.unidade s WHERE s.id = :idSetor AND t.nome LIKE :nomeServico";
		Query<TipoServico> query = getSession().createQuery(sql);
		query.setParameter("idSetor", idSetor);
		query.setParameter("nomeServico", "%" + nomeServico + "%");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public Integer countByNome(String nomeServico) {
		String sql = "select count(t) from TipoServico t WHERE t.nome LIKE :nomeServico";
		Query<Long> query = getSession().createQuery(sql);
		query.setParameter("nomeServico", "%" + nomeServico + "%");
		Long retorno = query.uniqueResult();
		return retorno.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public TipoServico findLastBySetor(Long idSetor) {
		String sql = "	SELECT t "
				+ "		FROM TipoServico t "
				+ "		WHERE t.id = (SELECT MAX(t.id) FROM TipoServico t)";
		Query<TipoServico> query = getSession().createQuery(sql);
		TipoServico retorno = query.uniqueResult();
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoServico> findPais(Long idSetor) {
		String sql = "	SELECT t "
				+ "		FROM TipoServico t "
				+ "		JOIN t.unidade u "
				+ "		WHERE u.id = " + idSetor
				+ "		AND t.tipoServicoPai = " + null
				+ " ORDER BY t.nome";
		Query<TipoServico> query = getSession().createQuery(sql);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoServico> findFilhosByPai(Long pai, Long idSetor){
		String sql = "	SELECT t "
				+ "		FROM TipoServico t "
				+ "		JOIN t.unidade u "
				+ "		WHERE u.id = " + idSetor
				+ "		AND t.tipoServicoPai = " + pai + " ORDER BY t.nome";
		Query<TipoServico> query = getSession().createQuery(sql);
		return query.list();
	}

}
