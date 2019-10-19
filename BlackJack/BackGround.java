
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class BackGround extends JPanel {	


	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); 
		try {
      		Image img = ImageIO.read(new File("backGroundScreen.jpg"));
      		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    	} 
		catch (IOException e) {
      		e.printStackTrace();
		}
	
    }	              
}               

