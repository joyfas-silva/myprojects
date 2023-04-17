package br.com.biblioteca.service;

import java.util.List;

import br.com.biblioteca.exeption.MyProjectsException;
import br.com.biblioteca.model.Projeto;

public interface IProjetoService {

	Projeto save(Projeto projeto);
	
	Projeto findById(Long id) throws MyProjectsException;

	List<Projeto> findAll();

	void deleteById(Long id) throws MyProjectsException;
}
