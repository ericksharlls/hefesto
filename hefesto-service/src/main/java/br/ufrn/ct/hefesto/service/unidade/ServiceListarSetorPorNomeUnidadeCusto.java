package br.ufrn.ct.hefesto.service.unidade;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.request.unidade.ListarSetorPorNomeUnidadeCusto;
import br.ufrn.ct.hefesto.persistence.dao.UnidadeDao;
import br.ufrn.ct.hefesto.persistence.entity.Unidade;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultar;

@Service("ListarSetorPorNomeUnidadeCusto")
public class ServiceListarSetorPorNomeUnidadeCusto implements IServiceConsultar<SetorModel, ListarSetorPorNomeUnidadeCusto> {

	@Autowired
	private UnidadeDao setorDao;
	
	@Transactional
	public List<SetorModel> consultar(ListarSetorPorNomeUnidadeCusto request) throws NegocioException {
		List<SetorModel> retorno = new ArrayList<SetorModel>(0);
		
		for (Unidade setor : this.setorDao.findByNomeUnidadeCusto(request.getNome())) {
			SetorModel model = new SetorModel();
			
			model.setId(setor.getId());
			model.setNome(setor.getNome());
			model.setDescricao(setor.getDescricao());
			model.setCodigo(setor.getCodigo());
			
			retorno.add(model);
		}
		
		return retorno;
	}

	public void validar(ListarSetorPorNomeUnidadeCusto request) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
