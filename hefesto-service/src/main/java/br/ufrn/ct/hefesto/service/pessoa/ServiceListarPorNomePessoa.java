package br.ufrn.ct.hefesto.service.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.PessoaModel;
import br.ufrn.ct.hefesto.model.request.pessoa.ListarPorNomePessoa;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultar;

@Service("ListarPorNomePessoa")
public class ServiceListarPorNomePessoa implements IServiceConsultar<PessoaModel, ListarPorNomePessoa>{

	@Autowired
	PessoaDao pessoaDao;
	
	@Transactional
	public List<PessoaModel> consultar(ListarPorNomePessoa request) throws NegocioException {
		List<PessoaModel> retorno = new ArrayList<PessoaModel>(0);
		
		for (Pessoa pessoa : this.pessoaDao.findByNome(request.getNome())) {
			PessoaModel model = new PessoaModel();
			
			model.setId(pessoa.getId());
			model.setNome(pessoa.getNome());
			model.setIdTipoPessoa(pessoa.getTipoPessoa().getId());
			model.setNumeroDocumento(pessoa.getNumeroDocumento());
			model.setMatricula(pessoa.getMatricula());
			
			retorno.add(model);
		}
		
		return retorno;
	}

	public void validar(ListarPorNomePessoa arg0) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
