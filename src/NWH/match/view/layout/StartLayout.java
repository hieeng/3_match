package NWH.match.view.layout;

import java.awt.*;
import javax.swing.*;

public class StartLayout extends JPanel {
	Toolkit toolkit = getToolkit();
	Image backgroundImage = null;
	Image icon = null;
	Image startButton = null;
	Image exitButton = null;
	
	public StartLayout() {
		backgroundImage = toolkit.getImage("startBackgroundPanel.jpg");
		icon = toolkit.getImage("icon.jpg");
		startButton = toolkit.getImage("startButton.png");
		exitButton = toolkit.getImage("exitButton.png");
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, 1200, 950);
		g.drawImage(backgroundImage, 0, 0, 1200, 950, this);
		g.drawImage(icon, 450, 200, 300, 300, this);
		g.drawImage(startButton, 250, 650, 310, 180, this);
		g.drawImage(exitButton, 650, 680, 300, 110, this);
	}
}
