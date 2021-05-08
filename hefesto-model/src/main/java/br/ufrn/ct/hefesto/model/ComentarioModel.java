 package br.ufrn.ct.hefesto.model;

import dev.modulo.abstractmodel.AbstractModel;

public class ComentarioModel extends AbstractModel{
	private static final long serialVersionUID = 1L;
	
	private Long idComentario;
	private String textoComentario;
	private Long idHistorico;
	
	public ComentarioModel() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Long idComentario) {
		this.idComentario = idComentario;
	}

	public String getTextoComentario() {
		return textoComentario;
	}

	public void setTextoComentario(String textoComentario) {
		this.textoComentario = textoComentario;
	}

	public Long getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
