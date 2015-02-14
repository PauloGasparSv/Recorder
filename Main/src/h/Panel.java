package h;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable,KeyListener{
	public static final int LARGURA=800;
	public static final int ALTURA=600;
	private int w,h;
	
	
	private Manager manager;
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage imagem;
	private Graphics2D g;
	
	private int FPS=60;
	private long targetTime=1000/FPS;
	
	public Panel(){
		super();
		setPreferredSize(new Dimension(LARGURA,ALTURA));
		setFocusable(true);
		requestFocus();
		
		manager = new Manager();		
	}
	
	public void addNotify(){
		super.addNotify();
		if(thread==null){
			thread=new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	public void Initialize(){
		w = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		h = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		File file1 = new File("D:/Imagens/");
		if(file1.isDirectory()){
			String[]entries = file1.list();
			for(String s: entries){
			    File currentFile = new File(file1.getPath(),s);
			    currentFile.delete();
			}
		}
	
		
		imagem = new BufferedImage(LARGURA, ALTURA, 
				BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) imagem.getGraphics();
		
		running=true;
	}
	
	
	
	public void run() {
		Initialize();
		
		long start;
		long elapsedTime;
		long wait;
		
		//GAME LOOP
		while(running){
			
			start = System.nanoTime();
			Update();
			Draw();
			DrawToScreen();
			elapsedTime= System.nanoTime()-start;
			wait = targetTime-elapsedTime/1000000;
			if(wait<0)
				wait=5;
			try{
				Thread.sleep(wait);
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ERROR");
				System.exit(0);
			}
		}
		
	}
	
	private void Update(){
		manager.Update();
	}
	private void Draw(){
		manager.Draw(g);
		
	}
	private void DrawToScreen(){
		Graphics g2 =getGraphics();
		g2.drawImage(imagem,0,0,LARGURA,ALTURA,null);
		g2.dispose();
	}
	
	
	
	
	//KEYLISTENER METHODS
	public void keyPressed(KeyEvent key) {
		manager.keyPressed(key.getKeyCode());
	}


	public void keyReleased(KeyEvent key) {
		manager.keyReleased(key.getKeyCode());
	}

	public void keyTyped(KeyEvent key) {
		
	}


	
}
