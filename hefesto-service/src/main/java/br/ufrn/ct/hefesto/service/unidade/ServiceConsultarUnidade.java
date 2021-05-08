package br.ufrn.ct.hefesto.service.unidade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.request.unidade.ConsultarUnidade;
import br.ufrn.ct.hefesto.persistence.dao.UnidadeDao;
import br.ufrn.ct.hefesto.persistence.entity.Unidade;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultarPaginado;

@Service("ConsultarUnidade")
public class ServiceConsultarUnidade implements IServiceConsultarPaginado<SetorModel, ConsultarUnidade>{

	@Autowired
	private UnidadeDao setorDao;
	
	@Transactional(readOnly= true)
	public List<SetorModel> consultar(ConsultarUnidade request) throws NegocioException {
		List<SetorModel> retorno = new ArrayList<SetorModel>(0);
		for (Unidade setor : this.setorDao.findByNomeCodigoUnidadeCusto(request.getNome(), request.getCodigo(), request.getIsUnidadeCusto(), request.getStartPosition(), request.getMaxResult())) {
			SetorModel model = new SetorModel();
			model.setId(setor.getId());
			model.setNome(setor.getNome());
			model.setCodigo(setor.getCodigo());
			model.setFazAtendimento(setor.getFazAtendimento());
			model.setIsUnidadeCusto(setor.getUnidadeCusto());
			model.setDescricao(setor.getDescricao());
			model.setIsUnidadeCusto(setor.getUnidadeCusto());
			retorno.add(model);
		}
		return retorno;
	}

	@Transactional(readOnly= true)
	public Integer contar(ConsultarUnidade request) throws NegocioException {
		return this.setorDao.countByNomeCodigoUnidadeCusto(request.getNome(), request.getCodigo(), request.getIsUnidadeCusto());
	}

	public void validar(ConsultarUnidade arg0) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
