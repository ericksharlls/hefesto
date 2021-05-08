package br.ufrn.ct.hefesto.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.UsuarioModel;
import br.ufrn.ct.hefesto.model.request.obterusuario.ObterUsuario;
import br.ufrn.ct.hefesto.persistence.dao.PapelDao;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import br.ufrn.ct.hefesto.persistence.dao.UsuarioDao;
import br.ufrn.ct.hefesto.persistence.entity.Papel;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import br.ufrn.ct.hefesto.persistence.entity.Usuario;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceObter;

@Service("ObterUsuario")
public class ServiceObterUsuario implements IServiceObter<UsuarioModel, ObterUsuario> {
	
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	PapelDao papelDao;
	@Autowired
	PessoaDao pessoaDao;

	@Transactional(readOnly= true)
	public UsuarioModel obter(ObterUsuario request) throws NegocioException {
		Usuario usuario = this.usuarioDao.findByLogin(request.getLogin());
		UsuarioModel retorno = null;
		
		if (usuario != null) {
			retorno = new UsuarioModel();
			retorno.setId(usuario.getId());
			retorno.setLogin(usuario.getLogin());	
			retorno.setSenha(usuario.getSenha());
			retorno.setIsAtivo(usuario.getIsAtivo());
			retorno.setIdFuncionario(usuario.getPessoa().getId());
		}
		
		Pessoa pessoa = new Pessoa();
		pessoa = pessoaDao.getById(usuario.getPessoa().getId());
		
		retorno.setNomePessoa(pessoa.getNome());
		retorno.setIdPessoa(usuario.getPessoa().getId());
		retorno.setNumDocumento(pessoa.getNumeroDocumento());
		
		Papel[] papeis = usuario.getPapeis().toArray(new Papel[usuario.getPapeis().size()]);
		
		for (int i = 0; i < papeis.length; i++) {
			if(i == papeis.length - 1 || i == 0) {
				retorno.setPapelUsuario(papeis[i].getNome());
				retorno.setIdPapelusuario(papeis[i].getId());
			}else {
				retorno.setPapelUsuario(papeis[i].getNome() + ",");
				retorno.setIdPapelusuario(papeis[i].getId());
			}
		}
		
		return retorno;
	}

}
