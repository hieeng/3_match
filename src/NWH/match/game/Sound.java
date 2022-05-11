package NWH.match.game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Sound {
	JTextField name[] = { 
			new JTextField("ȿ����.wav"), // ���콺 �Ҹ�
			new JTextField("���.wav"), // ���� �Ҹ�
			new JTextField("���.wav"), // ���� �Ұ� �Ҹ�
			new JTextField("���Ӿص�.wav"), // ���Ӿص� �Ҹ�
			new JTextField("�ð�Ҹ�.wav") // �ð��� �˹������� ���� �Ҹ�
			};
	// �Ҹ� ����
	File file[] = { 	
			new File(name[0].getText()),
			new File(name[1].getText()),
			new File(name[2].getText()),
			new File(name[3].getText()),
			new File(name[4].getText())
			};

	//�Ҹ����
	public void startSound(int i) {
		try {
			AudioClip audioClip = Applet.newAudioClip(file[i].toURL());
			audioClip.play();
		} catch (MalformedURLException mue) {
			JOptionPane.showMessageDialog(name[i], "Wrong file name.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}
}
