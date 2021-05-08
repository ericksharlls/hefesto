package br.ufrn.ct.hefesto.service.tiposervico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.TipoServicoModel;
import br.ufrn.ct.hefesto.model.request.tiposervico.ConsultarTipoServico;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import br.ufrn.ct.hefesto.persistence.dao.TipoServicoDao;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import br.ufrn.ct.hefesto.persistence.entity.TipoServico;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultarPaginado;

@Service("ConsultarTipoServico")
public class ServiceConsultarTipoServico implements IServiceConsultarPaginado<TipoServicoModel, ConsultarTipoServico> {

	@Autowired
	private TipoServicoDao tipoServicoDao;
	@Autowired
	private PessoaDao pessoaDao;
	
	@Transactional(readOnly= true)
	public List<TipoServicoModel> consultar(ConsultarTipoServico request) throws NegocioException {
		List<TipoServicoModel> retorno = new ArrayList<TipoServicoModel>(0);
		Pessoa pessoaLogada = new Pessoa();
		pessoaLogada = pessoaDao.getById(request.getIdPessoaLogada());
		
		for (TipoServico tipoServico : this.tipoServicoDao.findBySetorNome(pessoaLogada.getUnidadeLocalizacao().getId(), request.getNome(), request.getStartPosition(), request.getMaxResult())) {
			TipoServicoModel model = new TipoServicoModel();
			model.setId(tipoServico.getId());
			model.setCodigo(tipoServico.getCodigo());
			model.setDescricao(tipoServico.getDescricao());
			model.setNome(tipoServico.getNome());
			model.setIdTipoServicoPai(tipoServico.getTipoServicoPai() != null ? tipoServico.getTipoServicoPai().getId() : null);
			model.setTipoServicoPai(tipoServico.getTipoServicoPai() != null ? tipoServico.getTipoServicoPai().getNome() : null);
			retorno.add(model);
		}
		return retorno;
	}

	@Transactional(readOnly= true)
	public Integer contar(ConsultarTipoServico request) throws NegocioException {
		return this.tipoServicoDao.countByNome(request.getNome());
	}

	public void validar(ConsultarTipoServico request) throws NegocioException {
		
	}

}
