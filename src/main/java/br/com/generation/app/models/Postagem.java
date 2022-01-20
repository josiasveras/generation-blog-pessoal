package br.com.generation.app.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name = "tb_postagens")
@EntityScan(basePackages = {"br.com.generation.app.model"})
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
	
	@Deprecated
	public Postagem() {
		super();
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
}
