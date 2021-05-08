package br.ufrn.ct.hefesto.controller.historicochamado;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.ufrn.ct.hefesto.AbstractFormBeanHefesto;
import br.ufrn.ct.hefesto.model.ChamadoModel;
import br.ufrn.ct.hefesto.model.ComentarioModel;
import br.ufrn.ct.hefesto.model.HistoricoChamadoModel;
import br.ufrn.ct.hefesto.model.HistoricoMudancaStatusChamadoModel;
import br.ufrn.ct.hefesto.model.PessoaModel;
import br.ufrn.ct.hefesto.model.StatusModel;
import br.ufrn.ct.hefesto.model.request.chamado.ObterChamado;
import br.ufrn.ct.hefesto.model.request.historicochamado.ConsultarHistoricoChamado;
import br.ufrn.ct.hefesto.model.request.historicochamado.ObterComentario;
import br.ufrn.ct.hefesto.model.request.historicochamado.ObterHistoricoMudancaStatusChamado;
import br.ufrn.ct.hefesto.model.request.obterpessoa.ObterPessoa;
import br.ufrn.ct.hefesto.model.request.status.ObterStatus;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Controller
@Scope("view")
public class ConsultarHistoricoChamadoFormBean extends AbstractFormBeanHefesto{
	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextService service;
	private ConsultarHistoricoChamado consultarHistoricoChamado = new ConsultarHistoricoChamado();
	private Long idChamadoSelecionado;
	private List<HistoricoChamadoModel> historicosEncontrados = new ArrayList<HistoricoChamadoModel>(0);
	private List<HistoricoChamadoModel> historicosExibidos = new ArrayList<HistoricoChamadoModel>(0);
	private ChamadoModel chamado = new ChamadoModel();
	
	public ConsultarHistoricoChamadoFormBean() {
		
	}
	
	@SuppressWarnings("unchecked")
	public String obterHistorico() {
		ConsultarHistoricoChamado consultarChamado = new ConsultarHistoricoChamado();
		consultarChamado.setIdChamado(this.getIdChamadoSelecionado());
		
		try {
			setHistoricosEncontrados((List<HistoricoChamadoModel>) this.service.consultar(consultarChamado));
			
			ObterChamado obterChamado = new ObterChamado();
			obterChamado.setId(idChamadoSelecionado);
			this.chamado = (ChamadoModel) (this.service.obter(obterChamado));
			
			for (HistoricoChamadoModel historico : getHistoricosEncontrados()) {
				
				HistoricoChamadoModel model = new HistoricoChamadoModel();
				model.setId(historico.getId());
				DateFormat dfdia = new SimpleDateFormat("dd/MM/yyyy");
				DateFormat dfhora = new SimpleDateFormat("HH:mm");
				model.setLabelHora(dfdia.format(historico.getHoraRealizacao().getTime()) + " às " + dfhora.format(historico.getHoraRealizacao().getTime()));
				
				if(historico.getIdOperacao() == 1)
					model.setLabelOperacao(getMessageByCodeMessage("1"));
				if(historico.getIdOperacao() == 2)
					model.setLabelOperacao(getMessageByCodeMessage("2"));
				if(historico.getIdOperacao() == 3)
					model.setLabelOperacao(getMessageByCodeMessage("3"));
				if(historico.getIdOperacao() == 4) {
					ObterHistoricoMudancaStatusChamado obterHistoricoStatus = new ObterHistoricoMudancaStatusChamado();
					obterHistoricoStatus.setIdHistoricoChamado(historico.getId());
					HistoricoMudancaStatusChamadoModel historicoStatusModel = new HistoricoMudancaStatusChamadoModel();	
					historicoStatusModel = (HistoricoMudancaStatusChamadoModel) this.service.obter(obterHistoricoStatus);
					
					ObterStatus obterStatus = new ObterStatus();
					obterStatus.setId(historicoStatusModel.getIdStatus());
					StatusModel statusModel = new StatusModel();
					statusModel = (StatusModel) this.service.obter(obterStatus);
					
					model.setLabelOperacao(getMessageByCodeMessage("4") + statusModel.getNome());
				}
				if(historico.getIdOperacao() == 5)
					model.setLabelOperacao(getMessageByCodeMessage("5"));
				
				ObterPessoa obterPessoa = new ObterPessoa();
				obterPessoa.setId(historico.getIdPessoa());
				PessoaModel pessoa = new PessoaModel();
				
				pessoa = (PessoaModel) this.service.obter(obterPessoa);
				
				model.setNomePessoa(pessoa.getNome());
				
				model.setTemComentario(false);
				ObterComentario obterComentario = new ObterComentario();
				obterComentario.setIdHistoricoChamado(historico.getId());
				ComentarioModel comentario = new ComentarioModel();
				
				comentario = (ComentarioModel) this.service.obter(obterComentario);
				
				if(comentario != null) {
					model.setComentario(comentario.getTextoComentario());
					model.setTemComentario(true);
				}
				historicosExibidos.add(model);
				
			
			}
		
		} catch (NegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public ContextService getService() {
		return service;
	}

	public void setService(ContextService service) {
		this.service = service;
	}

	public ConsultarHistoricoChamado getConsultarHistoricoChamado() {
		return consultarHistoricoChamado;
	}

	public void setConsultarHistoricoChamado(ConsultarHistoricoChamado consultarHistoricoChamado) {
		this.consultarHistoricoChamado = consultarHistoricoChamado;
	}

	public Long getIdChamadoSelecionado() {
		return idChamadoSelecionado;
	}

	public void setIdChamadoSelecionado(Long idChamadoSelecionado) {
		this.idChamadoSelecionado = idChamadoSelecionado;
	}

	public List<HistoricoChamadoModel> getHistoricosEncontrados() {
		return historicosEncontrados;
	}

	public void setHistoricosEncontrados(List<HistoricoChamadoModel> historicosEncontrados) {
		this.historicosEncontrados = historicosEncontrados;
	}

	public List<HistoricoChamadoModel> getHistoricosExibidos() {
		return historicosExibidos;
	}

	public void setHistoricosExibidos(List<HistoricoChamadoModel> historicosExibidos) {
		this.historicosExibidos = historicosExibidos;
	}

	@Override
	protected String retornaOperacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}

	public ChamadoModel getChamado() {
		return chamado;
	}

	public void setChamado(ChamadoModel chamado) {
		this.chamado = chamado;
	}
	
}
