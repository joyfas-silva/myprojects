package br.com.biblioteca.service;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.biblioteca.enums.StatusEnum;
import br.com.biblioteca.exeption.MyProjectsException;
import br.com.biblioteca.model.Projeto;
import br.com.biblioteca.repository.ProjetoRepository;

@RunWith(SpringRunner.class)
public class ProjetoServiceTest {

	@InjectMocks
	private ProjetoService projetoService;

	@Mock
	private ProjetoRepository projetoRepository;

	private Long id = 1l;

	@Test
	public void findById() {

		when(projetoRepository.findById(id)).thenReturn(Optional.empty());
		MyProjectsException thrown = assertThrows(MyProjectsException.class, () -> {
			projetoService.findById(id);
		});

		assertEquals("Projeto não encontrado!", thrown.getMessage());
	}

	@Test
	public void findById2() {

		when(projetoRepository.findById(id)).thenReturn(Optional.of(new Projeto()));
		
		try {
			projetoService.findById(id);
		} catch (Exception e) {
			fail("Should not have thrown any exception");
		}
	}
	
	@Test
	public void deleteById() {

		when(projetoRepository.findById(id)).thenReturn(Optional.empty());
		MyProjectsException thrown = assertThrows(MyProjectsException.class, () -> {
			projetoService.deleteById(id);
		});

		assertEquals("Projeto não encontrado!", thrown.getMessage());
	}
	
	@Test
	public void deleteById2() {

		when(projetoRepository.findById(id)).thenReturn(Optional.of(new Projeto()));
		
		try {
			projetoService.deleteById(id);
		} catch (Exception e) {
			fail("Should not have thrown any exception");
		}
	}
	
	@Test
	public void deleteById3() {
		
		Projeto projeto = new Projeto();
		projeto.setStatus(StatusEnum.INICIADO);
		
		when(projetoRepository.findById(id)).thenReturn(Optional.of(projeto));
		
		MyProjectsException thrown = assertThrows(MyProjectsException.class, () -> {
			projetoService.deleteById(id);
		});

		assertEquals("Projeto não pode ser excluído!", thrown.getMessage());
		
		projeto.setStatus(StatusEnum.EM_ANDAMENTO);
		
		thrown = assertThrows(MyProjectsException.class, () -> {
			projetoService.deleteById(id);
		});
		
		assertEquals("Projeto não pode ser excluído!", thrown.getMessage());
		
		projeto.setStatus(StatusEnum.ENCERRADO);
		thrown = assertThrows(MyProjectsException.class, () -> {
			projetoService.deleteById(id);
		});
		
		assertEquals("Projeto não pode ser excluído!", thrown.getMessage());
	}
}
