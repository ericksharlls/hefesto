package br.ufrn.ct.hefesto.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufrn.ct.hefesto.model.SetorModel;
import br.ufrn.ct.hefesto.model.request.unidade.ObterSetor;
import dev.modulo.service.context.ContextService;
import dev.modulo.service.exception.NegocioException;

@Component
@Scope("request")
public class setorConverter implements Converter {

	@Autowired
	private ContextService service;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.equals("null") &&  value.trim().length() > 0) {
			ObterSetor obterSetor = new ObterSetor();
			SetorModel setor = new SetorModel();
			
			try {
				obterSetor.setId(Long.parseLong(value));
				setor = (SetorModel) service.obter(obterSetor);
				return setor;
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
			String retorno = String.valueOf(((SetorModel) value).getId());
			return retorno;
		} else {
			return null;
		}
	}

}
