package br.com.generation.app.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_postagens")
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPostagem;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String tituloPostagem;
	
	@NotBlank
	@Size(min = 10, max = 1000)
	private String textoPostagem;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPostagem = new java.sql.Date(System.currentTimeMillis());
	
	@ManyToOne
	@JoinColumn(name="fk_tema")
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne
	@JoinColumn(name="fk_usuario")
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
	public Postagem(long idPostagem, String tituloPostagem, String textoPostagem, Date dataPostagem, Tema tema, Usuario usuario) {
		this.idPostagem = idPostagem;
		this.tituloPostagem = tituloPostagem;
		this.textoPostagem = textoPostagem;
		this.dataPostagem = dataPostagem;
		this.tema = tema;
		this.usuario = usuario;
	}

	public Postagem() {

	}

	public long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public String getTituloPostagem() {
		return tituloPostagem;
	}

	public void setTituloPostagem(String tituloPostagem) {
		this.tituloPostagem = tituloPostagem;
	}

	public String getTextoPostagem() {
		return textoPostagem;
	}

	public void setTextoPostagem(String textoPostagem) {
		this.textoPostagem = textoPostagem;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
