package com.credsystem.timesheet;

public class Funcionario extends Pessoa{


	private Double salario;
	private Carro carro;

	public Double getSalario() {
		return salario;
	}
	
	public Double getSalarioAnual() {
		return getSalario() * 12;
	}
	

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	@Override
	public String toString() {
		
		return "Funcionario [nome=" + this.getNome() + ", idade=" + this.getIdade() + " salario=" + salario + ", carro=" + carro + "]";
	}
	
	
	
	
	
	
}
