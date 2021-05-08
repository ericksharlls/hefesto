package br.ufrn.ct.hefesto.persistence.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import br.ufrn.ct.hefesto.persistence.entity.Papel;
import dev.modulo.persistence.dao.AbstractDao;

@Repository
public class PapelDao extends AbstractDao<Long, Papel> {
	
	@SuppressWarnings("unchecked")
	public List<Papel> findByUsuario(Long idUsuario) {
		String sql = "select p from Papel p, PermissaoUsuario pu, Usuario u WHERE p.id = pu.permissaoUsuarioPK.idPapel AND pu.permissaoUsuarioPK.idUsuario = u.id AND u.id = :idUsuario";
		Query<Papel> query = getSession().createQuery(sql);
		query.setParameter("idUsuario", idUsuario);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public Papel findOneByUsuario(Long idUsuario) {
		String sql = "select p from Papel p, PermissaoUsuario pu, Usuario u WHERE p.id = pu.permissaoUsuarioPK.idPapel AND pu.permissaoUsuarioPK.idUsuario = u.id AND u.id = :idUsuario";
		Query<Papel> query = getSession().createQuery(sql);
		query.setParameter("idUsuario", idUsuario);
		return query.uniqueResult();
	}

}
