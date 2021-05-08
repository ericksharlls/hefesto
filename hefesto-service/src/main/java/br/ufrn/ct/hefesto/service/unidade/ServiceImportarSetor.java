package br.ufrn.ct.hefesto.service.unidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.request.unidade.ImportarSetor;
import br.ufrn.ct.hefesto.persistence.dao.UnidadeDao;
import br.ufrn.ct.hefesto.persistence.entity.Unidade;
import dev.modulo.adaptador.apiufrn.dto.DepartamentoDTO;
import dev.modulo.adaptador.apiufrn.repository.PessoaUnidadeAPIUFRNRepository;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("ImportarSetor")
public class ServiceImportarSetor implements IServiceVoid<ImportarSetor>{
	@Autowired
	private PessoaUnidadeAPIUFRNRepository api;

	@Autowired
	private UnidadeDao setorDao;
	
	@Transactional
	public void executar(ImportarSetor request) throws NegocioException {

		for (DepartamentoDTO departamento : api.retornaTodosDepartamentosPorDoisPrimeirosDigitosCodigoCentro(request.getIdCentro().toString(), request.getClientId(), request.getClientSecret())) {
			
			if(!request.getIdsSetoresJaCadastrados().contains(Long.parseLong(departamento.getIdUnidade().toString()))){			
				Unidade unidade = new Unidade();
				
				unidade.setCodigo(departamento.getCodigoUnidade().toString());
				unidade.setNome(departamento.getNomeUnidade());
				unidade.setSigla(departamento.getSigla());
				unidade.setFazAtendimento(false);
				unidade.setDescricao(departamento.getNomeUnidade());
				unidade.setIdUnidadeUfrn(Long.parseLong(departamento.getIdUnidade().toString()));
				unidade.setUnidadeCusto(false);
				
				setorDao.persist(unidade);
			}else {
				Unidade setor = setorDao.findByIdUfrn(departamento.getIdUnidade());
				
				setor.setCodigo(departamento.getCodigoUnidade().toString());
				setor.setNome(departamento.getNomeUnidade());
				setor.setDescricao(departamento.getNomeUnidade());
				setor.setSigla(departamento.getSigla());
				
				setorDao.update(setor);
			}
			
		}
	}

	public void validar(ImportarSetor request) throws NegocioException {
		
	}

	public UnidadeDao getSetorDao() {
		return setorDao;
	}

	public void setSetorDao(UnidadeDao setorDao) {
		this.setorDao = setorDao;
	}

}
