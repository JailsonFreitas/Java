package Seguradora;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import javax.swing.JOptionPane;

public class CadastroFisico {
	public String nome;
	public String endereco;
	public double valor_imovel;
	public double seguro;
	public static boolean saiu = false, ok = false, cancelar = false;
	public String check;
	private String cpf;
	private int zona;
	private int tipo;
	private String zon2;
	private String tipo2;
	
	//Escolha de zona
	public static int localOpc() {
	int local;
	String localEscolha = "Seguradora - Escolha uma opção\n";
	localEscolha = localEscolha + "1 – Zona urbana\n";
	localEscolha = localEscolha + "2 – Zona suburbana\n";
	localEscolha = localEscolha + "3 – Zona rural\n";
	localEscolha = localEscolha + "0 – Sair";
	String aux = JOptionPane.showInputDialog(localEscolha);
	local = Integer.valueOf(aux);
	return local;
	}
	
	//Escolha de tipo
		public static int tipoOpc() {
		int tipo;
		String tipoEscolha = "Seguradora - Escolha uma opção\n";
		tipoEscolha = tipoEscolha + "1 – Residência\n";
		tipoEscolha = tipoEscolha + "2 – Apartamento\n";
		tipoEscolha = tipoEscolha + "0 – Sair";
		String aux = JOptionPane.showInputDialog(tipoEscolha);
		tipo = Integer.valueOf(aux);
		return tipo;
		}
			
	//Cadastro Físico
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
		
		//Escolha de zona
				do {
					try {
						zona = localOpc();
						switch(zona){
							case 1:
								zon2 = "Urbana";
								break;
							case 2:
								zon2 = "Suburbana";
								break;
							case 3:
								zon2 = "Rural";
								break;
						}break;
					} catch (NullPointerException ex) {
						cancelar = true;
						saiu = true;
						break;
					}
				} while (zona >= 1 && zona <= 3);
				
				//Escolha de tipo
				do {
					try {
						tipo = tipoOpc();
						switch(tipo){
							case 1:
								tipo2 = "Residência";
								break;
							case 2:
								tipo2 = "Apartamento";
								break;
						}break;
					} catch (NullPointerException ex) {
						cancelar = true;
						saiu = true;
						break;
					}
				} while (tipo >= 1 && tipo <= 2);
	}
	
	//Calculo do Seguro Físico 
	public void calculoSeguroResidencial() {

		seguro += valor_imovel * 0.01;

		if (zona == 0) {
			seguro += valor_imovel * 0.01;
		}

		if (zona == 1) {
			seguro += valor_imovel * 0.025;
		}

		if (tipo == 0) {
			seguro += valor_imovel * 0.005;
		}
	}
	//Salvar Cadastro
	public void salvarCadastro() {
		Pessoa c = new Pessoa(nome, seguro, true);

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
	//Gerar Contrato 
	public void gerarContrato() {
		NumberFormat f = NumberFormat.getCurrencyInstance();

		try {
				FileWriter arq = new FileWriter(nome + ".txt");
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.printf("**CONTRATO**%n%nNome do cliente: " + nome + "%nEndereço: "
						+ endereco + "%nTipo de residência: " + tipo2 + "%nZona: " + zon2 + "%nValor do imóvel: "
						+ f.format(valor_imovel) + "%nValor do seguro: " + f.format(seguro));

				JOptionPane.showMessageDialog(null, "Contrato salvo com sucesso como " + nome + ".txt !");
				arq.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar contrato!");
		}
	}
}
