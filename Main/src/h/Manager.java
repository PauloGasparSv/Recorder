package h;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Manager{
	ArrayList<BufferedImage> images;
	Robot robot;
	private int w,h;
	private boolean saving;
	
	
	public Manager(){
		w = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		h = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		images = new ArrayList<BufferedImage>();
		saving = false;
	}

	public void Update(){
		if(!saving){
			try {
				robot = new Robot();
				BufferedImage image = robot.createScreenCapture(new Rectangle((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
						(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
				images.add(image);
			} catch (HeadlessException e) {
				e.printStackTrace();
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	public void Draw(Graphics2D g){
		if(!saving){
			BufferedImage image = images.get(images.size()-1);
			g.drawImage(image, 0, 0,Panel.LARGURA,Panel.ALTURA,null);
	
		}	
	}
	public void Save(){
		saving = true;
		int current = 0;
		int max = images.size();
		File file1 = new File("D:/Imagens/");
		
		file1.mkdir();
	
		File file = new File("D:/Imagens/image_"+current+".jpg");
		
		for(int i=0;i<max;i++){
			BufferedImage image = images.get(i);
			
			try {
				ImageIO.write(image, "jpg", file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			current++;
			file = new File("D:/Imagens/image_"+current+".jpg");
			System.out.println("SAVING");
		}
		
		file = new File("D:/Imagens/txt.txt");
		file.delete();
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileWriter writer;
		try {
			writer = new FileWriter("D:/Imagens/txt.txt", true);
			writer.append(""+max);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	

		System.exit(0);
	}
	
	
	
	
	public void keyPressed(int k) {
		if(k==KeyEvent.VK_ESCAPE){
			System.out.println("TRYING");
			Save();
		}
	}

	public void keyReleased(int e) {
		
	}

	
}
