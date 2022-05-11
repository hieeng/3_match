package NWH.match.view.screen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

import NWH.match.game.BGM;
import NWH.match.view.layout.Buttons;
import NWH.match.view.layout.StartLayout;

public class StartScreen {

	public StartScreen() {
		JFrame frame = new JFrame ("s2016301027 남원희");
		frame.setLocation(500, 20);
		frame.setPreferredSize(new Dimension(1215, 995));
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(null);

		StartLayout drawPanel = new StartLayout();
		drawPanel.setLayout(null);
		drawPanel.setBounds(0, 0, 1200, 950);
		
		BGM.loop();
		BGM.stop();

		// 스타트
		Buttons startButton = new Buttons(250, 650) {
			public void actionPerformed(ActionEvent e) {
				new MapsScreen();
				frame.dispose();
			}
		};
		
		// 종료
		Buttons exitButton = new Buttons(650, 680) {
			public void actionPerformed(ActionEvent e) {
				BGM.stop();
				frame.dispose();
			}
		};

		contentPane.add(drawPanel);
		contentPane.add(startButton);
		contentPane.add(exitButton);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}