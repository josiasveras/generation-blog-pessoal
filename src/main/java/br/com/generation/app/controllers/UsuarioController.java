package br.com.generation.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.generation.app.models.UserLogin;
import br.com.generation.app.models.Usuario;
import br.com.generation.app.repositories.UsuarioRepository;
import br.com.generation.app.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("/logar")
	public ResponseEntity<UserLogin>  Autentication(@RequestBody Optional<UserLogin> user) {
		
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario>  Post(@RequestBody Usuario usuario) {
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.CadastrarUsuario(usuario));
	
	}
	
	@GetMapping("/buscar/tudo")
	public ResponseEntity<List<Usuario>>findAllUsuario(){
		return ResponseEntity.ok(usuarioRepository.findAll());
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<Usuario> findByIdUsuario(@PathVariable (value="id") Long id){
		return usuarioRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/buscar/login/{usuario}")
	public ResponseEntity<Optional<Usuario>> findByUsuario(@PathVariable (value="usuario") String usuario){
		return ResponseEntity.ok(usuarioRepository.findByUsuario(usuario));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> put (@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
	}
	
	@DeleteMapping("/apagar/{id}")
	public void delete(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
	}
}
