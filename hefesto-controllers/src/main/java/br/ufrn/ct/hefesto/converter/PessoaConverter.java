package br.ufrn.ct.hefesto.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufrn.ct.hefesto.model.PessoaModel;
import br.ufrn.ct.hefesto.model.request.obterpessoa.ObterPessoa;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Component
@Scope("request")
public class PessoaConverter implements Converter {

	@Autowired
	private ContextService service;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.equals("null") &&  value.trim().length() > 0) {
			ObterPessoa obterPessoa = new ObterPessoa();
			PessoaModel pessoa = new PessoaModel();
			
			try {
				obterPessoa.setId(Long.parseLong(value));
				pessoa = (PessoaModel) service.obter(obterPessoa);
				return pessoa;
			} catch (NegocioException e) {
				e.printStackTrace();
				throw new ConverterException(new FacesMessage("Conversion Error", "Not a valid theme."));
			}

		} else {
			return null;
		}
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			String retorno = String.valueOf(((PessoaModel) value).getId());
			return retorno;
		} else {
			return null;
		}
	}

}
