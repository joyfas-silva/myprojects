package br.com.biblioteca.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.biblioteca.enums.RiscoEnum;
import br.com.biblioteca.enums.StatusEnum;
import br.com.biblioteca.model.Pessoa;

public class ProjetoDTO {

private Long id;
	
	private String nome;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataInicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataPrevisaoFim;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataFim;
	
	private String descricao;
	
	private StatusEnum status;
	
	private BigDecimal orcamento;
	
	private RiscoEnum risco;
	
	private Pessoa gerente;
	
	private List<Pessoa> membros;

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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataPrevisaoFim() {
		return dataPrevisaoFim;
	}

	public void setDataPrevisaoFim(Date dataPrevisaoFim) {
		this.dataPrevisaoFim = dataPrevisaoFim;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public BigDecimal getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(BigDecimal orcamento) {
		this.orcamento = orcamento;
	}

	public RiscoEnum getRisco() {
		return risco;
	}

	public void setRisco(RiscoEnum risco) {
		this.risco = risco;
	}

	public Pessoa getGerente() {
		return gerente;
	}

	public void setGerente(Pessoa gerente) {
		this.gerente = gerente;
	}

	public List<Pessoa> getMembros() {
		return membros;
	}

	public void setMembros(List<Pessoa> membros) {
		this.membros = membros;
	}
}
