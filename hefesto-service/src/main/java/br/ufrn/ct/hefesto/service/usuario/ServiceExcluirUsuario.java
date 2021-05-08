package br.ufrn.ct.hefesto.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.request.usuario.ExcluirUsuario;
import br.ufrn.ct.hefesto.persistence.dao.UsuarioDao;
import br.ufrn.ct.hefesto.persistence.entity.Usuario;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("ExcluirUsuario")
public class ServiceExcluirUsuario implements IServiceVoid<ExcluirUsuario>{

	@Autowired
	UsuarioDao usuarioDao;
	
	@Transactional
	public void executar(ExcluirUsuario request) throws NegocioException {
		Usuario usuario = usuarioDao.findByLogin(request.getLogin());
		usuario.setIsAtivo(false);
		usuarioDao.update(usuario);
		
	}

	public void validar(ExcluirUsuario request) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
