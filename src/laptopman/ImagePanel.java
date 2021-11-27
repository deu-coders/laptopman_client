package laptopman;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
//from w w w . j  av a 2 s  .c o  m
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

public class ImagePanel extends JPanel{
	Image image;

	  public ImagePanel(String src) {
		  if (src.startsWith("http://") || src.startsWith("https://")) {
			try {
				image = new ImageIcon(new URL("https://i.stack.imgur.com/MEBIB.gif")).getImage();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  else {
				image = Toolkit.getDefaultToolkit().getImage(src);
		  }
	  }

	  @Override
	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if (image != null) {
	      g.drawImage(image, 0, 0, this);
	    }
	  }
}
