package br.ufrn.ct.hefesto.service.chamado;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.ChamadoModel;
import br.ufrn.ct.hefesto.model.request.chamado.ConsultarChamado;
import br.ufrn.ct.hefesto.persistence.dao.AtendimentoDao;
import br.ufrn.ct.hefesto.persistence.dao.ChamadoDao;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import br.ufrn.ct.hefesto.persistence.dao.PredioDao;
import br.ufrn.ct.hefesto.persistence.entity.Atendimento;
import br.ufrn.ct.hefesto.persistence.entity.Chamado;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultarPaginado;

@Service("ConsultarChamado")
public class ServiceConsultarChamado implements IServiceConsultarPaginado<ChamadoModel, ConsultarChamado>{

	@Autowired
	ChamadoDao chamadoDao;
	@Autowired
	AtendimentoDao atendimentoDao;
	@Autowired
	PredioDao predioDao;
	@Autowired
	PessoaDao pessoaDao;
	
	@Transactional(readOnly= true)
	public List<ChamadoModel> consultar(ConsultarChamado request) throws NegocioException {
		List<ChamadoModel> retorno = new ArrayList<ChamadoModel>(0);
		Pessoa pessoaLogada = this.pessoaDao.getById(request.getIdPessoaLogada());
		List<Chamado> chamados = new ArrayList<Chamado>(0);
		if(request.getAtivo())
			chamados = this.chamadoDao.findAtivosByAllParametros(request.getCodigo(), request.getDataAbertura(), request.getDataConclusao(), request.getIdTipoServico(), pessoaLogada.getUnidadeLocalizacao().getId(), request.getIdSetor(), request.getIdSolicitante(), request.getIdStatus(), request.getStartPosition(), request.getMaxResult());
		if(!request.getAtivo())
			chamados = this.chamadoDao.findByAllParametros(request.getCodigo(), request.getDataAbertura(), request.getDataConclusao(), request.getIdTipoServico(), pessoaLogada.getUnidadeLocalizacao().getId(), request.getIdSetor(), request.getIdSolicitante(), request.getIdStatus(), request.getStartPosition(), request.getMaxResult());
		
		for (Chamado chamado : chamados) {
			ChamadoModel model = new ChamadoModel();
			
			model.setId(chamado.getId());
			model.setCodigo(chamado.getCodigo());
			model.setDataAbertura(chamado.getDataAbertura().toString());
			
			if(chamado.getDataConclusao() != null)
				model.setDataConclusao(chamado.getDataConclusao().toString());
			
			model.setDescricao(chamado.getDescricao());
			model.setMateriais(chamado.getMateriais());
			
			if(chamado.getObservacoes() != null)
				model.setObservacoes(chamado.getObservacoes());
			
			model.setValorServico(chamado.getValorServico());
			//model.setIdSetor(chamado.getSetor().getId());
			model.setIdSetor(chamado.getUnidade().getId());
			//model.setSetor(chamado.getSetor().getNome());
			//model.setSetor(chamado.getSetor().getDescricao());
			model.setSetor(chamado.getUnidade().getDescricao());
			
			//Predio predio = predioDao.getById(chamado.getSetor().getPredio().getId());
			//model.setPredio(predio.getNome());
			
			model.setIdSolicitante(chamado.getSolicitante().getId());
			model.setSolicitante(chamado.getSolicitante().getNome());
			
			model.setIdStatus(chamado.getStatus().getId());
			model.setStatus(chamado.getStatus().getNome());
			
			model.setIdTipoServico(chamado.getTipoServico().getId());
			model.setTipoServico(chamado.getTipoServico().getNome());
			
			List<Long> idsFuncionarios = new ArrayList<Long>(0);
			for (Atendimento atendimento : atendimentoDao.findByChamado(model.getId())) {
				idsFuncionarios.add(atendimento.getPessoa().getId());
			}
			model.setIdsFuncionarios(idsFuncionarios);
			
			retorno.add(model);
		}
		return retorno;
	}
	
	@Transactional(readOnly= true)
	public Integer contar(ConsultarChamado request) throws NegocioException {
		return chamadoDao.countByAllParametros(request.getAtivo(), request.getCodigo(), request.getDataAbertura(), request.getDataConclusao(), 
				  request.getIdTipoServico(), request.getIdSetor(), request.getIdSolicitante(), request.getIdStatus());
	}

	public void validar(ConsultarChamado arg0) throws NegocioException {
		// TODO Auto-generated method stub
	}
}
