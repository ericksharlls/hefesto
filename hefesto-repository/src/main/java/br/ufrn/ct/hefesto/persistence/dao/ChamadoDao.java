package br.ufrn.ct.hefesto.persistence.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import br.ufrn.ct.hefesto.persistence.entity.Atendimento;
import br.ufrn.ct.hefesto.persistence.entity.Chamado;
import dev.modulo.persistence.dao.AbstractDao;

@Repository
public class ChamadoDao extends AbstractDao<Long, Chamado>{
	@SuppressWarnings("unchecked")
	public Chamado findLastByAnoSetorTipoServico(Integer ano, Long idSetor, Long idTipoServico) {
		Chamado retorno = null;
		/*
		String sql = "	SELECT c "
				+ "		FROM Chamado c "
				+ "		WHERE c.id = (	SELECT MAX(c.id) "
				+ "						FROM Chamado c "
				+ "							JOIN c.unidade u "
				+ "							JOIN c.tipoServico t "
				+ "						WHERE YEAR(c.dataAbertura) = YEAR(CURDATE()) "
				+ "							AND u.id = :idSetor "
				+ "							AND t.id = :idTipoServico) ";
				*/
		String sql = "SELECT c.* FROM chamado c WHERE c.id_chamado = ( SELECT MAX(ch.id_chamado) FROM chamado ch INNER JOIN unidade u INNER JOIN tipo_servico t " 
				+ " WHERE "+
				" ch.id_unidade = u.id_unidade AND ch.id_tipo_servico = t.id_tipo_servico AND " 
				+ " YEAR(ch.data_abertura) = YEAR(CURDATE()) AND u.id_unidade = " + idSetor + " AND t.id_tipo_servico = " + idTipoServico + ")";
		List<Object[]> rows = getSession().createSQLQuery(sql).list();
		System.out.println("########" + rows.toString());
		for(Object[] row : rows) {
			retorno = new Chamado();
			retorno.setId(Long.parseLong(row[0].toString()));
			retorno.setCodigo(row[1].toString());
			System.out.println("###### codigo: " + row[1].toString());
		}
		if(retorno==null) {
			System.out.println("####### Objeto Vazio!!");
		} else {
			System.out.println("####### Objeto Com coteudo!!");
		}
		System.out.println("###### Chamado(DAO) idSetor e idTipoServico: " + idSetor + " - " + idTipoServico);
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Chamado> findByAllParametros(String codigo, String dataAbertura, String dataConclusao, Long idTipoServico, Long idSetorLogado, Long idSetor, Long idSolicitante, Long idStatus, Integer startPage, Integer maxPage) {
		String sql = " SELECT c "
					+ " FROM Chamado c "
					+ " 	JOIN c.tipoServico tipoServico "
					+ " 	JOIN c.unidade setor "
					+ " 	JOIN c.solicitante solicitante "
					+ " 	JOIN c.status status "
					+ "	WHERE 1=1 ";
					if(codigo != null)
						sql += "	AND c.codigo LIKE '%" + codigo + "%'";
					if(dataAbertura != null)
						sql += "	AND c.dataAbertura LIKE '%" + dataAbertura + "%'";
					if(dataConclusao != null)
						sql += "	AND c.dataConclusao LIKE '%" + dataConclusao + "%'";
					if(idTipoServico != null && idTipoServico != 0) {
						sql += "	AND tipoServico.id = " + idTipoServico;
						sql += "	AND tipoServico.unidade.id = " + idSetorLogado;
					}
					if(idSetor != null && idSetor != 0)
						sql += "	AND setor.id = " + idSetor;
					if(idSolicitante != null && idSolicitante != 0)
						sql += "	AND solicitante.id = " + idSolicitante;
					if(idStatus != null && idStatus != 0)
						sql += "	AND status.id = " + idStatus;
		
		Query<Chamado> query = getSession().createQuery(sql);
		query.setFirstResult(startPage);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Chamado> findAtivosByAllParametros(String codigo, String dataAbertura, String dataConclusao, Long idTipoServico, Long idSetorLogado, Long idSetor, Long idSolicitante, Long idStatus, Integer startPage, Integer maxPage) {
		String sql = " SELECT c "
					+ " FROM Chamado c "
					+ " 	JOIN c.tipoServico tipoServico "
					+ " 	JOIN c.unidade setor "
					+ " 	JOIN c.solicitante solicitante "
					+ " 	JOIN c.status status "
					+ "	WHERE 1=1 "
					+ " AND status.id <> 4 ";
					if(codigo != null)
						sql += "	AND c.codigo LIKE '%" + codigo + "%'";
					if(dataAbertura != null)
						sql += "	AND c.dataAbertura LIKE '%" + dataAbertura + "%'";
					if(dataConclusao != null)
						sql += "	AND c.dataConclusao LIKE '%" + dataConclusao + "%'";
					if(idTipoServico != null && idTipoServico != 0) {
						sql += "	AND tipoServico.id = " + idTipoServico;
						sql += "	AND tipoServico.setor.id = " + idSetorLogado;
					}
					if(idSetor != null && idSetor != 0)
						sql += "	AND setor.id = " + idSetor;
					if(idSolicitante != null && idSolicitante != 0)
						sql += "	AND solicitante.id = " + idSolicitante;
					if(idStatus != null && idStatus != 0)
						sql += "	AND status.id = " + idStatus;
		
		Query<Chamado> query = getSession().createQuery(sql);
		query.setFirstResult(startPage);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public Integer countByAllParametros(Boolean ativo, String codigo, String dataAbertura, String dataConclusao, Long idTipoServico, Long idSetor, Long idSolicitante, Long idStatus) {
		String sql = " SELECT count(c) "
				+ " FROM Chamado c "
				+ " 	JOIN c.tipoServico tipoServico "
				+ " 	JOIN c.unidade setor "
				+ " 	JOIN c.solicitante solicitante "
				+ " 	JOIN c.status status "
				+ "	WHERE 1=1 ";
				if(ativo)
					sql += "	AND status.id <> 4 ";
				if(codigo != null)
					sql += "	AND c.codigo LIKE '%" + codigo + "%'";
				if(dataAbertura != null)
					sql += "	AND c.dataAbertura LIKE '%" + dataAbertura + "%'";
				if(dataConclusao != null)
					sql += "	AND c.dataConclusao LIKE '%" + dataConclusao + "%'";
				if(idTipoServico != null && idTipoServico != 0) 
					sql += "	AND tipoServico.id = " + idTipoServico;
				if(idSetor != null && idSetor != 0)
					sql += "	AND setor.id = " + idSetor;
				if(idSolicitante != null && idSolicitante != 0)
					sql += "	AND solicitante.id = " + idSolicitante;
				if(idStatus != null && idStatus != 0)
					sql += "	AND status.id = " + idStatus;
		Query<Long> query = getSession().createQuery(sql);
		Long retorno = query.uniqueResult();
		return retorno.intValue();
	}
}
