package br.com.biblioteca.controller;

import java.util.Arrays;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.biblioteca.dto.ProjetoDTO;
import br.com.biblioteca.enums.AlertTypeEnum;
import br.com.biblioteca.enums.RiscoEnum;
import br.com.biblioteca.enums.StatusEnum;
import br.com.biblioteca.exeption.MyProjectsException;
import br.com.biblioteca.model.Projeto;
import br.com.biblioteca.service.IPessoaService;
import br.com.biblioteca.service.IProjetoService;

@Controller
public class ProjetoController {

	@Autowired
	private IProjetoService projetoService;
	
	@Autowired
	private IPessoaService pessoaService;
	
	private static  final String ERRO_SYS = "Erro de sistema: ";
	private static  final String REDIRECT_LISTAR = "redirect:/listar";
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("projetos", projetoService.findAll());
		return "/pages/index";
	}
	
	@PostMapping("/cadastro")
	public String cadastro(ProjetoDTO projeto, Model model, RedirectAttributes redirectAttributes) {
		String str = REDIRECT_LISTAR;
		
		if(projeto.getId() != null) {
			try {
				addAttrs(model, projetoService.findById(projeto.getId()).toDTO());
				str = "/pages/projeto/cadastro";
			} catch (MyProjectsException e) {
				setMessageRedirect(redirectAttributes, AlertTypeEnum.DANGER, e.getMessage());
			} catch (Exception e) {
				setMessageRedirect(redirectAttributes, AlertTypeEnum.DANGER, ERRO_SYS.concat(e.getMessage()));
			}
		}else {
			projeto.setStatus(StatusEnum.PLANEJADO);
			projeto.setDataInicio(new Date());
			
			addAttrs(model, projeto);
		}
		
		return str;
	}
	
	@PostMapping("/salvar")
	public String salvar(Model model, @Valid ProjetoDTO projeto, RedirectAttributes redirectAttributes) {
		try {
			projetoService.save(Projeto.toEntity(projeto));
			setMessageRedirect(redirectAttributes, AlertTypeEnum.SUCCESS, "Operação realizada com sucesso!");
		} catch (Exception e) {
			model.addAttribute("msgs", Arrays.asList(new MessageAlert(AlertTypeEnum.DANGER, ERRO_SYS.concat(e.getMessage()))));
			return cadastro(projeto, model, redirectAttributes);
		}
		
		return REDIRECT_LISTAR;
	}
	
	@PostMapping("/consultar")
	public String consultar(Long id, Model model, RedirectAttributes redirectAttributes) {
		String str = REDIRECT_LISTAR;
		try {
			model.addAttribute("consultar", true);
			addAttrs(model, projetoService.findById(id).toDTO());
			str = "/pages/projeto/cadastro";
		} catch (MyProjectsException e) {
			setMessageRedirect(redirectAttributes, AlertTypeEnum.DANGER, e.getMessage());
		} catch (Exception e) {
			setMessageRedirect(redirectAttributes, AlertTypeEnum.DANGER, ERRO_SYS.concat(e.getMessage()));
		}
		
		return str;
	}

	@PostMapping("/deletar")
	public String deletar(Long id, Model model, RedirectAttributes redirectAttributes) {
		
		try {
			projetoService.deleteById(id);
			setMessageRedirect(redirectAttributes, AlertTypeEnum.SUCCESS, "Exclusão realizada com sucesso!");
		} catch (MyProjectsException e) {
			setMessageRedirect(redirectAttributes, AlertTypeEnum.DANGER, e.getMessage());
		} catch (Exception e) {
			setMessageRedirect(redirectAttributes, AlertTypeEnum.DANGER, ERRO_SYS.concat(e.getMessage()));
		}
		
		return REDIRECT_LISTAR;
	}
	
	private void addAttrs(Model model, ProjetoDTO projeto) {
		
		model.addAttribute("gerentes", pessoaService.listGerentes());
		model.addAttribute("funcs", pessoaService.listFuncs());
		model.addAttribute("riscosOpt", RiscoEnum.map());
		model.addAttribute("statusOpt", StatusEnum.map());
		model.addAttribute("projeto", projeto);
	}
	
	private void setMessageRedirect(RedirectAttributes redirectAttributes, AlertTypeEnum alertTypeEnum, String msg) {
		redirectAttributes.addFlashAttribute("msgs", Arrays.asList(new MessageAlert(alertTypeEnum, msg)));
	}
}
