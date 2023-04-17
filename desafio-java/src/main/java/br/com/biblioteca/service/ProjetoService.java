package br.com.biblioteca.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.biblioteca.enums.StatusEnum;
import br.com.biblioteca.exeption.MyProjectsException;
import br.com.biblioteca.model.Projeto;
import br.com.biblioteca.repository.ProjetoRepository;

@Service
public class ProjetoService implements IProjetoService {

	@Autowired
	private ProjetoRepository projetoRepository;

	@Override
	public Projeto save(Projeto projeto) {
		
		if(projeto.getStatus() == null)
			projeto.setStatus(StatusEnum.PLANEJADO);
		
		return projetoRepository.save(projeto);
	}

	@Override
	public Optional<Projeto> findById(Long id) throws MyProjectsException {
		Optional<Projeto> oProjeto = projetoRepository.findById(id);
				
		if(!oProjeto.isPresent())
			throw new MyProjectsException("Projeto não encontrado!");
		
		return oProjeto;
	}
	
	@Override
	public List<Projeto> findAll() {
		Sort sort = Sort.by("dataInicio","nome");
		return projetoRepository.findAll(sort);
	}

	@Override
	public void deleteById(Long id) throws MyProjectsException {
		
		Optional<Projeto> oProjeto = findById(id);
		List<StatusEnum> enums = Arrays.asList(StatusEnum.INICIADO, StatusEnum.EM_ANDAMENTO, StatusEnum.ENCERRADO);
		
		if(enums.contains(oProjeto.get().getStatus()))
			throw new MyProjectsException("Projeto não pode ser excluído!");
			
		projetoRepository.deleteById(id);
	}
}
