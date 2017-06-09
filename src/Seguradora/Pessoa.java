package Seguradora;
import java.io.Serializable;


public class Pessoa implements Serializable{
	private static final long serialVersionUID = 7331615495936945583L;
	String nome;
	double seguro;
	boolean isPessoaFisica;

	public Pessoa(String nome,  double seguro, boolean isPessoaFisica) {
		super();
		this.nome = nome;
		this.seguro = seguro;
		this.isPessoaFisica = isPessoaFisica;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getSeguro() {
		return seguro;
	}

	public void setSeguro(double seguro) {
		this.seguro = seguro;
	}

	public boolean isPessoaFisica() {
		return isPessoaFisica;
	}

	public void setPessoaFisica(boolean isPessoaFisica) {
		this.isPessoaFisica = isPessoaFisica;
	}
}
