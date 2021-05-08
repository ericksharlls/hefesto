package br.ufrn.ct.hefesto.persistence.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import dev.modulo.persistence.dao.AbstractDao;

@Repository
public class PessoaDao extends AbstractDao<Long, Pessoa> {
	@SuppressWarnings("unchecked")
	public List<Pessoa> findByNome(String nomePessoa, Integer startPage, Integer maxPage) {
		String sql = "select p from Pessoa p WHERE p.nome LIKE :nomePessoa";
		Query<Pessoa> query = getSession().createQuery(sql);
		query.setParameter("nomePessoa", "%" + nomePessoa + "%");
		query.setFirstResult(startPage);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> findByNome(String nomePessoa) {
		String sql = "select p from Pessoa p WHERE p.nome LIKE :nomePessoa";
		Query<Pessoa> query = getSession().createQuery(sql);
		query.setParameter("nomePessoa", "%" + nomePessoa + "%");
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> findByTipoPessoa(Long tipoPessoa){
		String sql = " SELECT p"
				+ " FROM Pessoa p "
				+ " 	JOIN p.tipoPessoa tp "
				+ "	WHERE tp.id = " + tipoPessoa;
		Query<Pessoa> query = getSession().createQuery(sql);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> findByAllParametros(String nomePessoa, String numeroDocumentoPessoa, String matricula, Long tipoPessoa, Long idSetorLocalizacao, Long idSetorLotacao, Integer startPage, Integer maxPage) {
		String sql = " SELECT p"
					+ " FROM Pessoa p "
					+ " 	JOIN p.tipoPessoa tp "
					+ "		LEFT JOIN p.unidadeLocalizacao sloc "
					+ "		JOIN p.unidadeLotacao slot "
					+ "	WHERE 1=1 ";
					if(numeroDocumentoPessoa != null)
						sql += "	AND p.numeroDocumento LIKE '%" + numeroDocumentoPessoa + "%'";
					if(matricula != null)
						sql += "	AND p.matricula LIKE '%" + matricula + "%'";
					if(nomePessoa != null)
						sql += "	AND p.nome LIKE '%" + nomePessoa + "%'";
					if(tipoPessoa != null && tipoPessoa != 0)
						sql += "	AND tp.id = " + tipoPessoa;
					if(idSetorLocalizacao != null && idSetorLocalizacao != 0)
						sql += "	AND sloc.id = " + idSetorLocalizacao;
					if(idSetorLotacao != null && idSetorLotacao != 0)
						sql += "	AND slot.id = " + idSetorLotacao;
		
		Query<Pessoa> query = getSession().createQuery(sql);
		query.setFirstResult(startPage);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> findByAllParametros(String nomePessoa, String numeroDocumentoPessoa, String matricula, Long tipoPessoa, Long idSetorLocalizacao, Long idSetorLotacao) {
		String sql = " SELECT p"
					+ " FROM Pessoa p "
					+ " 	JOIN p.tipoPessoa tp "
					+ "		LEFT JOIN p.unidadeLocalizacao sloc "
					+ "		JOIN p.unidadeLotacao slot "
					+ "	WHERE 1=1 ";
					if(numeroDocumentoPessoa != null)
						sql += "	AND p.numeroDocumento LIKE '%" + numeroDocumentoPessoa + "%'";
					if(matricula != null)
						sql += "	AND p.matricula LIKE '%" + matricula + "%'";
					if(nomePessoa != null)
						sql += "	AND p.nome LIKE '%" + nomePessoa + "%'";
					if(tipoPessoa != null && tipoPessoa != 0)
						sql += "	AND tp.id = " + tipoPessoa;
					if(idSetorLocalizacao != null && idSetorLocalizacao != 0)
						sql += "	AND sloc.id = " + idSetorLocalizacao;
					if(idSetorLotacao != null && idSetorLotacao != 0)
						sql += "	AND slot.id = " + idSetorLotacao;
		Query<Pessoa> query = getSession().createQuery(sql);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public Integer countByNome(String nomePessoa) {
		String sql = "select count(p) from Pessoa p WHERE p.nome LIKE :nomePessoa";
		Query<Long> query = getSession().createQuery(sql);
		query.setParameter("nomePessoa", "%" + nomePessoa + "%");
		Long retorno = query.uniqueResult();
		return retorno.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public Integer countByAllParametros(String nomePessoa, String numeroDocumentoPessoa, String matricula, Long tipoPessoa, Long idSetorLocalizacao, Long idSetorLotacao) {
		String sql = " SELECT count(p) "
				+ " FROM Pessoa p "
				+ " 	JOIN p.tipoPessoa tp "
				+ "		LEFT JOIN p.unidadeLocalizacao sloc "
				+ "		JOIN p.unidadeLotacao slot "
				+ "	WHERE 1=1 ";
				if(numeroDocumentoPessoa != null)
					sql += "	AND p.numeroDocumento LIKE '%" + numeroDocumentoPessoa + "%'";
				if(matricula != null)
					sql += "	AND p.matricula LIKE '%" + matricula + "%'";
				if(nomePessoa != null)
					sql += "	AND p.nome LIKE '%" + nomePessoa + "%'";
				if(tipoPessoa != null && tipoPessoa != 0)
					sql += "	AND tp.id = " + tipoPessoa;
				if(idSetorLocalizacao != null && idSetorLocalizacao != 0)
					sql += "	AND sloc.id = " + idSetorLocalizacao;
				if(idSetorLotacao != null && idSetorLotacao != 0)
					sql += "	AND slot.id = " + idSetorLotacao;
		Query<Long> query = getSession().createQuery(sql);
		Long retorno = query.uniqueResult();
		return retorno.intValue();
	}

	@SuppressWarnings("unchecked")
	public Pessoa findByIdUfrn(Long idPessoaUfrn) {
		String sql = "	SELECT p "
				+ "		FROM Pessoa p "
				+ "		WHERE p.idPessoaUfrn = " + idPessoaUfrn;
		Query<Pessoa> query = getSession().createQuery(sql);
		Pessoa retorno = query.uniqueResult();
		return retorno;
	}
}
