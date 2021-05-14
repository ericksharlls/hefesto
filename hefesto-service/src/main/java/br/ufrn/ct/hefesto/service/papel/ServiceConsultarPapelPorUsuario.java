package br.ufrn.ct.hefesto.service.papel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.PapelModel;
import br.ufrn.ct.hefesto.model.request.consultarpapelporusuario.ConsultarPapelPorUsuario;
import br.ufrn.ct.hefesto.persistence.dao.PapelDao;
import br.ufrn.ct.hefesto.persistence.entity.Papel;
import dev.modulo.service.interfaces.IServiceConsultar;
import dev.modulo.service.exception.NegocioException;

@Service("ConsultarPapelPorUsuario")
public class ServiceConsultarPapelPorUsuario implements IServiceConsultar<PapelModel, ConsultarPapelPorUsuario>{

	@Autowired
	private PapelDao papelDao;
	
	@Transactional(readOnly= true)
	public List<PapelModel> consultar(ConsultarPapelPorUsuario model) throws NegocioException {
		List<PapelModel> retorno = new ArrayList<PapelModel>(0);
		for (Papel papel : this.papelDao.findByUsuario(model.getIdUsuario())) {
			PapelModel papelModel = new PapelModel();
			papelModel.setId(papel.getId());
			papelModel.setNome(papel.getNome());
			retorno.add(papelModel);
		}
		return retorno;
	}

	public void validar(ConsultarPapelPorUsuario model) throws NegocioException {
		
	}

}
