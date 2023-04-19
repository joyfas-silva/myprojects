package br.com.biblioteca.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.biblioteca.dto.PessoaDTO;

@Entity
@Table
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nome;

	@Column(name = "datanascimento")
	private LocalDate dataNascimento;

	@Column
	private String cpf;

	@Column
	private Boolean funcionario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Boolean getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Boolean funcionario) {
		this.funcionario = funcionario;
	}
	
	public PessoaDTO toDTO() {
		PessoaDTO dto = new PessoaDTO();
		dto.setId(this.getId());
		dto.setNome(this.getNome());
		dto.setDataNascimento(this.getDataNascimento());
		dto.setCpf(this.getCpf());
		dto.setFuncionario(this.getFuncionario());
		
		return dto;
	}
	
	public static Pessoa toEntity(PessoaDTO dto) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(dto.getId());
		pessoa.setNome(dto.getNome());
		pessoa.setDataNascimento(dto.getDataNascimento());
		pessoa.setCpf(dto.getCpf());
		pessoa.setFuncionario(dto.getFuncionario());
		
		return pessoa;
	}
}
