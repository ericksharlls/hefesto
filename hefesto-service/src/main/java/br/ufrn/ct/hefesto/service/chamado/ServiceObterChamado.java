package br.ufrn.ct.hefesto.service.chamado;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.ChamadoModel;
import br.ufrn.ct.hefesto.model.request.chamado.ObterChamado;
import br.ufrn.ct.hefesto.persistence.dao.AtendimentoDao;
import br.ufrn.ct.hefesto.persistence.dao.ChamadoDao;
import br.ufrn.ct.hefesto.persistence.dao.PredioDao;
import br.ufrn.ct.hefesto.persistence.entity.Atendimento;
import br.ufrn.ct.hefesto.persistence.entity.Chamado;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceObter;

@Service("ObterChamado")
public class ServiceObterChamado implements IServiceObter<ChamadoModel, ObterChamado>{

	@Autowired
	ChamadoDao chamadoDao;
	@Autowired
	PredioDao predioDao;
	@Autowired
	AtendimentoDao atendimentoDao;
	
	@Transactional
	public ChamadoModel obter(ObterChamado request) throws NegocioException {
		Chamado chamado = chamadoDao.getById(request.getId());
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
		//model.setSetor(chamado.getSetor().getNome());
		model.setIdSetor(chamado.getUnidade().getId());
		model.setSetor(chamado.getUnidade().getNome());
		
		//Predio predio = predioDao.getById(chamado.getSetor().getPredio().getId());
		//model.setIdPredio(predio.getId());
		//model.setPredio(predio.getNome());
		
		model.setIdSolicitante(chamado.getSolicitante().getId());
		model.setSolicitante(chamado.getSolicitante().getNome());
		
		model.setIdStatus(chamado.getStatus().getId());
		model.setStatus(chamado.getStatus().getNome());
		
		model.setIdTipoServico(chamado.getTipoServico().getId());
		model.setTipoServico(chamado.getTipoServico().getNome());
		
		model.setIdTipoServicoPai(chamado.getTipoServico().getTipoServicoPai().getId());
		
		List<Long> idsFuncionarios = new ArrayList<Long>(0);
		for (Atendimento atendimento : atendimentoDao.findByChamado(model.getId())) {
			idsFuncionarios.add(atendimento.getPessoa().getId());
		}
		model.setIdsFuncionarios(idsFuncionarios);
		return model;
	}

}
