package NWH.match.game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Sound {
	JTextField name[] = { 
			new JTextField("효과음.wav"), // 마우스 소리
			new JTextField("띠딩.wav"), // 삭제 소리
			new JTextField("띠용.wav"), // 스왑 불가 소리
			new JTextField("게임앤드.wav"), // 게임앤드 소리
			new JTextField("시계소리.wav") // 시간이 촉박해질때 나는 소리
			};
	// 소리 저장
	File file[] = { 	
			new File(name[0].getText()),
			new File(name[1].getText()),
			new File(name[2].getText()),
			new File(name[3].getText()),
			new File(name[4].getText())
			};

	//소리출력
	public void startSound(int i) {
		try {
			AudioClip audioClip = Applet.newAudioClip(file[i].toURL());
			audioClip.play();
		} catch (MalformedURLException mue) {
			JOptionPane.showMessageDialog(name[i], "Wrong file name.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}
}
