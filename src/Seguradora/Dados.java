package Seguradora;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class Dados {
	public void mostraContrato() {
		String nomeArq = JOptionPane.showInputDialog(null, "Informe o nome do titular:\n");

		try {
			if (nomeArq != null && nomeArq.equals("") == false) {
				JTextArea ta = new JTextArea(20, 20);
				nomeArq = nomeArq + ".txt";
				ta.read(new FileReader(nomeArq), null);
				ta.setEditable(false);
				JOptionPane.showMessageDialog(null, new JScrollPane(ta), "Seguradora", 2, null);
			} else if (nomeArq != null && nomeArq.equals("") == true) {
				throw new Vazio();
			}
		} catch (NullPointerException ex) {

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Este contrato não existe!");
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(null, "Este contrato não existe!");
		} catch (Vazio e) {
		}
	}
	
	public void lerCadastro() {

		String dadosPF = null, dadosPJ = null;

		try {
			FileFilter filter = new FileFilter() {
				public boolean accept(File file) {
					return file.getName().endsWith(".bin");
				}
			};

			File dir = new File("./");
			File[] files = dir.listFiles(filter);

			List<String> listaClientesPF = new ArrayList<>();
			List<String> listaClientesPJ = new ArrayList<>();

			for (int i = 0; i < files.length; i++) {
				FileInputStream fis = new FileInputStream(files[i]);
				ObjectInputStream ois = new ObjectInputStream(fis);

				Pessoa ler = (Pessoa) ois.readObject();

				NumberFormat f = NumberFormat.getCurrencyInstance();

				if (ler.isPessoaFisica() == true) {

					dadosPF = ("\n\nCliente\n\nNome : " + ler.getNome() + 
						 "\nValor do Seguro: " + f.format(ler.getSeguro()));
					listaClientesPF.add(dadosPF);
				} else if (ler.isPessoaFisica() == false) {
					dadosPJ = ("\n\nCliente\n\nNome : " + ler.getNome() + "\nValor do Seguro: " + f.format(ler.getSeguro()));
					listaClientesPJ.add(dadosPJ);
				}

			}

			JOptionPane.showMessageDialog(null,
					"PESSOA FISICA:" + listaClientesPF + "\n\nPESSOA JURIDICA:" + listaClientesPJ, "Seguradora", 2);

		} catch (NullPointerException | FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Ainda Não Realizou Cadastros");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
