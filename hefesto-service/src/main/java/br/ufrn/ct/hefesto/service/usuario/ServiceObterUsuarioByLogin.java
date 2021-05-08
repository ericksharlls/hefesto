package br.ufrn.ct.hefesto.service.usuario;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.PapelModel;
import br.ufrn.ct.hefesto.model.UsuarioModel;
import br.ufrn.ct.hefesto.model.request.usuario.ObterUsuarioByLogin;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import br.ufrn.ct.hefesto.persistence.dao.UsuarioDao;
import br.ufrn.ct.hefesto.persistence.entity.Papel;
import br.ufrn.ct.hefesto.persistence.entity.Usuario;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceObter;

@Service("ObterUsuarioByLogin")
public class ServiceObterUsuarioByLogin implements IServiceObter<UsuarioModel, ObterUsuarioByLogin> {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private PessoaDao pessoaDao;

	@Transactional(readOnly= true)
	public UsuarioModel obter(ObterUsuarioByLogin request) throws NegocioException {
		Usuario usuario = this.usuarioDao.findByLogin(request.getLogin());
		UsuarioModel retorno = null;
		
		if (usuario != null) {
			retorno = new UsuarioModel();
			retorno.setId(usuario.getId());
			retorno.setIdPessoa(usuario.getPessoa().getId());
			retorno.setLogin(usuario.getLogin());	
			retorno.setSenha(usuario.getSenha());
			retorno.setIsAtivo(usuario.getIsAtivo());
			retorno.setIdFuncionario(usuario.getPessoa().getId());
			retorno.setNomePessoa(this.pessoaDao.getById(usuario.getPessoa().getId()).getNome());
		
			Set<PapelModel> listaPapeis = new HashSet<PapelModel>(0);
			for (Papel papel : usuario.getPapeis()) {
				PapelModel papelModel = new PapelModel();
				papelModel.setId(papel.getId());
				papelModel.setNome(papel.getNome());
				papelModel.setDescricao(papel.getDescricao());
				listaPapeis.add(papelModel);
			}
			retorno.setPapeis(listaPapeis);
			return retorno;
		}else {
			return null;
		}
	}

}
