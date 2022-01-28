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

import br.com.generation.app.models.Tema;
import br.com.generation.app.repositories.TemaRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*")
public class TemaController {

	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping("/buscar/tudo")
	public ResponseEntity<List<Tema>> buscarTodosTemas(){
		List<Tema> list = temaRepository.findAll();
		
		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}
	
	@GetMapping("/buscar/{idTema}")
	public ResponseEntity<Tema> buscarTemaPorId(@PathVariable(value = "idTema") Long idTema) {
		return temaRepository.findById(idTema)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
				});
	}
	
	@GetMapping("/buscar/descricao/{descricaoTema}")
	public ResponseEntity<List<Tema>> buscarTemaPorDescricao(@PathVariable(value = "descricaoTema") String descricaoTema) {
		
		List<Tema> list = temaRepository.findAllByDescricaoTemaContainingIgnoreCase(descricaoTema);		
		
		if (list.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(list);
		}
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Tema> salvarTema(@RequestBody Tema tema) {
		return ResponseEntity.status(201).body(temaRepository.save(tema));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Tema> atualizarTema(@RequestBody Tema tema) {
		return temaRepository.findById(tema.getIdTema())
				.map(resp -> ResponseEntity.status(200).body(temaRepository.save(tema)))
				.orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não encontrado");
				});
	}
	
	@DeleteMapping("/deletar/{idTema}")
	public ResponseEntity<Tema> deletarTema(@PathVariable(value = "idTema") Long idTema) {
		Optional<Tema> optional = temaRepository.findById(idTema);
		
		if (optional.isPresent()) {
			temaRepository.deleteById(idTema);
			return ResponseEntity.status(200).build();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não foi encontrado");
		}
	}
	
}
