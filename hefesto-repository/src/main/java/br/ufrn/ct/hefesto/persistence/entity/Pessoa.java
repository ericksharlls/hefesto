package br.ufrn.ct.hefesto.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "numero_documento")
	private String numeroDocumento;
	
	@Column(name = "matricula")
	private String matricula;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_pessoa")
	private TipoPessoa tipoPessoa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidade_localizacao")
	private Unidade unidadeLocalizacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidade_lotacao")
	private Unidade unidadeLotacao;
	
	@Column(name = "id_pessoa_ufrn")
	private Long idPessoaUfrn;
	
	public Pessoa() {
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Unidade getUnidadeLocalizacao() {
		return unidadeLocalizacao;
	}

	public void setUnidadeLocalizacao(Unidade unidadeLocalizacao) {
		this.unidadeLocalizacao = unidadeLocalizacao;
	}

	public Unidade getUnidadeLotacao() {
		return unidadeLotacao;
	}

	public void setUnidadeLotacao(Unidade unidadeLotacao) {
		this.unidadeLotacao = unidadeLotacao;
	}

	public Long getIdPessoaUfrn() {
		return idPessoaUfrn;
	}

	public void setIdPessoaUfrn(Long idPessoaUfrn) {
		this.idPessoaUfrn = idPessoaUfrn;
	}

}
