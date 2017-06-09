package Seguradora;
import javax.swing.JOptionPane;

public class Principal {
	 public static int menu() {
		 int opcao;
	     String menu = "Seguradora - Escolha uma opção\n";
	     menu = menu + "1 – Cadastrar\n";
	     menu = menu + "2 – Pesquisar contrato\n";
	     menu = menu + "3 – Cadastros\n";
	     menu = menu + "0 – Sair";
	     String aux = JOptionPane.showInputDialog(menu);
	     opcao = Integer.valueOf(aux); //vai chamar a tela de menu //
	     return opcao;
	    }
	 public static int cadastro() {
		 int opcao2;
	     String cadastro = "Cadastro - Escolha uma opção\n";
	     cadastro = cadastro + "1 – Pessoa física\n";
	     cadastro = cadastro + "2 – Pessoa jurídica\n";
	     cadastro = cadastro + "0 – Voltar";
	     String aux = JOptionPane.showInputDialog(cadastro);
	     opcao2 = Integer.valueOf(aux);
	     return opcao2; // vai chamar a tela de cadastros//
	    }
	 
	 public static void main(String[] args) {
		 int op1;
		 int op2;
		 
		 CadastroFisico cadastroFisico = new CadastroFisico();
		 CadastroJuridico cadastroJuridico = new CadastroJuridico();
		 Dados dados = new Dados();
		 
		 do{
			 op1 = menu();
	         switch (op1){
	         	case 1: 
	         		do{
	         			op2 = cadastro();
	         			switch(op2){
	         				case 1:
	         					cadastroFisico.cadastro();
	         					cadastroFisico.calculoSeguroResidencial();
	         					cadastroFisico.salvarCadastro();
	         					cadastroFisico.gerarContrato();
	         					break;
	         				case 2:
	         					cadastroJuridico.cadastro();
	         					cadastroJuridico.calculoSeguroEmpresarial();
	         					cadastroJuridico.salvarCadastro();
	         					cadastroJuridico.gerarContrato();
	         					break;
	         			}
	         		}while (op2 >= 1 && op2 <= 2);
	         		break;
	         	case 2:
	         		dados.mostraContrato();
	         		break;
	         	case 3:
	         		dados.lerCadastro();
	         		break;
	         }
		}while (op1 >= 1 && op1 <= 3);
	 }
}
