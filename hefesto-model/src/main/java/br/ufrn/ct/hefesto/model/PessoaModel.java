package br.ufrn.ct.hefesto.model;

import java.util.Date;

import dev.modulo.abstractmodel.AbstractModel;

public class PessoaModel extends AbstractModel {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String numeroDocumento;
	private String matricula;
	private String telefone;
	private Date dataNascimento;
	private Long idTipoPessoa;
	private String tipoPessoa;
	private Long idPredio;
	private Long idSetorLocalizacao;
	private String setorLocalizacao;
	private Long idSetorLotacao;
	private String setorLotacao;
	private Long idPessoaUfrn;
	
	public PessoaModel() {
		
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

	public Long getIdTipoPessoa() {
		return idTipoPessoa;
	}

	public void setIdTipoPessoa(Long idTipoPessoa) {
		this.idTipoPessoa = idTipoPessoa;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getSetorLotacao() {
		return setorLotacao;
	}

	public void setSetorLotacao(String setorLotacao) {
		this.setorLotacao = setorLotacao;
	}

	public Long getIdSetorLocalizacao() {
		return idSetorLocalizacao;
	}

	public void setIdSetorLocalizacao(Long idSetorLocalizacao) {
		this.idSetorLocalizacao = idSetorLocalizacao;
	}

	public String getSetorLocalizacao() {
		return setorLocalizacao;
	}

	public void setSetorLocalizacao(String setorLocalizacao) {
		this.setorLocalizacao = setorLocalizacao;
	}

	public Long getIdSetorLotacao() {
		return idSetorLotacao;
	}

	public void setIdSetorLotacao(Long idSetorLotacao) {
		this.idSetorLotacao = idSetorLotacao;
	}

	@Override
	public boolean equals(Object obj){
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PessoaModel))
			return false;
		PessoaModel other = (PessoaModel) obj;
		if (id == null){
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getIdPredio() {
		return idPredio;
	}

	public void setIdPredio(Long idPredio) {
		this.idPredio = idPredio;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Long getIdPessoaUfrn() {
		return idPessoaUfrn;
	}

	public void setIdPessoaUfrn(Long idPessoaUfrn) {
		this.idPessoaUfrn = idPessoaUfrn;
	}

}
