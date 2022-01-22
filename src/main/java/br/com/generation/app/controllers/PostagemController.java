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

import br.com.generation.app.models.Postagem;
import br.com.generation.app.repositories.PostagemRepository;

@RestController
@RequestMapping("/postagem")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;
	
	@GetMapping("/buscar/todas")
	public ResponseEntity<List<Postagem>> buscarTodasPostagens(){
		List<Postagem> list = postagemRepository.findAll();
		
		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Postagem> buscarPostagemPorId(@PathVariable(value = "id") Long idPostagem) {
		return postagemRepository.findById(idPostagem)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
				});
	}
	
	@GetMapping("/buscar/titulo/{tituloPostagem}")
	public ResponseEntity<List<Postagem>> buscarPostagemPorTitulo(@PathVariable String tituloPostagem) {
		//return ResponseEntity.ok(postagemRepository.findAllByTitleContainingIgnoreCase(tituloPostagem));
		
		List<Postagem> list = postagemRepository.findAllBytituloPostagemContainingIgnoreCase(tituloPostagem);		
		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Postagem> salvarPostagem(@RequestBody Postagem postagem) {
		return ResponseEntity.status(201).body(postagemRepository.save(postagem));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Postagem> atualizarPostagem(@RequestBody Postagem postagem) {
		return postagemRepository.findById(postagem.getIdPostagem())
				.map(resp -> ResponseEntity.status(200).body(postagemRepository.save(postagem)))
				.orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
				});
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Postagem> deletarPostagem(@PathVariable(value = "id") Long idPostagem) {
		Optional<Postagem> optional = postagemRepository.findById(idPostagem);
		
		if (optional.isPresent()) {
			postagemRepository.deleteById(idPostagem);
			return ResponseEntity.status(200).build();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não foi encontrado");
		}
	}
}
