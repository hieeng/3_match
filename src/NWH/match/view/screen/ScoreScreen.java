package NWH.match.view.screen;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

import NWH.match.view.layout.Buttons;
import NWH.match.view.layout.ScoreLayout;

public class ScoreScreen {
	
	public ScoreScreen() {
		JFrame frame = new JFrame("s2016301027 ³²¿øÈñ");
		frame.setLocation(500,20);
		frame.setPreferredSize(new Dimension(1215,995));
		
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(null);
		
		ScoreLayout drawPanel = new ScoreLayout();
		drawPanel.setBounds(0, 0, 1200, 950);
		contentPane.add(drawPanel);
		
		Buttons menuButton = new Buttons(520, 600) {
			public void actionPerformed(ActionEvent e) {
				new StartScreen();
				frame.dispose();
			}
		};
		
		contentPane.add(drawPanel);
		contentPane.add(menuButton);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
