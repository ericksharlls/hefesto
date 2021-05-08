package br.ufrn.ct.hefesto.service.tiposervico;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.request.tiposervico.CadastrarTipoServico;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import br.ufrn.ct.hefesto.persistence.dao.TipoServicoDao;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import br.ufrn.ct.hefesto.persistence.entity.TipoServico;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("CadastrarTipoServico")
public class ServiceCadastrarTipoServico implements IServiceVoid<CadastrarTipoServico>{

	@Autowired
	private TipoServicoDao tipoServicoDao;
	@Autowired
	private PessoaDao pessoaDao;
	
	private static String NOME_TIPO_SERVICO_VAZIO = "01.1";
	private static String DESCRICAO_TIPO_SERVICO_VAZIO = "01.2";
	private static String NOME_TAMANHO_EXCEDIDO = "01.4";
	private static String DESCRICAO_TIPO_SERVICO_TAMANHO_EXCEDIDO = "01.5";
	
	
	@Transactional
	public void executar(CadastrarTipoServico request) throws NegocioException {
		TipoServico tipoServico = new TipoServico();
		
		Pessoa pessoaLogada = this.pessoaDao.getById(request.getIdPessoaLogada());
		
		TipoServico ultimo = this.tipoServicoDao.findLastBySetor(pessoaLogada.getUnidadeLocalizacao().getId());
		String codigo;
		if(ultimo == null) {
			codigo = "001";
		} else {
			codigo = ultimo.getCodigo().trim();
			int aux = Integer.parseInt(codigo);

			if(Integer.parseInt(codigo) < 9) {
				codigo = "00" + Integer.toString(aux + 1);
			}else if(Integer.parseInt(codigo) < 99) {
				codigo = "0" + Integer.toString(aux + 1);
			}else {
				codigo = Integer.toString(aux + 1);
			}
			
		}

		if(request.getIdTipoServicoPai() != null && request.getIdTipoServicoPai() != 0) {
			TipoServico tipoServicoPai = new TipoServico();
			tipoServicoPai.setId(request.getIdTipoServicoPai());
			tipoServico.setTipoServicoPai(tipoServicoPai);
		}
		tipoServico.setUnidade(pessoaLogada.getUnidadeLocalizacao());
		tipoServico.setCodigo(codigo);
		tipoServico.setDescricao(request.getDescricao());
		tipoServico.setNome(request.getNome());
		this.tipoServicoDao.persist(tipoServico);
	}

	public void validar(CadastrarTipoServico request) throws NegocioException {
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
