package br.ufrn.ct.hefesto.service.pessoa;

import java.text.ParseException;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.request.pessoa.EditarPessoa;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import br.ufrn.ct.hefesto.persistence.entity.Unidade;
import br.ufrn.ct.hefesto.persistence.entity.TipoPessoa;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;
import dev.modulo.utils.EmailUtils;
import dev.modulo.utils.FormatadorMascaras;
import dev.modulo.utils.ManipuladorStrings;

@Service("EditarPessoa")
public class ServiceEditarPessoa implements IServiceVoid<EditarPessoa>{

	@Autowired
	PessoaDao pessoaDao;
	
	private static String NOME_PESSOA_VAZIO = "04.1";
	private static String EMAIL_PESSOA_VAZIO = "04.2";
	private static String TELEFONE_PESSOA_VAZIO = "04.3";
	private static String DOCUMENTO_PESSOA_VAZIO = "04.4";
	private static String NASCIMENTO_PESSOA_VAZIO = "04.5";
	private static String ID_PESSOA_VAZIO = "04.6";
	private static String SETOR_LOCALIZACAO_PESSOA_VAZIO = "04.7";
	private static String DOCUMENTO_PESSOA_INVALIDO = "04.8";
	private static String EMAIL_PESSOA_INVALIDO = "04.9";
	private static String SETOR_LOTACAO_PESSOA_VAZIO = "04.12";
	private static String NOME_TAMANHO_EXCEDIDO = "04.13";
	private static String EMAIL_TAMANHO_EXCEDIDO = "04.14";
	private static String DOCUMENTO_TAMANHO_EXCEDIDO = "04.15";
	private static String MATRICULA_TAMANHO_EXCEDIDO = "04.16";
	
	@Transactional
	public void executar(EditarPessoa request) throws NegocioException {
		Pessoa pessoa = new Pessoa();
		
		Unidade setorLoc = new Unidade();
		setorLoc.setId(request.getIdSetorLocalizacao());
		pessoa.setUnidadeLocalizacao(setorLoc);
		
		Unidade setorLot = new Unidade();
		setorLot.setId(request.getIdSetorLotacao());
		pessoa.setUnidadeLotacao(setorLot);
		
		TipoPessoa tipoPessoa = new TipoPessoa();
		tipoPessoa.setId(request.getIdTipoPessoa());
		pessoa.setTipoPessoa(tipoPessoa);
		
		pessoa.setId(request.getId());
		pessoa.setNome(request.getNome());
		pessoa.setEmail(request.getEmail());
		pessoa.setNumeroDocumento(request.getNumeroDocumento());
		pessoa.setMatricula(request.getMatricula());
		try {
			pessoa.setTelefone(request.getTelefone().substring(1, 3) + FormatadorMascaras.removerMascaraTelefone(request.getTelefone()).substring(5));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		pessoa.setDataNascimento(request.getDataNascimento());
		
		pessoaDao.update(pessoa);
	}

	public void validar(EditarPessoa request) throws NegocioException {
		if (request.getId() == null || request.getId() == 0) {
			throw new NegocioException(ID_PESSOA_VAZIO);
		}
		if (StringUtils.isEmpty(request.getNome())) {
			throw new NegocioException(NOME_PESSOA_VAZIO);
		}
		
		if(request.getNome().length() > 80) {
			throw new NegocioException(NOME_TAMANHO_EXCEDIDO);
		}
		
		if (StringUtils.isEmpty(request.getEmail())) {
			throw new NegocioException(EMAIL_PESSOA_VAZIO);
		}
		try {
			if(!EmailUtils.validarEmail(request.getEmail())) {
				throw new NegocioException(EMAIL_PESSOA_INVALIDO);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(request.getEmail().length() > 40) {
			throw new NegocioException(EMAIL_TAMANHO_EXCEDIDO);
		}
		
		if (StringUtils.isEmpty(request.getNumeroDocumento())) {
			throw new NegocioException(DOCUMENTO_PESSOA_VAZIO);
		}
		
		if (!ManipuladorStrings.isOnlyNumbers(request.getNumeroDocumento())) {	
			throw new NegocioException(DOCUMENTO_PESSOA_INVALIDO);
		}
		
		if(request.getNumeroDocumento().length() > 11) {
			throw new NegocioException(DOCUMENTO_TAMANHO_EXCEDIDO);
		}
		
		if (StringUtils.isEmpty(request.getTelefone())) {
			throw new NegocioException(TELEFONE_PESSOA_VAZIO);
		}
		
		if(request.getMatricula().length() > 13) {
			throw new NegocioException(MATRICULA_TAMANHO_EXCEDIDO);
		}
		
		Calendar calOld = Calendar.getInstance(); 
		calOld.add(Calendar.YEAR, -15);
		
		if(request.getDataNascimento() == null || calOld.before(request.getDataNascimento())) {
			throw new NegocioException(NASCIMENTO_PESSOA_VAZIO);
		}
		
		if (request.getIdSetorLocalizacao() == null || request.getIdSetorLocalizacao() == 0)
			throw new NegocioException(SETOR_LOCALIZACAO_PESSOA_VAZIO);
		if (request.getIdSetorLotacao() == null || request.getIdSetorLotacao() == 0)
			throw new NegocioException(SETOR_LOTACAO_PESSOA_VAZIO);
	
		
		
	}

}
