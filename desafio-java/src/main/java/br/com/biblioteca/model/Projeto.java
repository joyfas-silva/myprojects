package br.com.biblioteca.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.biblioteca.dto.ProjetoDTO;
import br.com.biblioteca.enums.RiscoEnum;
import br.com.biblioteca.enums.StatusEnum;

@Entity
@Table
public class Projeto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private Date dataInicio;
	
	@Column
	private Date dataPrevisaoFim;
	
	@Column
	private Date dataFim;
	
	@Column
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	@Column
	private BigDecimal orcamento;
	
	@Enumerated(EnumType.STRING)
	private RiscoEnum risco;
	
	@OneToOne(optional=false)
    @JoinColumn(name="idgerente", referencedColumnName="id")
	private Pessoa gerente;
	
	@OneToMany
    @JoinTable(
        name="membros",
        joinColumns=
            @JoinColumn(name="idprojeto", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="idpessoa", referencedColumnName="id")
    )
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

	public String getStatusDesc() {
		return getStatus().getDesc();
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

	public String getRiscoDesc() {
		return getRisco().getDesc();
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
	
	public ProjetoDTO toDTO() {
		ProjetoDTO dto = new ProjetoDTO();
		dto.setId(this.getId());
		dto.setNome(this.getNome());
		dto.setGerente(this.getGerente());
		dto.setDataInicio(this.getDataInicio());
		dto.setDataFim(this.getDataFim());
		dto.setDataPrevisaoFim(this.getDataPrevisaoFim());
		dto.setMembros(this.getMembros());
		dto.setRisco(this.getRisco());
		dto.setStatus(this.getStatus());
		dto.setDescricao(this.getDescricao());
		dto.setOrcamento(this.getOrcamento());
		
		return dto;
	}

	public static Projeto toEntity(ProjetoDTO dto) {
		Projeto entity = new Projeto();
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setDataInicio(dto.getDataInicio());
		entity.setDataPrevisaoFim(dto.getDataPrevisaoFim());
		entity.setDataFim(dto.getDataFim());
		entity.setDescricao(dto.getDescricao());
		entity.setStatus(dto.getStatus());
		entity.setOrcamento(dto.getOrcamento());
		entity.setRisco(dto.getRisco());
		entity.setGerente(dto.getGerente());
		entity.setMembros(dto.getMembros());
		
		return entity;
	}
}
