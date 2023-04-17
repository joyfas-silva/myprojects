package br.com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
