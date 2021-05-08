package br.ufrn.ct.hefesto.service.unidade;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.request.unidade.EditarUnidade;
import br.ufrn.ct.hefesto.persistence.dao.UnidadeDao;
import br.ufrn.ct.hefesto.persistence.entity.Unidade;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("EditarUnidade")
public class ServiceEditarUnidade implements IServiceVoid<EditarUnidade>{

	@Autowired
	private UnidadeDao unidadeDao;
	
	private static String NOME_SETOR_VAZIO = "03.1";
	private static String DESCRICAO_SETOR_VAZIO = "03.2";
	private static String ID_SETOR_VAZIO = "03.3";
	
	@Transactional
	public void executar(EditarUnidade request) throws NegocioException {
		Unidade unidade = this.unidadeDao.getById(request.getId());
		
		/*
		String codigo = "";
		
		if(request.getFazAtendimento()) {
			Unidade ultimo = this.setorDao.findLastByPredio(request.getIdPredio());
			
			if(ultimo == null) {
				codigo = "01";
				codigo = request.getIdPredio() + codigo;
			}else {
				codigo = ultimo.getCodigo();
				int aux = Integer.parseInt(codigo) + 1;
				codigo = Integer.toString(aux);
			}
		}
		setor.setCodigo(codigo); */
		
		unidade.setFazAtendimento(request.getFazAtendimento());
		unidade.setNome(request.getNome());
		unidade.setDescricao(request.getDescricao());
		unidade.setUnidadeCusto(request.getIsUnidadeCusto());
		
		this.unidadeDao.update(unidade);
	}

	public void validar(EditarUnidade request) throws NegocioException {
		if(request.getId() == null) {
			throw new NegocioException(ID_SETOR_VAZIO);
		}
		if (StringUtils.isEmpty(request.getNome())) {
			throw new NegocioException(NOME_SETOR_VAZIO);
		}
		if (StringUtils.isEmpty(request.getDescricao())) {
			throw new NegocioException(DESCRICAO_SETOR_VAZIO);
		}
		
	}

}
