package br.ufrn.ct.hefesto.service.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.PessoaModel;
import br.ufrn.ct.hefesto.model.request.obterpessoa.ObterPessoa;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceObter;

@Service("ObterPessoa")
public class ServiceObterPessoa implements IServiceObter<PessoaModel, ObterPessoa> {

	@Autowired
	private PessoaDao pessoaDao;
	
	@Transactional(readOnly= true)
	public PessoaModel obter(ObterPessoa request) throws NegocioException {
		Pessoa pessoa = this.pessoaDao.getById(request.getId());
		PessoaModel retorno = null;
		
		if (pessoa != null) {
			retorno = new PessoaModel();
			retorno.setId(pessoa.getId());
			retorno.setNome(pessoa.getNome());	
			retorno.setDataNascimento(pessoa.getDataNascimento());
			retorno.setEmail(pessoa.getEmail());
			retorno.setIdTipoPessoa(pessoa.getTipoPessoa().getId());
			retorno.setTipoPessoa(pessoa.getTipoPessoa().getNome());
			retorno.setNumeroDocumento(pessoa.getNumeroDocumento());
			retorno.setMatricula(pessoa.getMatricula());
			retorno.setTelefone(pessoa.getTelefone());
			//retorno.setIdPredio(pessoa.getSetorLocalizacao().getPredio().getId());
			//retorno.setIdPredio(pessoa.getSetorLocalizacao() != null ? pessoa.getSetorLocalizacao().getPredio().getId() : null);
			
			retorno.setIdSetorLocalizacao(pessoa.getUnidadeLocalizacao() != null ? pessoa.getUnidadeLocalizacao().getId() : null);
			retorno.setSetorLocalizacao(pessoa.getUnidadeLocalizacao() != null ? pessoa.getUnidadeLocalizacao().getNome() : null);
			retorno.setIdSetorLotacao(pessoa.getUnidadeLotacao().getId());
			retorno.setSetorLotacao(pessoa.getUnidadeLotacao().getNome());
		}
		
		return retorno;
	}

}
