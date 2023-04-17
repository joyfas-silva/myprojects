package br.com.biblioteca.service;

import java.util.List;
import java.util.Optional;

import br.com.biblioteca.model.Pessoa;

public interface IPessoaService {

	Pessoa save(Pessoa pessoa);
	
	Optional<Pessoa> findById(Long id);

	List<Pessoa> findAll();

	void deleteById(Long id);

	Iterable<Pessoa> listGerentes();

	Iterable<Pessoa> listFuncs();

}
