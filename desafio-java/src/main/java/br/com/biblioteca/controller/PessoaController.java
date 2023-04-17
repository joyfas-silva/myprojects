package br.com.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.biblioteca.model.Pessoa;
import br.com.biblioteca.service.IPessoaService;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private IPessoaService pessoaService;

	@GetMapping("/find")
	public ResponseEntity<List<Pessoa>> listAll() {

		return new ResponseEntity<>(pessoaService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/listar-funcs")
	public ResponseEntity<Iterable<Pessoa>> listarFuncs() {
		
		return new ResponseEntity<>(pessoaService.listFuncs(), HttpStatus.OK);
	}

	@PostMapping("/salvar")
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa) {
		
		return new ResponseEntity<>(pessoaService.save(pessoa), HttpStatus.OK);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Long id) {
		try {
			pessoaService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
