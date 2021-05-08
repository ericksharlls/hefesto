package br.ufrn.ct.hefesto.service.pessoa;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.PessoaModel;
import br.ufrn.ct.hefesto.model.request.pessoa.ConsultarPessoa;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceConsultarPaginado;
import dev.modulo.utils.FormatadorMascaras;

@Service("ConsultarPessoa")
public class ServiceConsultarPessoa implements IServiceConsultarPaginado<PessoaModel, ConsultarPessoa> {

	@Autowired
	PessoaDao pessoaDao;
	
	@Transactional(readOnly= true)
	public List<PessoaModel> consultar(ConsultarPessoa request) throws NegocioException {
		List<PessoaModel> retorno = new ArrayList<PessoaModel>(0);

		for (Pessoa pessoa : this.pessoaDao.findByAllParametros(request.getNome(), request.getNumeroDocumento(), request.getMatricula(), request.getTipoPessoa(), request.getIdSetorLocalizacao(), request.getIdSetorLotacao(), request.getStartPosition(), request.getMaxResult())) {
			PessoaModel model = new PessoaModel();
			model.setId(pessoa.getId());
			model.setNome(pessoa.getNome());
			model.setEmail(pessoa.getEmail());
			model.setNumeroDocumento(pessoa.getNumeroDocumento());
			model.setMatricula(pessoa.getMatricula());
			try {
				model.setTelefone(StringUtils.isNullOrEmpty(pessoa.getTelefone()) ? " - " : FormatadorMascaras.aplicarMascaraTelefoneComDDD(pessoa.getTelefone()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			model.setDataNascimento(pessoa.getDataNascimento());
			model.setIdTipoPessoa(pessoa.getTipoPessoa().getId());
			model.setTipoPessoa(pessoa.getTipoPessoa().getNome());
			if(pessoa.getUnidadeLocalizacao() != null) {
				model.setIdSetorLocalizacao(pessoa.getUnidadeLocalizacao().getId());
				model.setSetorLocalizacao(pessoa.getUnidadeLocalizacao().getNome());
			}
			model.setIdSetorLotacao(pessoa.getUnidadeLotacao().getId());
			model.setSetorLotacao(pessoa.getUnidadeLotacao().getNome());
			
			retorno.add(model);
		}
		return retorno;
	}
	
	@Transactional(readOnly= true)
	public Integer contar(ConsultarPessoa request) throws NegocioException {
		return pessoaDao.countByAllParametros(request.getNome(), request.getNumeroDocumento(), request.getMatricula(), request.getTipoPessoa(), request.getIdSetorLocalizacao(), request.getIdSetorLotacao());
	}

	public void validar(ConsultarPessoa arg0) throws NegocioException {
		// TODO Auto-generated method stub
	}

}
