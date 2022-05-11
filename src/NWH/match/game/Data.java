package NWH.match.game;

public class Data {
	int time = 600;
	
	public void initTime() {
		time = 600;
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time -= time;
	}
}
