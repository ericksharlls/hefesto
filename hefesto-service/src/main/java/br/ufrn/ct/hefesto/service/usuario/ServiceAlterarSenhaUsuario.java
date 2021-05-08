package br.ufrn.ct.hefesto.service.usuario;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.request.usuario.AlterarSenhaUsuario;
import br.ufrn.ct.hefesto.persistence.dao.UsuarioDao;
import br.ufrn.ct.hefesto.persistence.entity.Usuario;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("AlterarSenhaUsuario")
public class ServiceAlterarSenhaUsuario implements IServiceVoid<AlterarSenhaUsuario>{

	private static String SENHA_VAZIO = "08.1";
	private static String SENHAS_INCOMPATIVEIS = "08.2";
	private static String OPRECAO_INVALIDA = "08.3";
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Transactional
	public void executar(AlterarSenhaUsuario request) throws NegocioException {
		
		Usuario usuario = new Usuario();
		
		usuario = this.usuarioDao.findByIdFuncionario(request.getIdPessoaEmEdicao());
		usuario.setSenha(new BCryptPasswordEncoder().encode(request.getSenha()));
		
		this.usuarioDao.update(usuario);
	}

	public void validar(AlterarSenhaUsuario request) throws NegocioException {
		
		if (StringUtils.isBlank(request.getSenha())) {
			throw new NegocioException(SENHA_VAZIO);
		}
		if (!request.getSenha().equals(request.getSenhaConfirmacao())) {
			
			throw new NegocioException(SENHAS_INCOMPATIVEIS);
		}

		if(!request.getIdPessoaEmEdicao().equals(request.getIdPessoaLogado())) {
			throw new NegocioException(OPRECAO_INVALIDA);
		}
		
	}

}
