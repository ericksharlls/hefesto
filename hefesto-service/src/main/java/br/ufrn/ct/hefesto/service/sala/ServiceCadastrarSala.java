package br.ufrn.ct.hefesto.service.sala;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.request.sala.CadastrarSala;
import br.ufrn.ct.hefesto.persistence.dao.SalaDao;
import br.ufrn.ct.hefesto.persistence.entity.Predio;
import br.ufrn.ct.hefesto.persistence.entity.Sala;
import br.ufrn.ct.hefesto.persistence.entity.Unidade;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("CadastrarSala")
public class ServiceCadastrarSala implements IServiceVoid<CadastrarSala>{

	@Autowired
	private SalaDao salaDao;
	
	private static String NOME_SALA_VAZIO = "09.1";
	private static String DESCRICAO_SALA_VAZIO = "09.2";
	private static String PREDIO_SALA_VAZIO = "09.3";
	private static String UNIDADE_SALA_VAZIO = "09.4";
	private static String NOME_TAMANHO_EXCEDIDO = "09.5";
	private static String DESCRICAO_TAMANHO_EXCEDIDO = "09.6";
	
	@Transactional
	public void executar(CadastrarSala request) throws NegocioException {
		
		Sala sala = new Sala();
		Predio predio = new Predio();
		Unidade unidade = new Unidade();
		
		sala.setNome(request.getNome());
		sala.setDescricao(request.getDescricao());
		predio.setId(request.getIdPredio());
		sala.setPredio(predio);
		unidade.setId(request.getIdUnidade());
		sala.setUnidade(unidade);
		
		this.salaDao.persist(sala);
	}

	public void validar(CadastrarSala request) throws NegocioException {
		if (StringUtils.isEmpty(request.getNome())) {
			throw new NegocioException(NOME_SALA_VAZIO);
		}
		if(request.getNome().length() > 45) {
			throw new NegocioException(NOME_TAMANHO_EXCEDIDO);
		}
		if (StringUtils.isEmpty(request.getDescricao())) {
			throw new NegocioException(DESCRICAO_SALA_VAZIO);
		}
		if(request.getDescricao().length() > 70) {
			throw new NegocioException(DESCRICAO_TAMANHO_EXCEDIDO);
		}
		if (request.getIdPredio() == null || request.getIdPredio() == 0) {
			throw new NegocioException(PREDIO_SALA_VAZIO);
		}
		if (request.getIdUnidade() == null || request.getIdUnidade() == 0) {
			throw new NegocioException(UNIDADE_SALA_VAZIO);
		}
	}


}
