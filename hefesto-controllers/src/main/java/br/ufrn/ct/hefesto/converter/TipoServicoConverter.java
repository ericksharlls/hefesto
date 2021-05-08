package br.ufrn.ct.hefesto.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class TipoServicoConverter implements Converter {
	
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return null;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			String retorno = "";
			return retorno;
		} else {
			return null;
		}
	}
}
