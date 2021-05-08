package br.ufrn.ct.hefesto.model.request.pessoa;

import dev.modulo.abstractmodel.RequestModel;

public class ListarPorParametrosPessoa extends RequestModel{

	private static final long serialVersionUID = 1L;
	private String nome;
	private String numeroDocumento;
	private String matricula;
	private Long tipoPessoa;
	private Long idSetorLocalizacao;
	private Long idSetorLotacao;
	
	public ListarPorParametrosPessoa() {
		// TODO Auto-generated constructor stub
	}

	public Long getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(Long tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Long getIdSetorLocalizacao() {
		return idSetorLocalizacao;
	}

	public void setIdSetorLocalizacao(Long idSetorLocalizacao) {
		this.idSetorLocalizacao = idSetorLocalizacao;
	}

	public Long getIdSetorLotacao() {
		return idSetorLotacao;
	}

	public void setIdSetorLotacao(Long idSetorLotacao) {
		this.idSetorLotacao = idSetorLotacao;
	}

	
}
