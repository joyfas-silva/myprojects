package br.com.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.biblioteca.model.Pessoa;
import br.com.biblioteca.repository.PessoaRepository;

@Service
public class PessoaService implements IPessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public Pessoa save(Pessoa pessoa) {

		return pessoaRepository.save(pessoa);
	}
	
	@Override
	public Optional<Pessoa> findById(Long id) {
		
		return pessoaRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {

		pessoaRepository.deleteById(id);
	}

	@Override
	public List<Pessoa> findAll() {

		return pessoaRepository.findAll();
	}
	
	@Override
	public Iterable<Pessoa> listGerentes() {
		Pessoa pessoa = new Pessoa();
		pessoa.setFuncionario(Boolean.FALSE);
		
		return findAllOrderByNome(pessoa);
	}
	
	@Override
	public Iterable<Pessoa> listFuncs() {
		Pessoa pessoa = new Pessoa();
		pessoa.setFuncionario(Boolean.TRUE);
		
		return findAllOrderByNome(pessoa);
	}
	
	private Iterable<Pessoa> findAllOrderByNome(Pessoa pessoa) {
		
		Example<Pessoa> example = Example.of(pessoa);
		Sort sort = Sort.by(Sort.Direction.ASC, "nome");
		return pessoaRepository.findAll(example, sort);
	}
	
}
