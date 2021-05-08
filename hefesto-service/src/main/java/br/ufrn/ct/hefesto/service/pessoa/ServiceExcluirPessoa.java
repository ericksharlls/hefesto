package br.ufrn.ct.hefesto.service.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.request.pessoa.ExcluirPessoa;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("ExcluirPessoa")
public class ServiceExcluirPessoa implements IServiceVoid<ExcluirPessoa>{

	@Autowired
	PessoaDao pessoaDao;
	
	private static String ID_PESSOA_VAZIO = "04.6";
	
	@Transactional
	public void executar(ExcluirPessoa request) throws NegocioException {
		pessoaDao.delete(request.getId());
		
	}

	public void validar(ExcluirPessoa request) throws NegocioException {
		if (request.getId() == null || request.getId() == 0) {
			throw new NegocioException(ID_PESSOA_VAZIO);
		}
	}

}
