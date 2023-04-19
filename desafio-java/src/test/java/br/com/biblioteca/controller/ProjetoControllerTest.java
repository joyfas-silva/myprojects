package br.com.biblioteca.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.biblioteca.dto.ProjetoDTO;
import br.com.biblioteca.exeption.MyProjectsException;
import br.com.biblioteca.model.Projeto;
import br.com.biblioteca.service.IPessoaService;
import br.com.biblioteca.service.IProjetoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProjetoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private ProjetoController controller;
	
	@Mock
	private IProjetoService projetoService;
	
	@Mock
	private IPessoaService pessoaService;
	
	@Mock
	Model model;
	
	@Mock
	RedirectAttributes redirectAttribute;
	
	private Long id = 1l;
	Projeto projeto = new Projeto();
	
	@Test
	public void listar() throws Exception {
		mockMvc.perform(get("/listar"))
				.andDo(print())
                .andExpect(status().isOk());
	}
	
	@Test
	public void cadastroMyProjectsEx() throws Exception {
		ProjetoDTO projetoDto = new ProjetoDTO();
		projetoDto.setId(id);
		
		when(projetoService.findById(id)).thenThrow(new MyProjectsException("Test"));
		controller.cadastro(projetoDto, model, redirectAttribute);
		
		mockMvc.perform(post("/cadastro"))
		.andDo(print())
		.andExpect(status().isFound());
	}
	
	@Test
	public void cadastroException() throws Exception{
		ProjetoDTO projetoDto = new ProjetoDTO();
		projetoDto.setId(id);
		when(projetoService.findById(id)).thenThrow(new NullPointerException("Test"));
		controller.cadastro(projetoDto, model, redirectAttribute);
		
		mockMvc.perform(post("/cadastro"))
		.andDo(print())
		.andExpect(status().isFound());
	}
	
	@Test
	public void cadastro() throws Exception{
		ProjetoDTO projetoDto = new ProjetoDTO();
		projetoDto.setId(id);
		when(projetoService.findById(id)).thenReturn(projeto);
		controller.cadastro(projetoDto, model, redirectAttribute);
		
		mockMvc.perform(post("/cadastro"))
		.andDo(print())
		.andExpect(status().isFound());
	}
	
	@Test
	public void salvar() throws Exception{
		
		when(projetoService.save(projeto)).thenReturn(projeto);
		controller.salvar(model, new ProjetoDTO(), redirectAttribute);
		
		mockMvc.perform(post("/salvar", projeto))
		.andDo(print())
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/listar"));
	}
	
	@Test
	public void consultarMyProjectsEx() throws Exception{
		when(projetoService.findById(id)).thenThrow(new MyProjectsException("Test"));
		controller.consultar(id, model, redirectAttribute);

		mockMvc.perform(post("/consultar"))
		.andDo(print())
		.andExpect(flash().attributeExists("msgs"))
		.andExpect(status().isFound());
	}
	
	@Test
	public void consultarException() throws Exception{
		
		when(projetoService.findById(id)).thenThrow(new NullPointerException("Test"));
		controller.consultar(id, model, redirectAttribute);
		
		mockMvc.perform(post("/consultar"))
		.andDo(print())
		.andExpect(flash().attributeExists("msgs"))
		.andExpect(status().isFound());
	}
	
	@Test
	public void consultar4() throws Exception{
		
		when(projetoService.findById(id)).thenReturn(projeto);
		controller.consultar(id, model, redirectAttribute);
		
		mockMvc.perform(post("/consultar"))
		.andDo(print())
		.andExpect(status().isFound());
	}
	
	@Test
	public void deletar2() throws Exception{
		
		doThrow(new MyProjectsException("Test")).when(projetoService).deleteById(id);
		controller.deletar(id, model, redirectAttribute);
		
		mockMvc.perform(post("/deletar"))
		.andDo(print())
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/listar"));
	}
	
	@Test
	public void deletar3() throws Exception{
		
		doNothing().when(projetoService).deleteById(id);
		controller.deletar(id, model, redirectAttribute);
		
		mockMvc.perform(post("/deletar"))
		.andDo(print())
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/listar"));
	}
}
