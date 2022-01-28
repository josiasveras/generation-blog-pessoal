package br.com.generation.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.generation.app.models.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long>{

	public List<Tema> findAllByDescricaoTemaContainingIgnoreCase(String descricaoTema);
}
