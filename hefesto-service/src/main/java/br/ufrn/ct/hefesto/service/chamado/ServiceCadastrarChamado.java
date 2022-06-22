package br.ufrn.ct.hefesto.service.chamado;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.ct.hefesto.model.request.chamado.CadastrarChamado;
import br.ufrn.ct.hefesto.persistence.dao.AtendimentoDao;
import br.ufrn.ct.hefesto.persistence.dao.ChamadoDao;
import br.ufrn.ct.hefesto.persistence.dao.HistoricoChamadoDao;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import br.ufrn.ct.hefesto.persistence.dao.TipoServicoDao;
import br.ufrn.ct.hefesto.persistence.entity.Atendimento;
import br.ufrn.ct.hefesto.persistence.entity.Chamado;
import br.ufrn.ct.hefesto.persistence.entity.HistoricoChamado;
import br.ufrn.ct.hefesto.persistence.entity.OperacaoChamado;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import br.ufrn.ct.hefesto.persistence.entity.Sala;
import br.ufrn.ct.hefesto.persistence.entity.Unidade;
import br.ufrn.ct.hefesto.persistence.entity.Status;
import br.ufrn.ct.hefesto.persistence.entity.TipoServico;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;

@Service("CadastrarChamado")
public class ServiceCadastrarChamado implements IServiceVoid<CadastrarChamado>{

	@Autowired
	private ChamadoDao chamadoDao;
	@Autowired
	private TipoServicoDao tipoServicoDao;
	@Autowired
	private HistoricoChamadoDao historicoChamadoDao;
	@Autowired
	private AtendimentoDao atendimentoDao;
	@Autowired
	private PessoaDao pessoaDao;
	
	private static String ID_TIPO_SERVICO_VAZIO = "05.1";
	private static String DESCRICAO_VAZIO = "05.2";
	private static String ID_SETOR_VAZIO = "05.3";
	private static String MATERIAIS_TAMANHO_EXCEDIDO = "05.4";
	private static String ID_SOLICITANTE_VAZIO = "05.5";
	private static String DESCRICAO_TAMANHO_EXCEDIDO = "05.7";
	private static String OBSERVACOES_TAMANHO_EXCEDIDO = "05.8";
	
	@Transactional
	public void executar(CadastrarChamado request) throws NegocioException {
		
		Chamado chamado = new Chamado();
		
		chamado.setDescricao(request.getDescricao());
		chamado.setMateriais(request.getMateriais());
		chamado.setObservacoes(request.getObservacoes());
		
		Calendar cal = Calendar.getInstance();
		chamado.setDataAbertura(cal);
		
		Sala sala = new Sala();
		sala.setId(request.getIdSala());
		chamado.setSala(sala);
		
		TipoServico tipoServico = new TipoServico();
		tipoServico.setId(request.getIdTipoServico());
		chamado.setTipoServico(tipoServico);
		
		Pessoa solicitante = new Pessoa();
		solicitante.setId(request.getIdSolicitante());
		chamado.setSolicitante(solicitante);
		
		Status status = new Status();
		status.setId(1L);
		chamado.setStatus(status);
		
		String codigo;
		String ano = Integer.toString(cal.get(Calendar.YEAR));
		Pessoa pessoaLogada = this.pessoaDao.getById(request.getIdPessoaLogada());
		
		Unidade setor = new Unidade();
		setor.setId(pessoaLogada.getUnidadeLocalizacao().getId());
		//setor.setPredio(predio);
		//chamado.setSetor(setor);
		chamado.setUnidade(setor);
		
		Chamado ultimo = this.chamadoDao.findLastByAnoSetorTipoServico(Integer.parseInt(ano), pessoaLogada.getUnidadeLocalizacao().getId(), request.getIdTipoServico());
		//System.out.println("#### Ultimo chamado: " + ultimo.getCodigo());

		if(ultimo == null) {
			System.out.println("###### no if");
			String codigoTipoServico = this.tipoServicoDao.getById(tipoServico.getId()).getCodigo();
			String sequence = "001";
			
			codigo = ano + codigoTipoServico + sequence;
		}else {
			System.out.println("###### no else");
			codigo = ultimo.getCodigo();
			Long aux = Long.parseLong(codigo) + 1;
			codigo = Long.toString(aux);
		}
		
		chamado.setCodigo(codigo);
		
		chamadoDao.persist(chamado);
		
		if(request.getIdsFuncionarios() != null) {
			for (Long idFuncionario : request.getIdsFuncionarios()) {
				Atendimento atendimento = new Atendimento();
				
				Pessoa funcionario = new Pessoa();
				funcionario.setId(idFuncionario);
				
				atendimento.setPessoa(funcionario);
				atendimento.setChamado(chamado);
				
				atendimentoDao.persist(atendimento);
			}
		}
		
		HistoricoChamado historicoChamado = new HistoricoChamado();
		OperacaoChamado operacaoChamado = new OperacaoChamado();
		operacaoChamado.setId(1L);
		
		historicoChamado.setHoraRealizacao(cal);
		historicoChamado.setChamado(chamado);
		historicoChamado.setOperacao(operacaoChamado);
		historicoChamado.setPessoa(pessoaLogada);
		
		historicoChamadoDao.persist(historicoChamado);
		
	}

	public void validar(CadastrarChamado request) throws NegocioException {
		if (request.getIdTipoServico() == null || request.getIdTipoServico() == 0) {
			throw new NegocioException(ID_TIPO_SERVICO_VAZIO);
		}
		
		if (StringUtils.isEmpty(request.getDescricao())) {
			throw new NegocioException(DESCRICAO_VAZIO);
		}
		if(request.getDescricao().length() > 100) {
			throw new NegocioException(DESCRICAO_TAMANHO_EXCEDIDO);
		}
		if(request.getMateriais().length() > 200) {
			throw new NegocioException(MATERIAIS_TAMANHO_EXCEDIDO);
		}
		if(request.getObservacoes().length() > 200) {
			throw new NegocioException(OBSERVACOES_TAMANHO_EXCEDIDO);
		}	
		if (request.getIdSetor() == null || request.getIdSetor() == 0) {
			throw new NegocioException(ID_SETOR_VAZIO);
		}
		if (request.getIdSolicitante() == null || request.getIdSolicitante() == 0) {
			throw new NegocioException(ID_SOLICITANTE_VAZIO);
		}
	
	}

}
