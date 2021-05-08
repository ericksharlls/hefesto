package br.ufrn.ct.hefesto.web.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.controller.UsuarioSistema;
import br.ufrn.ct.hefesto.model.PapelModel;
import br.ufrn.ct.hefesto.model.UsuarioModel;
import br.ufrn.ct.hefesto.model.request.usuario.ObterUsuarioByLogin;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Service
public class HefestoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private ContextService contextService;


	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ObterUsuarioByLogin requestObterUsuarioByLogin = new ObterUsuarioByLogin();
		UsuarioModel usuarioModel = new UsuarioModel();
		
		requestObterUsuarioByLogin.setLogin(username);
		
		try {
			usuarioModel = (UsuarioModel) contextService.obter(requestObterUsuarioByLogin);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		
		if (usuarioModel == null) {
			throw new UsernameNotFoundException("Usuário não foi encontrado!");
		}
		
		return new UsuarioSistema(usuarioModel.getNomePessoa(), usuarioModel.getIdPessoa(), usuarioModel.getLogin(), usuarioModel.getSenha(), usuarioModel.getIsAtivo(), authorities(usuarioModel));
	}
	
	public Collection<? extends GrantedAuthority> authorities(UsuarioModel usuarioModel) {
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>(0);
		
		for (PapelModel p : usuarioModel.getPapeis()) {
			auths.add(new SimpleGrantedAuthority("ROLE_" + p.getNome()));
		}
		return auths;
	}

}
