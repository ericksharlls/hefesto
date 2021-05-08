package br.ufrn.ct.hefesto.service.chamado;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.request.chamado.EditarChamado;
import br.ufrn.ct.hefesto.persistence.dao.AtendimentoDao;
import br.ufrn.ct.hefesto.persistence.dao.ChamadoDao;
import br.ufrn.ct.hefesto.persistence.dao.HistoricoChamadoDao;
import br.ufrn.ct.hefesto.persistence.entity.Atendimento;
import br.ufrn.ct.hefesto.persistence.entity.Chamado;
import br.ufrn.ct.hefesto.persistence.entity.HistoricoChamado;
import br.ufrn.ct.hefesto.persistence.entity.OperacaoChamado;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import br.ufrn.ct.hefesto.persistence.entity.Predio;
import br.ufrn.ct.hefesto.persistence.entity.Unidade;
import br.ufrn.ct.hefesto.persistence.entity.TipoServico;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("EditarChamado")
public class ServiceEditarChamado implements IServiceVoid<EditarChamado>{

	@Autowired
	private ChamadoDao chamadoDao;
	
	@Autowired
	private HistoricoChamadoDao historicoChamadoDao;
	
	@Autowired
	private AtendimentoDao atendimentoDao;
	
	private static String ID_TIPO_SERVICO_VAZIO = "05.1";
	private static String DESCRICAO_VAZIO = "05.2";
	private static String ID_SETOR_VAZIO = "05.3";
	private static String MATERIAIS_TAMANHO_EXCEDIDO = "05.4";
	private static String ID_SOLICITANTE_VAZIO = "05.5";
	private static String DESCRICAO_TAMANHO_EXCEDIDO = "05.7";
	private static String OBSERVACOES_TAMANHO_EXCEDIDO = "05.8";
	
	@Transactional
	public void executar(EditarChamado request) throws NegocioException {
		Chamado chamado = new Chamado();
		chamado = chamadoDao.getById(request.getIdChamado());
		
		chamado.setDescricao(request.getDescricao());
		chamado.setMateriais(request.getMateriais());
		chamado.setObservacoes(request.getObservacoes());
		
		Unidade setor = new Unidade();
		setor.setId(request.getIdSetor());
		Predio predio = new Predio();
		predio.setId(request.getIdPredio());
		//setor.setPredio(predio);
		//chamado.setSetor(setor);
		chamado.setUnidade(setor);
		
		TipoServico tipoServico = new TipoServico();
		tipoServico.setId(request.getIdTipoServico());
		chamado.setTipoServico(tipoServico);
		
		Pessoa solicitante = new Pessoa();
		solicitante.setId(request.getIdSolicitante());
		chamado.setSolicitante(solicitante);
		
		chamadoDao.update(chamado);
		
		if(request.getIdsFuncionarios() != null) {
			List<Atendimento> atendimentos = new ArrayList<Atendimento>(0);
			List<Long> idsAtendimentoFuncionario = new ArrayList<Long>(0);
			
			atendimentos = atendimentoDao.findByChamado(chamado.getId());
			for (Atendimento atendimento : atendimentos) {
				idsAtendimentoFuncionario.add(atendimento.getPessoa().getId());
			}
			
			// adicionando novos tecnicos
			for (Long idFuncionario : request.getIdsFuncionarios()) {
				if(!idsAtendimentoFuncionario.contains(idFuncionario)) {
					Atendimento atendimento = new Atendimento();					
					Pessoa funcionario = new Pessoa();
					funcionario.setId(idFuncionario);

					atendimento.setPessoa(funcionario);
					atendimento.setChamado(chamado);
					
					atendimentoDao.persist(atendimento);
				}
			}
			
			// deletando tecnicos que não estão mais na lista
			for (Long idFuncionario : idsAtendimentoFuncionario) {
				if(!request.getIdsFuncionarios().contains(idFuncionario)) {
					
					for (Atendimento atendimento : atendimentos) {
						if(atendimento.getPessoa().getId() == idFuncionario) {
							atendimentoDao.delete(atendimento);
						}
					}			
				}
			}
		}
		
		HistoricoChamado historicoChamado = new HistoricoChamado();
		OperacaoChamado operacaoChamado = new OperacaoChamado();
		Calendar cal = Calendar.getInstance();
		Pessoa pessoaLogada = new Pessoa();
		pessoaLogada.setId(request.getIdPessoaLogada());
		
		operacaoChamado.setId(2L);
		
		historicoChamado.setHoraRealizacao(cal);
		historicoChamado.setChamado(chamado);
		historicoChamado.setOperacao(operacaoChamado);
		historicoChamado.setPessoa(pessoaLogada);
		
		historicoChamadoDao.persist(historicoChamado);
	}

	public void validar(EditarChamado request) throws NegocioException {
		if (request.getIdTipoServico() == null || request.getIdTipoServico() == 0) {
			throw new NegocioException(ID_TIPO_SERVICO_VAZIO);
		}
		
		if (StringUtils.isEmpty(request.getDescricao())) {
			throw new NegocioException(DESCRICAO_VAZIO);
		}
		if(request.getDescricao().length() > 100)
			throw new NegocioException(DESCRICAO_TAMANHO_EXCEDIDO);
			
//		if (StringUtils.isEmpty(request.getMateriais())) {
//			throw new NegocioException(MATERIAIS_VAZIO);
//		}
		if(request.getMateriais().length() > 200)
			throw new NegocioException(MATERIAIS_TAMANHO_EXCEDIDO);
		
		if(request.getObservacoes().length() > 200)
			throw new NegocioException(OBSERVACOES_TAMANHO_EXCEDIDO);
			
		if (request.getIdSetor() == null || request.getIdSetor() == 0) {
			throw new NegocioException(ID_SETOR_VAZIO);
		}
		if (request.getIdSolicitante() == null || request.getIdSolicitante() == 0) {
			throw new NegocioException(ID_SOLICITANTE_VAZIO);
		}
		
	}

}
