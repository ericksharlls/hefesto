package br.ufrn.ct.hefesto.model.request.chamado;

import dev.modulo.abstractmodel.RequestModel;

public class AdicionarComentario extends RequestModel{
	private static final long serialVersionUID = 1L;

	private String textoComentario;
	private Long idChamado;
	private Long idPessoaLogada;
	
	public AdicionarComentario() {
		// TODO Auto-generated constructor stub
	}

	public String getTextoComentario() {
		return textoComentario;
	}

	public void setTextoComentario(String textoComentario) {
		this.textoComentario = textoComentario;
	}

	public Long getIdPessoaLogada() {
		return idPessoaLogada;
	}

	public void setIdPessoaLogada(Long idPessoaLogada) {
		this.idPessoaLogada = idPessoaLogada;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdChamado() {
		return idChamado;
	}

	public void setIdChamado(Long idChamado) {
		this.idChamado = idChamado;
	}
	
}
