package br.com.generation.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.generation.app.models.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start(){
		
		usuarioRepository.save(new Usuario(0L, "Frida Kahlo", "frida@email.com.br", "12345", "https://i.imgur.com/NtyGneo.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Fernanda Montenegro", "fernanda@email.com.br", "12345", "https://i.imgur.com/mB3VM2N.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Alfredo Volpi", "alfredo@email.com.br", "12345", "https://i.imgur.com/JR7kUFU.jpg"));

		usuarioRepository.save(new Usuario(0L, "Pablo Picasso", "pablo@email.com.br", "12345", "https://i.imgur.com/FETvs2O.jpg"));

	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario("frida@email.com.br");

		assertTrue(usuario.get().getUsuario().equals("frida@email.com.br"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("F");

		assertEquals(3, listaDeUsuarios.size());

		assertTrue(listaDeUsuarios.get(0).getNome().equals("Frida Kahlo"));

		assertTrue(listaDeUsuarios.get(1).getNome().equals("Fernanda Montenegro"));

		assertTrue(listaDeUsuarios.get(2).getNome().equals("Alfredo Volpi"));
		
	}
}
