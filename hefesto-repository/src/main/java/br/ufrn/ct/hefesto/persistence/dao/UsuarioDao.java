package br.ufrn.ct.hefesto.persistence.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import br.ufrn.ct.hefesto.persistence.entity.Usuario;
import dev.modulo.persistence.dao.AbstractDao;

@Repository
public class UsuarioDao extends AbstractDao<Long, Usuario> {
	
	@SuppressWarnings("unchecked")
	public Usuario findByLogin(String login) {
		String sql = "select u from Usuario u WHERE u.login = :login";
		Query<Usuario> query = getSession().createQuery(sql);		
		query.setParameter("login", login);
    	return query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findByLogin(String login, Integer startPage, Integer maxPage) {
		String sql = "select u from Usuario u WHERE u.login LIKE :login AND u.isAtivo = true";
		Query<Usuario> query = getSession().createQuery(sql);
		query.setParameter("login", "%" + login + "%");
		query.setFirstResult(startPage);
		query.setMaxResults(maxPage);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public Integer countByLogin(String login) {
		String sql = "select count(u) from Usuario u WHERE u.login LIKE :login";
		Query<Long> query = getSession().createQuery(sql);
		query.setParameter("login", "%" + login + "%");
		Long retorno = query.uniqueResult();
		return retorno.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public Usuario findByIdFuncionario(Long idPessoa) {
		String sql = "select u from Usuario u WHERE u.pessoa.id = :idPessoa";
		Query<Usuario> query = getSession().createQuery(sql);		
		query.setParameter("idPessoa", idPessoa);
    	return query.uniqueResult();
	}

}
