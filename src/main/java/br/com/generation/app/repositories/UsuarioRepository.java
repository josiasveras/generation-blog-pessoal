package br.com.generation.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.generation.app.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByUsuario(String login);
	
	public List <Usuario> findAllByNomeContainingIgnoreCase(String nome);
}
