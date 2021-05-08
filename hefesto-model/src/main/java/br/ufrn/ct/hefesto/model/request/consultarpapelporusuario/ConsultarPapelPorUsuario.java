package br.ufrn.ct.hefesto.model.request.consultarpapelporusuario;

import dev.modulo.abstractmodel.RequestModel;

public class ConsultarPapelPorUsuario extends RequestModel {

	private static final long serialVersionUID = 1L;
	
	private Long idUsuario;
	
	public ConsultarPapelPorUsuario() {
		
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

}
