package br.ufrn.ct.hefesto.persistence.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mysql.cj.util.StringUtils;

import br.ufrn.ct.hefesto.persistence.entity.Unidade;
import dev.modulo.persistence.dao.AbstractDao;

@Repository
public class UnidadeDao extends AbstractDao<Long, Unidade>{
	
	@SuppressWarnings("unchecked")
	public List<Unidade> findByNomeUnidadeCusto(String nomeUnidade) {
		String sql = "select u from Unidade u WHERE u.unidadeCusto = true AND u.descricao LIKE :nomeUnidade";
		Query<Unidade> query = getSession().createQuery(sql);
		query.setParameter("nomeUnidade", "%" + nomeUnidade + "%");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Unidade> findByNome(String nomeUnidade) {
		String sql = "select u from Unidade u WHERE u.descricao LIKE :nomeUnidade";
		Query<Unidade> query = getSession().createQuery(sql);
		query.setParameter("nomeUnidade", "%" + nomeUnidade + "%");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Unidade> findByPredio(Long idPredio){
		String sql = " SELECT u "
					+ " FROM Unidade u "
					+ " 	INNER JOIN u.predio p "
					+ " WHERE p.id = " + idPredio;
		Query<Unidade> query = getSession().createQuery(sql);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Unidade> findByNomeCodigoUnidadeCusto(String nomeUnidade, String codigo, Boolean isUnidadeCusto, Integer startPage, Integer maxPage){
		String sql = " SELECT u "
					+ " FROM Unidade u "
					//+ "		INNER JOIN u.predio p "
					+ " WHERE 1=1 AND u.unidadeCusto = " + isUnidadeCusto;
					//if(descricao != null)
					//	sql += " AND u.descricao LIKE '%" + descricao + "%' ";
					if(!StringUtils.isEmptyOrWhitespaceOnly(codigo) && codigo != null)
						sql += " AND u.codigo LIKE '%" + codigo + "%' ";
					if(nomeUnidade != null)
						sql += " AND u.nome LIKE '%" + nomeUnidade + "%' ";
					//if(idPredio != 0)
					//	sql += " AND p.id = " + idPredio;
		
		Query<Unidade> query = getSession().createQuery(sql);
		query.setFirstResult(startPage);
		query.setMaxResults(maxPage);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public Integer countByNomeCodigoUnidadeCusto(String nomeUnidade, String codigo, Boolean isUnidadeCusto) {
		String sql = "select count(u) from Unidade u WHERE u.unidadeCusto = :isUnidadeCusto AND u.nome LIKE :nomeUnidade ";
		//if(descricao != null)
		//	sql += " AND u.descricao LIKE '%" + descricao + "%' ";
		if(!StringUtils.isEmptyOrWhitespaceOnly(codigo) && codigo != null)
			sql += " AND u.codigo LIKE '%" + codigo + "%' ";
		Query<Long> query = getSession().createQuery(sql);
		query.setParameter("nomeUnidade", "%" + nomeUnidade + "%");
		query.setParameter("isUnidadeCusto", isUnidadeCusto);
		Long retorno = query.uniqueResult();
		return retorno.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public Unidade findLast() {
		String sql = "	SELECT u "
				+ "		FROM Unidade u "
				+ "		WHERE u.id = (SELECT MAX(u.id) FROM Unidade u)";
		Query<Unidade> query = getSession().createQuery(sql);
		Unidade retorno = query.uniqueResult();
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public Unidade findLastByPredio(Long idPredio) {
		String sql = "	SELECT u "
				+ "		FROM Unidade u "
				+ "		WHERE u.id = (SELECT MAX(u.id) FROM Unidade u JOIN u.predio p WHERE p.id = :idPredio AND u.fazAtendimento = true)";
		Query<Unidade> query = getSession().createQuery(sql);
		query.setParameter("idPredio", idPredio);
		Unidade retorno = query.uniqueResult();
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public Unidade findByIdUfrn(Integer idUnidadeLocalizacao) {
		String sql = "	SELECT u "
				+ "		FROM Unidade u "
				+ "		WHERE u.idUnidadeUfrn = " + idUnidadeLocalizacao;
		
		Query<Unidade> query = getSession().createQuery(sql);
		Unidade retorno = query.uniqueResult();
		return retorno;
	}
	
}
