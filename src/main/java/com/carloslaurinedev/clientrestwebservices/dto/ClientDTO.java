package com.carloslaurinedev.clientrestwebservices.dto;

import java.time.Instant;

import com.carloslaurinedev.clientrestwebservices.entities.Client;

public class ClientDTO {

	private Long id;
	private String name;

	private String cpf;
	private Double income;

	private Instant birthDate;

	private Integer children;

	public ClientDTO() {

	}

	public ClientDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public ClientDTO(Client category) {
		this.id = category.getId();
		this.name = category.getName();
		this.cpf = category.getCpf();
		this.income = category.getIncome();
		this.birthDate = category.getBirthDate();
		this.children = category.getChildren();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}
}
