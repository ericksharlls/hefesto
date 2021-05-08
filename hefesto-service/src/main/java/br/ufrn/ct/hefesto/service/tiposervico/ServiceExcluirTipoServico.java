package br.ufrn.ct.hefesto.service.tiposervico;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.request.tiposervico.ExcluirTipoServico;
import br.ufrn.ct.hefesto.persistence.dao.TipoServicoDao;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("ExcluirTipoServico")
public class ServiceExcluirTipoServico implements IServiceVoid<ExcluirTipoServico>{
	
	private static String ID_TIPO_SERVICO_VAZIO = "01.3";
	
	@Autowired
	private TipoServicoDao tipoServicoDao;
	
	@Transactional
	public void executar(ExcluirTipoServico request) throws NegocioException {
		this.tipoServicoDao.delete(request.getId());
	}

	public void validar(ExcluirTipoServico request) throws NegocioException {
		if(request.getId() == null) {
			throw new NegocioException(ID_TIPO_SERVICO_VAZIO);
		}
	}
	
}
