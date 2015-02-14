package h;


import javax.swing.JFrame;



public class Main {
	static boolean end=false;
	
	public static void main(String[] args) {
		//SETTING FRAME AND PANEL
		JFrame janela = new JFrame("Dragon Tale");
		janela.setContentPane(new Panel());
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setResizable(false);
		janela.setVisible(true);
		janela.pack();	
	}
	
}


