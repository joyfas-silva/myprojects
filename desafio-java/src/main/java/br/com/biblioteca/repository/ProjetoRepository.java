package br.com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}
