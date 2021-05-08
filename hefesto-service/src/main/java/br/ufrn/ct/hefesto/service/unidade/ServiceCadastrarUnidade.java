package br.ufrn.ct.hefesto.service.unidade;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.request.unidade.CadastrarUnidade;
import br.ufrn.ct.hefesto.persistence.dao.UnidadeDao;
import br.ufrn.ct.hefesto.persistence.entity.Unidade;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("CadastrarUnidade")
public class ServiceCadastrarUnidade implements IServiceVoid<CadastrarUnidade>{

	@Autowired
	UnidadeDao setorDao;
	
	private static String NOME_SETOR_VAZIO = "03.1";
	private static String DESCRICAO_SETOR_VAZIO = "03.2";
	private static String NOME_SETOR_TAMANHO_EXCEDIDO = "03.5";
	private static String DESCRICAO_TAMANHO_EXCEDIDO = "03.6";
	
	@Transactional
	public void executar(CadastrarUnidade cadastrarUnidade) throws NegocioException {
		Unidade unidade = new Unidade();
		unidade.setFazAtendimento(false);
		unidade.setUnidadeCusto(cadastrarUnidade.getIsUnidadeCusto());
		unidade.setNome(cadastrarUnidade.getNome());
		unidade.setDescricao(cadastrarUnidade.getDescricao());
		
		setorDao.persist(unidade);
	
	}
	
	public void validar(CadastrarUnidade request) throws NegocioException {
		if (StringUtils.isEmpty(request.getNome())) {
			throw new NegocioException(NOME_SETOR_VAZIO);
		}
		if(request.getNome().length() > 45)
			throw new NegocioException(NOME_SETOR_TAMANHO_EXCEDIDO);
		
		if (StringUtils.isEmpty(request.getDescricao())) {
			throw new NegocioException(DESCRICAO_SETOR_VAZIO);
		}
		if(request.getDescricao().length() > 200) {
			throw new NegocioException(DESCRICAO_TAMANHO_EXCEDIDO);
		}
		
		
	}

}
