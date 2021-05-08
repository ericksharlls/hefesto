package br.ufrn.ct.hefesto.service.usuario;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.request.usuario.CadastrarUsuario;
import br.ufrn.ct.hefesto.persistence.dao.PapelDao;
import br.ufrn.ct.hefesto.persistence.dao.UsuarioDao;
import br.ufrn.ct.hefesto.persistence.entity.Papel;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import br.ufrn.ct.hefesto.persistence.entity.Usuario;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("CadastrarUsuario")
public class ServiceCadastrarUsuario implements IServiceVoid<CadastrarUsuario>{

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private PapelDao papelDao;
	
	private static String LOGIN_VAZIO = "06.1";
	private static String SENHA_VAZIO = "06.2";
	private static String PESSOA_VAZIO = "06.3";
	private static String PAPEL_VAZIO = "06.4";
	private static String PESSOA_POSSUI_USUARIO_CADASTRADO = "06.5";
	private static String LOGIN_EXISTENTE = "06.6";
	private static String SENHAS_DIFERENTES = "06.7";
	
	
	@Transactional
	public void executar(CadastrarUsuario cadastrarUsuario) throws NegocioException {
		Usuario usuario = new Usuario();
		Papel papel = papelDao.getById(cadastrarUsuario.getIdPapel());
		
		usuario.setIsAtivo(true);
		usuario.setLogin(cadastrarUsuario.getLogin());
		usuario.setSenha(new BCryptPasswordEncoder().encode(cadastrarUsuario.getSenha()));
		usuario.setTentativasLogin(0);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId(cadastrarUsuario.getIdPessoa());
		
		usuario.setPessoa(pessoa);
		usuario.addPapel(papel);
		
		usuarioDao.persist(usuario);
		
	}

	@Transactional(readOnly= true)
	public void validar(CadastrarUsuario cadastrarUsuario) throws NegocioException {
		if (StringUtils.isBlank(cadastrarUsuario.getLogin())) {
			throw new NegocioException(LOGIN_VAZIO);
		}
		if (StringUtils.isBlank(cadastrarUsuario.getSenha())) {
			throw new NegocioException(SENHA_VAZIO);
		}
		if (cadastrarUsuario.getIdPessoa() == null || cadastrarUsuario.getIdPessoa().equals(new Long(0))) {
			throw new NegocioException(PESSOA_VAZIO);
		}
		if (cadastrarUsuario.getIdPapel() == null || cadastrarUsuario.getIdPapel().equals(new Long(0))) {
			throw new NegocioException(PAPEL_VAZIO);
		}
		if (usuarioDao.findByIdFuncionario(cadastrarUsuario.getIdPessoa()) != null) {
			throw new NegocioException(PESSOA_POSSUI_USUARIO_CADASTRADO);
		}
		if (usuarioDao.findByLogin(cadastrarUsuario.getLogin()) != null) {
			throw new NegocioException(LOGIN_EXISTENTE);
		}
		if (!cadastrarUsuario.getSenha().equals(cadastrarUsuario.getSenhaConf()))
			throw new NegocioException(SENHAS_DIFERENTES);
	}

}
