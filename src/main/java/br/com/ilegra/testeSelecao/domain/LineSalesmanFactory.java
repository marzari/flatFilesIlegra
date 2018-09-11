package br.com.ilegra.testeSelecao.domain;

/**
 * @author tiago
 * @since 10/09/2018
 */
public class LineSalesmanFactory extends Line {

	private String cpf;
	private String name;
	private Double salary;

	public LineSalesmanFactory(String cpf, String name, String salary) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.salary = Double.parseDouble(salary);
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "Nome: " + name + ", CPF: " + cpf + ", Salário: R$ " + salary.toString().replace(".", ",");
	}

}
