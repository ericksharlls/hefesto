package br.ufrn.ct.hefesto.service.usuario;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.request.usuario.EditarUsuario;
import br.ufrn.ct.hefesto.persistence.dao.PapelDao;
import br.ufrn.ct.hefesto.persistence.dao.UsuarioDao;
import br.ufrn.ct.hefesto.persistence.entity.Papel;
import br.ufrn.ct.hefesto.persistence.entity.Usuario;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("EditarUsuario")
public class ServiceEditarUsuario implements IServiceVoid<EditarUsuario>{

	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private PapelDao papelDao;
	
	private static String LOGIN_VAZIO = "06.1";
	private static String SENHA_VAZIO = "06.2";
	private static String PESSOA_VAZIO = "06.3";
	private static String PAPEL_VAZIO = "06.4";
	private static String SENHAS_DIFERENTES = "06.7";
	
	@Transactional
	public void executar(EditarUsuario editarUsuario) throws NegocioException {
		Usuario usuario = usuarioDao.getById(editarUsuario.getId());
		Papel papel = papelDao.getById(editarUsuario.getIdPapelUsuario());
		
		usuario.setSenha(new BCryptPasswordEncoder().encode(editarUsuario.getSenha()));
		
		if (!usuario.getPapeis().contains(papel)) {
			usuario.getPapeis().clear();
			usuario.addPapel(papel);
		}
		usuarioDao.update(usuario);
	}

	@Transactional
	public void validar(EditarUsuario editarUsuario) throws NegocioException {
		if (editarUsuario.getIdPessoa() == null || editarUsuario.getIdPessoa().equals(new Long(0))) {
			throw new NegocioException(PESSOA_VAZIO);
		}
		if (StringUtils.isBlank(editarUsuario.getLogin())) {
			throw new NegocioException(LOGIN_VAZIO);
		}
		if (StringUtils.isBlank(editarUsuario.getSenha())) {
			throw new NegocioException(SENHA_VAZIO);
		}
		if (editarUsuario.getIdPapelUsuario() == null || editarUsuario.getIdPapelUsuario().equals(new Long(0))) {
			throw new NegocioException(PAPEL_VAZIO);
		}
		if (!editarUsuario.getSenha().equals(editarUsuario.getSenhaConfirmacao()))
			throw new NegocioException(SENHAS_DIFERENTES);
	}

}
