package NWH.match.view.screen;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import NWH.match.game.BGM;
import NWH.match.game.Data;
import NWH.match.view.layout.*;

public class MapsScreen {
	
	public MapsScreen() {
		JFrame frame = new JFrame("s2016301027 남원희");
		frame.setLocation(500,20);
		frame.setPreferredSize(new Dimension(1215,995));
		
		BGM.loop();
		
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(null);
		
		MapsLayout maps = new MapsLayout();
		
		maps.addMouseListener(new MouseHandler(maps));
		maps.setLayout(null);
		maps.setBounds(0, 0, 1200, 950);
		contentPane.add(maps);
		//처음 생성시 3match 발생시 바로 삭제
		{
			maps.delete();
			while(maps.checkX()) {
				maps.delete();
			}
			while(maps.checkY()) {
				maps.delete();
			}
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}

