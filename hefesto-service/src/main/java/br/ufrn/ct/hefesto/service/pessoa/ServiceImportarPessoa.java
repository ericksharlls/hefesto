package br.ufrn.ct.hefesto.service.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.request.pessoa.ImportarPessoa;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import br.ufrn.ct.hefesto.persistence.entity.Unidade;
import br.ufrn.ct.hefesto.persistence.entity.TipoPessoa;
import dev.modulo.adaptador.apiufrn.dto.ServidorDTO;
import dev.modulo.adaptador.apiufrn.repository.PessoaUnidadeAPIUFRNRepository;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("ImportarPessoa")
public class ServiceImportarPessoa implements IServiceVoid<ImportarPessoa>{
	@Autowired
	private PessoaUnidadeAPIUFRNRepository api;
	@Autowired
	private PessoaDao pessoaDao;
	
	@Transactional
	public void executar(ImportarPessoa request) throws NegocioException {
		for (SetorModel setorModel : request.getSetores()) {
			
			if(setorModel.getIdSetorUfrn() != null) {
				for (ServidorDTO servidor : api.retornaServidoresAtivosPorIdUnidadeLotacao(Integer.parseInt(setorModel.getIdSetorUfrn().toString()), request.getClientId(), request.getClientSecret())) {
					
					if(!request.getIdsPessoasJaCadastrados().contains(Long.parseLong(servidor.getIdServidor().toString()))) {
						Pessoa pessoa = new Pessoa();
						pessoa.setMatricula(servidor.getSiape());
						pessoa.setNome(servidor.getNome());
						String cpf = servidor.getCpf();
						while(cpf.length() < 11) {
							cpf = "0"+cpf;
						}
						pessoa.setNumeroDocumento(cpf);
						pessoa.setTelefone("");
						pessoa.setEmail(servidor.getEmail());
						pessoa.setIdPessoaUfrn(Long.parseLong(servidor.getIdServidor().toString()));
						
						TipoPessoa tipoPessoa = new TipoPessoa();
						tipoPessoa.setId(Long.parseLong(servidor.getIdCategoria().toString()));
						pessoa.setTipoPessoa(tipoPessoa);
						
						Unidade setor = new Unidade();
						setor.setId(setorModel.getId());
						pessoa.setUnidadeLotacao(setor);
						
						pessoaDao.persist(pessoa);
						
					}
				}
			}
		}
		
	}

	public void validar(ImportarPessoa request) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

	public PessoaDao getPessoaDao() {
		return pessoaDao;
	}

	public void setPessoaDao(PessoaDao pessoaDao) {
		this.pessoaDao = pessoaDao;
	}


}
