package br.ufrn.ct.hefesto.persistence.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;
	
	@Column(name = "login_usuario")
	private String login;
	
	@Column(name = "senha_usuario")
	private String senha;
	
	@Column(name = "ativo_usuario")
	private Boolean isAtivo;
	
	@Column(name = "tentativas_login_usuario")
	private Integer tentativasLogin;
	
	@JoinColumn(name = "id_pessoa")
	@ManyToOne(fetch = FetchType.LAZY)
	private Pessoa pessoa;
	
	@ManyToMany(cascade = {
		    CascadeType.PERSIST, CascadeType.MERGE
	})
	@JoinTable(name = "permissao_usuario",
		    joinColumns = @JoinColumn(name = "id_usuario"),
		    inverseJoinColumns = @JoinColumn(name = "id_papel")
	)
	private Set<Papel> papeis = new HashSet<Papel>();
	
	public Usuario() {
		
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        return id != null && id.equals(((Usuario) o).getId());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
    
    public void addPapel(Papel papel) {
    	getPapeis().add(papel);
    	papel.getUsuarios().add(this);
    }
 
    public void removePapel(Papel papel) {
        getPapeis().remove(papel);
        papel.getUsuarios().remove(this);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public Integer getTentativasLogin() {
		return tentativasLogin;
	}

	public void setTentativasLogin(Integer tentativasLogin) {
		this.tentativasLogin = tentativasLogin;
	}

	public Set<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(Set<Papel> papeis) {
		this.papeis = papeis;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
