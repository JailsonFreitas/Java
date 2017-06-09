package Seguradora;
import javax.swing.JOptionPane;

public class Vazio extends Exception{
	private static final long serialVersionUID = 2628380054499696578L;
	
	public Vazio(){
		JOptionPane.showMessageDialog(null, "Nenhum valor informado!");
	}
}
