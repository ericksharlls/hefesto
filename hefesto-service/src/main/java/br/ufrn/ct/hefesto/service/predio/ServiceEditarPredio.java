package br.ufrn.ct.hefesto.service.predio;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.request.predio.EditarPredio;
import br.ufrn.ct.hefesto.persistence.dao.PredioDao;
import br.ufrn.ct.hefesto.persistence.entity.Predio;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("EditarPredio")
public class ServiceEditarPredio implements IServiceVoid<EditarPredio>{

	@Autowired
	PredioDao predioDao;
	
	private static String NOME_PREDIO_VAZIO = "02.1";
	private static String DESCRICAO_PREDIO_VAZIO = "02.2";
	private static String ID_PREDIO_VAZIO = "02.3";
	private static String NOME_TAMANHO_EXCEDIDO = "02.4";
	private static String DESCRICAO_TAMANHO_EXCEDIDO = "02.5";
	
	@Transactional
	public void executar(EditarPredio request) throws NegocioException {
		Predio predio = new Predio();
		
		predio.setId(request.getId());
		predio.setNome(request.getNome());
		predio.setDescricao(request.getDescricao());
		
		this.predioDao.update(predio);
	}

	public void validar(EditarPredio request) throws NegocioException {
		if(request.getId() == null) {
			throw new NegocioException(ID_PREDIO_VAZIO);
		}
		if (StringUtils.isEmpty(request.getNome())) {
			throw new NegocioException(NOME_PREDIO_VAZIO);
		}
		if(request.getNome().length() > 45) {
			throw new NegocioException(NOME_TAMANHO_EXCEDIDO);
		}
		if (StringUtils.isEmpty(request.getDescricao())) {
			throw new NegocioException(DESCRICAO_PREDIO_VAZIO);
		}
		if(request.getDescricao().length() > 70) {
			throw new NegocioException(DESCRICAO_TAMANHO_EXCEDIDO);
		}
		
	}

}
