package br.ufrn.ct.hefesto.service.tiposervico;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.request.tiposervico.EditarTipoServico;
import br.ufrn.ct.hefesto.persistence.dao.TipoServicoDao;
import br.ufrn.ct.hefesto.persistence.entity.TipoServico;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("EditarTipoServico")
public class ServiceEditarTipoServico implements IServiceVoid<EditarTipoServico>{

	@Autowired
	TipoServicoDao tipoServicoDao;
	
	private static String NOME_TIPO_SERVICO_VAZIO = "01.1";
	private static String DESCRICAO_TIPO_SERVICO_VAZIO = "01.2";
	private static String ID_TIPO_SERVICO_VAZIO = "01.3";
	private static String NOME_TAMANHO_EXCEDIDO = "01.4";
	private static String DESCRICAO_TIPO_SERVICO_TAMANHO_EXCEDIDO = "01.5";
	
	@Transactional
	public void executar(EditarTipoServico request) throws NegocioException {
		TipoServico tipoServico = new TipoServico();
		tipoServico = tipoServicoDao.getById(request.getId());
		
		tipoServico.setId(request.getId());
		tipoServico.setNome(request.getNome());
		tipoServico.setDescricao(request.getDescricao());
		if(request.getIdTipoServicoPai() != null && request.getIdTipoServicoPai() != 0) {
			TipoServico tipoServicoPai = tipoServicoDao.getById(request.getIdTipoServicoPai());
			tipoServico.setTipoServicoPai(tipoServicoPai);
		}else {
			tipoServico.setTipoServicoPai(null);
		}
		
		this.tipoServicoDao.update(tipoServico);
		
	}

	public void validar(EditarTipoServico request) throws NegocioException {
		if(request.getId() == null) {
			throw new NegocioException(ID_TIPO_SERVICO_VAZIO);
		}
		if (StringUtils.isEmpty(request.getNome())) {
			throw new NegocioException(NOME_TIPO_SERVICO_VAZIO);
		}
		if(request.getNome().length() > 45) {
			throw new NegocioException(NOME_TAMANHO_EXCEDIDO);
		}
		if (StringUtils.isEmpty(request.getDescricao())) {
			throw new NegocioException(DESCRICAO_TIPO_SERVICO_VAZIO);
		}
		if(request.getDescricao().length() > 70) {
			throw new NegocioException(DESCRICAO_TIPO_SERVICO_TAMANHO_EXCEDIDO);
		}
		
	}

}
