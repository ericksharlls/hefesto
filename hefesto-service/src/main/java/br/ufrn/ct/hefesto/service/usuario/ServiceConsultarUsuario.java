package br.ufrn.ct.hefesto.service.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.UsuarioModel;
import br.ufrn.ct.hefesto.model.request.usuario.ConsultarUsuario;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import br.ufrn.ct.hefesto.persistence.dao.UsuarioDao;
import br.ufrn.ct.hefesto.persistence.entity.Papel;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import br.ufrn.ct.hefesto.persistence.entity.Usuario;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultarPaginado;

@Service("ConsultarUsuario")
public class ServiceConsultarUsuario implements IServiceConsultarPaginado<UsuarioModel, ConsultarUsuario>{

	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private PessoaDao pessoaDao;
	
	@Transactional(readOnly= true)
	public List<UsuarioModel> consultar(ConsultarUsuario request) throws NegocioException {
		List<UsuarioModel> retorno = new ArrayList<UsuarioModel>(0);
		
		
		for (Usuario usuario : this.usuarioDao.findByLogin(request.getLogin(), request.getStartPosition(), request.getMaxResult())) {
			UsuarioModel model = new UsuarioModel();
			model.setId(usuario.getId());
			model.setLogin(usuario.getLogin());
			
			Pessoa pessoa = new Pessoa();
			pessoa = pessoaDao.getById(usuario.getPessoa().getId());
			
			model.setNomePessoa(pessoa.getNome());
			model.setIdPessoa(usuario.getPessoa().getId());
			model.setNumDocumento(pessoa.getNumeroDocumento());

			Papel[] papeis = usuario.getPapeis().toArray(new Papel[usuario.getPapeis().size()]);
			
			for (int i = 0; i < papeis.length; i++) {
				if(i == papeis.length - 1 || i == 0) {
					model.setPapelUsuario(papeis[i].getNome());
					model.setIdPapelusuario(papeis[i].getId());
				}else {
					model.setPapelUsuario(papeis[i].getNome() + ",");
					model.setIdPapelusuario(papeis[i].getId());
				}
			}

			retorno.add(model);
		}
		return retorno;
	}

	@Transactional
	public Integer contar(ConsultarUsuario request) throws NegocioException {
		return this.usuarioDao.countByLogin(request.getLogin());
	}

	public void validar(ConsultarUsuario arg0) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
