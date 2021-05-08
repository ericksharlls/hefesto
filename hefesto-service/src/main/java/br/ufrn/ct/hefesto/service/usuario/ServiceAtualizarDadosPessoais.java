package br.ufrn.ct.hefesto.service.usuario;

import java.text.ParseException;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.ct.hefesto.model.request.usuario.AtualizarDadosPessoais;
import br.ufrn.ct.hefesto.persistence.dao.PessoaDao;
import br.ufrn.ct.hefesto.persistence.dao.UsuarioDao;
import br.ufrn.ct.hefesto.persistence.entity.Pessoa;
import dev.modulo.service.exception.NegocioException;
import dev.modulo.service.interfaces.IServiceVoid;
import dev.modulo.utils.EmailUtils;
import dev.modulo.utils.FormatadorMascaras;

@Service("AtualizarDadosPessoais")
public class ServiceAtualizarDadosPessoais implements IServiceVoid<AtualizarDadosPessoais> {

	@Autowired
	PessoaDao pessoaDao;
	
	@Autowired
	UsuarioDao usuarioDao;
	
	private static String NOME_PESSOA_VAZIO = "07.1";
	private static String EMAIL_PESSOA_VAZIO = "07.2";
	private static String TELEFONE_PESSOA_VAZIO = "07.3";
	private static String DOCUMENTO_PESSOA_VAZIO = "07.4";
	private static String NASCIMENTO_PESSOA_VAZIO = "07.5";
	private static String MATRICULA_VAZIO = "07.6";
	private static String DOCUMENTO_PESSOA_INVALIDO = "07.7";
	private static String EMAIL_PESSOA_INVALIDO = "07.8";
	private static String OPRECAO_INVALIDA = "07.9";
	

	
	@Transactional
	public void executar(AtualizarDadosPessoais request) throws NegocioException {
		
		Pessoa pessoa = new Pessoa();
		
		pessoa = pessoaDao.getById(request.getIdPessoaEmEdicao());
		
		pessoa.setId(request.getIdPessoaEmEdicao());
		pessoa.setNome(request.getNome());
		pessoa.setDataNascimento(request.getDataNascimento());
		pessoa.setMatricula(request.getMatricula());
		pessoa.setEmail(request.getEmail());
		try {
			pessoa.setTelefone(request.getTelefone().substring(1, 3) + FormatadorMascaras.removerMascaraTelefone(request.getTelefone()).substring(5));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		pessoa.setNumeroDocumento(request.getNumeroDocumento());

		pessoaDao.update(pessoa);

	}

	public void validar(AtualizarDadosPessoais request) throws NegocioException {
		
		
		if (StringUtils.isEmpty(request.getNome())) {
			throw new NegocioException(NOME_PESSOA_VAZIO);
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
		if (StringUtils.isEmpty(request.getTelefone())) {
			throw new NegocioException(TELEFONE_PESSOA_VAZIO);
		}
		if (StringUtils.isEmpty(request.getNumeroDocumento())) {
			throw new NegocioException(DOCUMENTO_PESSOA_VAZIO);
		}
		if (request.getNumeroDocumento().length() > 11) {
			throw new NegocioException(DOCUMENTO_PESSOA_INVALIDO);
		}
		
		Calendar calOld = Calendar.getInstance(); 
		calOld.add(Calendar.YEAR, -15);
		
		if(request.getDataNascimento() == null || calOld.before(request.getDataNascimento())) {
			throw new NegocioException(NASCIMENTO_PESSOA_VAZIO);
		}
		
		if (StringUtils.isEmpty(request.getNome())) {
			throw new NegocioException(MATRICULA_VAZIO);
		}
		
		
		
		
		if(!request.getIdPessoaEmEdicao().equals( request.getIdPessoaLogada())) {
			throw new NegocioException(OPRECAO_INVALIDA);
		}
		
	}
	
}
