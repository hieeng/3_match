package NWH.match.view.layout;

import java.awt.*;
import javax.swing.*;
import java.io.*;

import NWH.match.game.Data;
import NWH.match.game.Sound;
import NWH.match.view.screen.ScoreScreen;
  
public class MapsLayout extends JPanel {
    Image image[] = new Image[10];
    Toolkit toolkit = getToolkit();
    
    Data data = new Data();
    
    Image background = null;
    Image scoreBG = null;
    
    public int score = 0;
    
    int MAX_ROW = 9, MAX_COL=9;
	int mHeight = 9;
	int mWidth = 8;
	
	boolean state = true;
	
    int maps[][] = new int[MAX_ROW][MAX_COL];
    {
    	for(int i=0; i<=8; i++) {
    		for(int j=0; j<=8; j++) {
    			maps[i][j]=(int)(Math.random()*10);
    			while(maps[i][j]==0)
        			maps[i][j]=(int)(Math.random()*10);
    		}
    		}
    	}
    
    //이미지 생성
    public MapsLayout()
    {
    	for(int i=0; i < image.length; ++i)
    		image[i] = toolkit.getImage("0"+i+".png");
		data.initTime();
		background();
    }
    public void paint(Graphics g)  {
        g.clearRect(0, 0, getWidth(), getHeight());
        g.drawImage(background, 0, 0, 1200, 950, this);
        
        for(int row=0; row < 9; row++)
        	for(int col=0; col < 9; col++)
        		if (maps[row][col] > -1)
        			g.drawImage(image[maps[row][col]], col*80, row*80, 80, 80, this);
        if (_isSelected)
        {
        	int x = _selectedCol*80;
        	int y = _selectedRow*80;
        	
        	Graphics2D g2=(Graphics2D)g;
        	g2.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,0));
        	g2.drawRect(x, y, 80, 80);
        }
        
        drawTime(g);
        if(state) {
        	new TimeThread().start();
        	state = false;
        }
        drawScore(g);
    }
    
    public void background() {
    	background = toolkit.getImage("background.jpg");
    }
    
    //카운트    
	int getRunLengthX(int i, int j) {
		int leftCount=0, rightCount=0;
		int target = maps[i][j];
		
		for(int pos=j-1; pos>=0 && maps[i][pos]==target; pos--, leftCount++);
		for(int pos=j+1; pos<MAX_COL && maps[i][pos]==target; pos++, rightCount++);
		
		return(leftCount + rightCount);
	}
	int getRunLengthY(int i, int j) {
		int topCount=0, bottomCount=0;
		int target = maps[i][j];
		
		for(int pos=i-1; pos>=0 && maps[pos][j]==target; pos--, topCount++);
		for(int pos=i+1; pos<MAX_ROW && maps[pos][j]==target; pos++, bottomCount++);
		
		return(topCount + bottomCount);
	}
	
	//삭제
	public void delete() {
		//가로
		for(int y=0; y < MAX_ROW; y++) {
			for(int x=0; x < MAX_COL; x++) {
				if(getRunLengthX(y,x) >= 2) {
					int target = maps[y][x];
					for(int pos=x-1; pos>=0 && maps[y][pos]==target; pos--)
						 maps[y][pos]=0;
					for(int pos=x+1; pos<MAX_COL && maps[y][pos]==target; pos++)
						 maps[y][pos]=0;
					maps[y][x]=0;
					score += 1000; //스코어 +
				}
			}
		}
		//세로
		for(int x=0; x < MAX_COL; x++) {
			for(int y=0; y < MAX_ROW; y++) {
				if(getRunLengthY(y,x) >= 2) {
					int target = maps[y][x];
					for(int pos=y-1; pos>=0 && maps[pos][x]==target; pos--)
						maps[pos][x]=0;
					for(int pos=y+1; pos<MAX_COL && maps[pos][x]==target; pos++)
						maps[pos][x]=0;
					maps[y][x]=0;
					score += 1000; //스코어 +
				}
			}
		}
		new Sound().startSound(1); //효과음
		make(); //채우기
	}
    
    //타일 채우기
    public void make() { 
        for(int y=8; y>=1; y--) {
            for(int x=8; x>=0; x--) {
                if(maps[y][x]==0) { 
                    int i;
                    for(i = y-1; i > 0 && maps[i][x] == 0 ; --i )  // 비지않은 첫 블록 찾음
                        ;
                    if (maps[i][x] != 0) {    // 비지않은 블록이 있다면 drop.
                        maps[y][x] = maps[i][x];    
                        maps[i][x] = 0;
                    }
               }
            }
        }
        for(int x=0; x<9; x++) 
            if(maps[0][x]==0) {
                //맨위칸이 비어있다면, 그 밑칸도 비어있을수 있음.                    
                int i;
                for(i = 0; i <9 && maps[i][x]==0 ; ++i) { // 빈칸이 발견되지 않는 동안 블록을 채움
                    maps[i][x]=(int)(Math.random()*10);
                    while(maps[i][x]==0)
                    	maps[i][x]=(int)(Math.random()*10);
                }
            }
   }
    
    public boolean checkX() {
    	for(int y=0; y<9; y++) {
    		for(int x=0; x<9; x++) {
    			if(getRunLengthX(y,x) >=2)
    				return true;
    		}
    	}
    	return false;
    }
    public boolean checkY() {
    	for(int x=0; x<9; x++) {
    		for(int y=0; y<9; y++) {
    			if(getRunLengthY(y,x) >=2)
    				return true;
    		}
    	}
    	return false;
    }
    
    //스왑
    boolean _isSelected = false;
    int _selectedRow, _selectedCol;
    int checkRow, checkCol;
    
    public void clicked(int x, int y)
    {
    	int row = y/80;
    	int col = x/80;
    	if ( row > 8 || col > 8 )
    		return;
    	if ( !_isSelected ) {
    		_selectedRow = row;
    		_selectedCol = col;
    		_isSelected = true;
    	} else
    		// 십자표시만 움직이게
    		if(Math.abs(_selectedRow-row) == 0 && Math.abs(_selectedCol-col) <= 1) {
    			int temp = maps[_selectedRow][_selectedCol];
    			maps[_selectedRow][_selectedCol] = maps[row][col];
    			maps[row][col] = temp;
    			_isSelected = false;
    			//스왑후 삭제가 된경우
    			if(checkX() || checkY()) {
        			while(checkX())
        				delete();
        			while(checkY())
        				delete();
    			} else { //삭제가 안된경우
    				int temp2 = maps[_selectedRow][_selectedCol];
    				maps[_selectedRow][_selectedCol]=temp;
    				maps[row][col]= temp2;
    				new Sound().startSound(2);
    			}
    		}
    		else if(Math.abs(_selectedCol-col) == 0 && Math.abs(_selectedRow-row) <= 1) {
    			int temp = maps[_selectedRow][_selectedCol];
    			maps[_selectedRow][_selectedCol] = maps[row][col];
    			maps[row][col] = temp;
    			_isSelected = false;
    			
    			//스왑후 삭제가 된경우
    			if(checkX() || checkY()) {
        			while(checkX())
        				delete();
        			while(checkY())
        				delete();
    			} else { //삭제가 안된경우
    				int temp2 = maps[_selectedRow][_selectedCol];
    				maps[_selectedRow][_selectedCol]=temp;
    				maps[row][col]= temp2;
    				new Sound().startSound(2);
    			}
    			}
    		else
    			return;
    	repaint();
    	}
    
    //시간바	(외부 참조)
	public void drawTime(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(190, 820, 825, 90);
		g.setColor(Color.RED);
		g.fillRect(210, 840, 785, 50);
		g.clearRect(995 - (600 - data.getTime()) * 13 / 10, 840, (600 - data.getTime()) * 13 / 10, 50);
		g.setColor(Color.RED);
		g.fillRect(975 - (600 - data.getTime()) * 13 / 10, 840, 20, 50);
		g.setColor(Color.black);
		if(data.getTime()==100 || data.getTime()==80 || data.getTime()==60
				|| data.getTime()==40|| data.getTime()==20)
			new Sound().startSound(4);
		if(data.getTime()==0) { //게임 종료
			new Sound().startSound(3);
			storeScore();
			new ScoreScreen();
		}
	}
	// 외부참조
	public class TimeThread extends Thread {
		public void run() {
			while (data.getTime() > 0) {
				try {
					drawTime(getGraphics());
					Thread.sleep(100);
					data.setTime(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			repaint();
			}
		
		}
	
	//스코어 창
	public void drawScore(Graphics g) {
		scoreBG = toolkit.getImage("scoreBG.png");
		g.drawImage(scoreBG, 820, 190, 200, 50, this);
		g.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		g.setColor(Color.white);
		g.drawString("SCORE : " + score + "점", 850, 225);
	}
	
	//스코어 저장
	public void storeScore() {
		File file = new File("score.txt");
		String strScore = String.valueOf(score);
		
		try {
			FileWriter fw = new FileWriter(file, false);
			fw.write(strScore);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
