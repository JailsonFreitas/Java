package Seguradora;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import javax.swing.JOptionPane;


public class CadastroJuridico {
	private long numero_funcionarios;
	private long numero_visitas;
	private int ramo;
	private String cnpj;
	public static boolean saiu = false, ok = false, cancelar = false;
	public String nome;
	public String check;
	public double valor_imovel;
	public String endereco;
	public String ramo2;
	public double seguro;
	
	
	//Escolha Ramo 
		public static int ramoOpc() {
		int ramo;
		String ramoEscolha = "Seguradora - Escolha uma opção\n";
		ramoEscolha = ramoEscolha + "1 – Industria\n";
		ramoEscolha = ramoEscolha + "2 – Comércio\n";
		ramoEscolha = ramoEscolha + "3 – Agropecuaria\n";
		ramoEscolha = ramoEscolha + "0 – Sair";
		String aux = JOptionPane.showInputDialog(ramoEscolha);
		ramo = Integer.valueOf(aux);
		return ramo;
		}
	//Cadastro
	void cadastro(){
		VerificarEntrada verificar = new VerificarEntrada();
		ValidarDoc validar = new ValidarDoc();
		
		//Nome do cliente
				do{
					cancelar = false;
					ok = false;
					saiu = false;
				
					try{
						nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
						if (nome != null && nome.length() > 0) {
							ok = true;
						} else if (nome.length() == 0 && nome != null) {
							throw new Vazio();
						}
					}catch (NullPointerException ex) {
						cancelar = true;
						saiu = true;
						break;
					}catch(Vazio e){
					}
				}while (ok == false);
	
				//Endereço do cliente
				do {
					try {
						endereco = JOptionPane.showInputDialog("Digite o endereco do cliente:");

						if (endereco != null && endereco.length() > 0) {
							ok = true;
						} else if (endereco.length() == 0 && endereco != null) {
							throw new Vazio();
						}
					} catch (NullPointerException ex) {
						cancelar = true;
						saiu = true;
						break;
					} catch (Vazio e) {
					}
				} while (ok == false);
				
				//Valor do imovel
				
				do {
					try {
						check = JOptionPane.showInputDialog("Digite o valor do imovel:");

						if (check != null && check.length() > 0 && check.length() <= 19 && verificar.isCurrency(check) == true
								&& check.indexOf(",") == -1) {
							ok = true;
						} else if (check.length() == 0 && check != null) {
							throw new Vazio();
						} else if (check != null && verificar.isNumeric(check) == false) {
							throw new NumberFormatException();
						} else if (check.length() > 19 || check.indexOf(",") != -1) {
							throw new NumberFormatException();
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Você digitou um valor não aceito!");
					} catch (NullPointerException ex) {
						cancelar = true;
						saiu = true;
						break;
					} catch (Vazio e) {
					}
				} while (ok == false);
				
				if(!cancelar){
					valor_imovel = Float.parseFloat(check);
					System.out.println(valor_imovel);
				}
				
				// quantos funcionarios a empresa contem
				
				
				do {
					try {
						check = JOptionPane.showInputDialog("Informe a quantidade de funcionarios:");

						if (check != null && check.length() > 0 && check.length() <= 7 && verificar.isNumeric(check) == true) {
							ok = true;
						} else if (check.length() == 0 && check != null) {
							throw new Vazio();
						} else if (check != null && verificar.isNumeric(check) == false) {
							throw new NumberFormatException();
						} else if (check.length() > 7) {
							throw new NumberFormatException();
						}
					} catch (NullPointerException ex) {
						cancelar = true;
						saiu = true;
						break;
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Você digitou um valor não aceito!");
					} catch (Vazio e) {
					}
				} while (ok == false);

				if (!cancelar) {
					numero_funcionarios = Integer.parseInt(check);
					System.out.println(numero_funcionarios);
				}

				ok = false;

				// Visitas diarias
				
				do {
					try {
						check = JOptionPane.showInputDialog("Digite o numero de visitas:");

						if (check != null && check.length() > 0 && check.length() <= 7 && verificar.isNumeric(check) == true) {
							ok = true;
						} else if (check.length() == 0 && check != null) {
							throw new Vazio();
						} else if (check != null && verificar.isNumeric(check) == false) {
							throw new NumberFormatException();
						} else if (check.length() > 7) {
							throw new NumberFormatException();
						}
					} catch (NullPointerException ex) {
						cancelar = true;
						saiu = true;
						break;
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Você digitou um valor não aceito!");
					} catch (Vazio e) {
					}
				} while (ok == false);

				if (!cancelar) {
					numero_visitas = Integer.parseInt(check);
					System.out.println(numero_visitas);
				}
				
				//Escolha de ramo
				do {
					try {
						ramo = ramoOpc();
						switch(ramo){
							case 1:
								ramo2 = "Industria";
								break;
							case 2:
								ramo2 = "Comércio";
								break;
							case 3:
								ramo2 = "Agropecuaria";
								break;
						}break;
					} catch (NullPointerException ex) {
						cancelar = true;
						saiu = true;
						break;
					}
				} while (ramo >= 1 && ramo <= 3);
	}
	public void calculoSeguroEmpresarial() {
		seguro += valor_imovel * 0.04;

		int i;
		double acPorcentagem = 0, porFunc = 0, porVis = 0;

		if (numero_funcionarios >= 10) {
			porFunc = 0.002;
		}

		if (numero_visitas >= 200) {
			porVis = 0.003;
		}

		for (i = 1; i <= numero_funcionarios; i++) {
			if (i % 10 == 0) {
				porFunc += 0.002;
			}
		}

		for (i = 1; i <= numero_visitas; i++) {
			if (i % 200 == 0) {
				porFunc += 0.003;
			}
		}

		acPorcentagem = porFunc + porVis;

		seguro += valor_imovel * acPorcentagem;

		if (ramo == 0) {
			seguro += valor_imovel * 0.01;
		}

		if (ramo == 1) {
			seguro += valor_imovel * 0.005;
		}
	}

	public void salvarCadastro() {
		Pessoa c = new Pessoa(nome, seguro, false);

		try {

			FileOutputStream fos = new FileOutputStream(nome + ".bin");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);

			oos.close();
			fos.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro!");
		}
	}

	public void gerarContrato() {
		NumberFormat f = NumberFormat.getCurrencyInstance();

		try {
				FileWriter arq = new FileWriter(nome + ".txt");
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.printf("**CONTRATO**%n%nNome do cliente: " + nome + "%nEndereço: "
						+ endereco + "%nRamo: " + ramo2 + "%nValor do imóvel: " + f.format(valor_imovel)
						+ "%nNúmero de Funcionários: " + numero_funcionarios + "%nNúmero de visitas: " + numero_visitas
						+ "%nValor do seguro: " + f.format(seguro));

				JOptionPane.showMessageDialog(null, "Contrato salvo com sucesso como " + nome + ".txt !");
				arq.close();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar contrato!");
		}
	}
}
