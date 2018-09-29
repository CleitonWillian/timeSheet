package com.credsystem.timesheet;

public class TestePrograma {
	
	public static void main(String[] args) {

		String nome = "Diego";
		int idade = 33;

		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome(nome);
		funcionario.setIdade(idade );
		funcionario.setSalario(90000.0);
		
		Carro carro = new Carro("Gol","22121" );
	
		funcionario.setCarro(carro) ;
		
		funcionario.getCarro().setModelo("Palio");
		
		System.out.println(funcionario);
		System.out.println("Salario anual:" + funcionario.getSalarioAnual());
		
	}
}
