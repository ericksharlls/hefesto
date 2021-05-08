package br.ufrn.ct.hefesto.service.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.PapelModel;
import br.ufrn.ct.hefesto.model.request.usuario.ListarPapel;
import br.ufrn.ct.hefesto.persistence.dao.PapelDao;
import br.ufrn.ct.hefesto.persistence.entity.Papel;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultar;

@Service("ListarPapel")
public class ServiceListarPapel implements IServiceConsultar<PapelModel, ListarPapel>{

	@Autowired
	PapelDao papelDao;
	
	@Transactional
	public List<PapelModel> consultar(ListarPapel request) throws NegocioException {
		List<PapelModel> retorno = new ArrayList<PapelModel>(0);
		for (Papel papel : this.papelDao.findAll()) {
			PapelModel model = new PapelModel();
			
			model.setId(papel.getId());
			model.setDescricao(papel.getDescricao());
			model.setNome(papel.getNome());
			
			retorno.add(model);
		}
		
		return retorno;
	}

	public void validar(ListarPapel request) throws NegocioException {
		// TODO Auto-generated method stub
		
	}

}
