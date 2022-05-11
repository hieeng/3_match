package NWH.match.game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BGM {
	public static JTextField name = new JTextField("bgm.wav");
	public static File file = null;
	static AudioClip audioClip = null;
	
	//À½¾Ç Àç»ý
	public static void loop() {
		try {
			file = new File(name.getText());
			audioClip = Applet.newAudioClip(file.toURL());
			audioClip.loop();
		} catch (MalformedURLException mue) {
			JOptionPane.showMessageDialog(name, "Wrong file name.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void stop() {
		audioClip.stop();
	}
}
