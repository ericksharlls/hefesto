package br.ufrn.ct.hefesto.web;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class ViewScope implements Scope {

	public Object get(String name, ObjectFactory<?> objectFactory) {
		if (FacesContext.getCurrentInstance().getViewRoot() != null) {
			Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().
					getViewMap(); // Map do JSF	cujo os valores s�o referentes ao escopo de view
			if (viewMap.containsKey(name)) {
				return viewMap.get(name);
			} else {
				Object object = objectFactory.getObject();
				// da pr�xima vez que for requisitado, dentro do escopo de view,
				// ele n�o precisar� ser constru�do.
				viewMap.put(name, object);
				return object;
			}
		} else {
			return null;
		}
	}


	public Object remove(String name) {
		if (FacesContext.getCurrentInstance().getViewRoot() != null) {
			// simplesmente, remove o bean Spring do ViewMap do JSF
			return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
		} else {
			return null;
		}
	}

	public void registerDestructionCallback(String name, Runnable callback) {
		// TODO Auto-generated method stub

	}


	public Object resolveContextualObject(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getConversationId() {
		// TODO Auto-generated method stub
		return null;
	}

}
