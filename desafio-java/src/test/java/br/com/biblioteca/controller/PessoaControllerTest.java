package br.com.biblioteca.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.biblioteca.dto.PessoaDTO;
import br.com.biblioteca.model.Pessoa;
import br.com.biblioteca.service.PessoaService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PessoaControllerTest {
	
	
	@Mock
	private PessoaService pessoaService;
	
	@InjectMocks
	private PessoaController controller;
	
	private ResponseEntity<?> response;
	
	private Iterable<Pessoa> itarable;
	
	@Test
	public void listAll() throws Exception {
		when(pessoaService.findAll()).thenReturn(new ArrayList<Pessoa>());
		response = controller.listAll();
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}
	
	@Test
	public void listarFuncs() throws Exception {
		
		when(pessoaService.listFuncs()).thenReturn(itarable);
		response = controller.listarFuncs();
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}
	
	@Test
	public void save() throws Exception {
		PessoaDTO dto = new PessoaDTO();
		when(pessoaService.save(Pessoa.toEntity(dto))).thenReturn(new Pessoa());
		response = controller.save(dto);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}
	
	@Test
	public void delete() throws Exception {
		Long id = 1l;
		doNothing().when(pessoaService).deleteById(id);
		response = controller.delete(id);
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		
	}

}
