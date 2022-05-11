package NWH.match.view.layout;

import java.awt.*;
import javax.swing.JPanel;
import java.io.*;

import NWH.match.game.Data;


public class ScoreLayout extends JPanel{
	String score = "";
	char[] inData = new char[6];
	
	Toolkit toolkit = getToolkit();
	Image background = null;
	Image scoreBackgroundImage = null;
	Image button = null;
	
	void setScore() {
		try {
		FileReader reader = new FileReader("score.txt");
		reader.read(inData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<6; i++) {
			score += inData[i];
		}
	}
	
	public ScoreLayout() {
		background = toolkit.getImage("background.jpg");
		scoreBackgroundImage = toolkit.getImage("input.png");
		button = toolkit.getImage("menuButton.png");
		setScore();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.drawImage(background, 0, 0, 1200, 950, this);
		g.drawImage(scoreBackgroundImage, 150, 150, 900, 600, this);		
		
		g.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 100));
		g.drawString(score + "Á¡", 400, 400); // Á¡¼ö
		
		g.drawImage(button, 520, 600, 180, 185, this);
	}
	
}
