package br.ufrn.ct.hefesto.service.pessoa;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.request.pessoa.ImportarLocalizacoesPessoa;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import br.ufrn.ct.hefesto.persistence.dao.UnidadeDao;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import dev.modulo.adaptador.apiufrn.dto.LocalizacaoServidorDTO;
import dev.modulo.adaptador.apiufrn.repository.PessoaUnidadeAPIUFRNRepository;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("ImportarLocalizacoesPessoa")
public class ServiceImportarLocalizacoesPessoa implements IServiceVoid<ImportarLocalizacoesPessoa>{
	@Autowired
	private PessoaUnidadeAPIUFRNRepository api;
	
	@Autowired
	private PessoaDao pessoaDao;
	
	@Autowired
	private UnidadeDao setorDao;
	
	@Transactional
	public void executar(ImportarLocalizacoesPessoa request) throws NegocioException {
		for (Long idPessoaUfrn : request.getIdsPessoaUfrn()) {
			if(idPessoaUfrn != null) {
				for (LocalizacaoServidorDTO localizacao : api.retornaLocalizacoesDeServidoresAtivosPorIdServidor(Integer.parseInt(idPessoaUfrn.toString()), request.getClientId(), request.getClientSecret())) {
					if(localizacao != null) {
						Pessoa pessoa = new Pessoa();
						pessoa = pessoaDao.findByIdUfrn(idPessoaUfrn);
						pessoa.setUnidadeLocalizacao(setorDao.findByIdUfrn(localizacao.getIdUnidadeLocalizacao()));
						
						pessoaDao.update(pessoa);
					}
				}
			}
		}
		
	}

	public void validar(ImportarLocalizacoesPessoa request) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
