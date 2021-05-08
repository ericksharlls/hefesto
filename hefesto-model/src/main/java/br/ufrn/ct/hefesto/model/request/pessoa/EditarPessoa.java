package br.ufrn.ct.hefesto.model.request.pessoa;

import java.util.Date;

import dev.modulo.abstractmodel.RequestModel;

public class EditarPessoa extends RequestModel{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String numeroDocumento;
	private String matricula;
	private String telefone;
	private Long idSetorLocalizacao;
	private Long idSetorLotacao;
	private Date dataNascimento;
	private Long idTipoPessoa;
	
	public EditarPessoa() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdTipoPessoa() {
		return idTipoPessoa;
	}

	public void setIdTipoPessoa(Long idTipoPessoa) {
		this.idTipoPessoa = idTipoPessoa;
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
