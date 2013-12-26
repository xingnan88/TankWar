import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Tank 
{   
	public static final int XSPEED = 5;
	public static final int YSPEED = 5;
	
	private int x,y;
	
	private boolean bL=false,bU=false,bR=false,bD=false;
	
	enum Direction {L,LU,U,RU,R,RD,D,LD,STOP};
	
	private Direction dir=Direction.STOP;
	
	public Tank(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
    
	public void draw(Graphics g)
	{
		Color c=g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x,y,30,30);
		g.setColor(c);
		
		move();
		
	}
	
	public void move()
	{
		switch(dir)
		{
		case L:
			x-=XSPEED;
			break;
		case LU:
			x-=XSPEED;
			y-=YSPEED;
			break;
		case U:
			y-=YSPEED;
			break;
		case RU:
			x-=XSPEED;
			y+=YSPEED;
			break;
		case R:
			x+=XSPEED;
			break;
		case RD:
			x+=XSPEED;
			y+=YSPEED;
			break;
		case D:
			y+=YSPEED;
			break;
		case LD:
			x-=XSPEED;
			y+=YSPEED;
			break;
		case STOP:
			break;
		}
	}
	
	public void keyPress(KeyEvent e)
	{
		int key=e.getKeyCode();
		switch(key)
		{
			case KeyEvent.VK_RIGHT:
				bR=true;
				break;
			case KeyEvent.VK_DOWN:
		        bD=true;
				break;
			case KeyEvent.VK_LEFT:
				bL=true;
				break;
			case KeyEvent.VK_UP:
				bU=true;
				break;
		}
		locationDirection();
	}
	
	public void locationDirection()
	{
		if(bL && !bU && !bR && !bD) dir=Direction.L;
		if(bL && bU && !bR && !bD) dir=Direction.LU;
		if(!bL && bU && !bR && !bD) dir=Direction.U;
		if(!bL && bU && bR && !bD) dir=Direction.RU;
		if(!bL && !bU && bR && !bD) dir=Direction.R;
		if(!bL && !bU && bR && bD) dir=Direction.RD;
		if(!bL && !bU && !bR && bD) dir=Direction.D;
		if(bL && !bU && !bR && bD) dir=Direction.LD;
		if(!bL && !bU && !bR && !bD) dir=Direction.STOP;	
	}
	
}
