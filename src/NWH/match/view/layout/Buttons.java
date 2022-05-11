package NWH.match.view.layout;

import java.applet.AudioClip;
import java.io.File;
import java.awt.event.*;

import javax.swing.*;

import NWH.match.*;
import NWH.match.game.Sound;

public class Buttons extends JButton implements ActionListener, MouseListener {
	JLabel label = null;
	JTextField name = new JTextField("enteredButton.wav");
	File file = null;
	AudioClip audioClip = null;
	
	//������
	public Buttons(int x, int y) {
		this.setBounds(x, y, 300, 100);
		this.setContentAreaFilled(false);
		
		this.addActionListener(this);
		this.addMouseListener(this);
	}
	
	//actionListener �������̵�
	public void actionPerformed(ActionEvent e) { }
	
	// mouseListener �������̵�
	public void mouseClicked(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) {
		new Sound().startSound(0);
	}
	public void mouseExited(MouseEvent e) {	}
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
}
