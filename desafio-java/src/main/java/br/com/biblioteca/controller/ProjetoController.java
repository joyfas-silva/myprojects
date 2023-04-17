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
	
	@GetMapping("/listar")
	public String home(Model model) {
		model.addAttribute("projetos", projetoService.findAll());
		return "/pages/index";
	}
	
	@PostMapping("/cadastro")
	public String novo(Long id, Model model, RedirectAttributes redirectAttributes) {
		
		if(id != null) {
			try {
				addAttrs(model, projetoService.findById(id).get());
			} catch (MyProjectsException e) {
				setMessage(redirectAttributes, AlertTypeEnum.DANGER, e.getMessage());
			} catch (Exception e) {
				setMessage(redirectAttributes, AlertTypeEnum.DANGER, "Erro de sistema: ".concat(e.getMessage()));
			}
		}else {
			Projeto projeto = new Projeto();
			projeto.setStatus(StatusEnum.PLANEJADO);
			projeto.setDataInicio(new Date());
			
			addAttrs(model, projeto);
		}
		
		return "/pages/projeto/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(Model model, @Valid Projeto projeto, RedirectAttributes redirectAttributes) {
		
		try {
			projetoService.save(projeto);
			setMessage(redirectAttributes, AlertTypeEnum.SUCCESS, "Operação realizada com sucesso!");
		} catch (Exception e) {
			setMessage(redirectAttributes, AlertTypeEnum.DANGER, "Erro de sistema: ".concat(e.getMessage()));
		}
		
		return "redirect:/listar";
	}
	
	@PostMapping("/consultar")
	public String consultar(Long id, Model model, RedirectAttributes redirectAttributes) {
		
		try {
			model.addAttribute("consultar", true);
			addAttrs(model, projetoService.findById(id).get());
		} catch (MyProjectsException e) {
			setMessage(redirectAttributes, AlertTypeEnum.DANGER, e.getMessage());
		} catch (Exception e) {
			setMessage(redirectAttributes, AlertTypeEnum.DANGER, "Erro de sistema: ".concat(e.getMessage()));
		}
		
		return "/pages/projeto/cadastro";
	}

	@PostMapping("/delete")
	public String delete(Long id, Model model, RedirectAttributes redirectAttributes) {
		
		try {
			projetoService.deleteById(id);
			setMessage(redirectAttributes, AlertTypeEnum.SUCCESS, "Exclusão realizada com sucesso!");
		} catch (MyProjectsException e) {
			setMessage(redirectAttributes, AlertTypeEnum.DANGER, e.getMessage());
		} catch (Exception e) {
			setMessage(redirectAttributes, AlertTypeEnum.DANGER, "Erro de sistema: ".concat(e.getMessage()));
		}
		
		return "redirect:/listar";
	}
	
	private void addAttrs(Model model, Projeto projeto) {
		
		model.addAttribute("gerentes", pessoaService.listGerentes());
		model.addAttribute("funcs", pessoaService.listFuncs());
		model.addAttribute("riscosOpt", RiscoEnum.map());
		model.addAttribute("statusOpt", StatusEnum.map());
		model.addAttribute("projeto", projeto);
	}
	
	private RedirectAttributes setMessage(RedirectAttributes redirectAttributes, AlertTypeEnum alertTypeEnum, String msg) {
		return redirectAttributes.addFlashAttribute("msgs", Arrays.asList(new MessageAlert(alertTypeEnum, msg)));
	}
}
