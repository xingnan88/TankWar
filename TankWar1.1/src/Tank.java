import java.awt.*;
import java.awt.event.*;


public class Tank 
{   
	public static final int XSPEED = 5;
	public static final int YSPEED = 5;
	
	public static final int TANKWIDTH=30;
	public static final int TANKHEIGH=30;
	
	
	TankWindow tw;
	
	private int x,y;
	
	private boolean bL=false,bU=false,bR=false,bD=false;
	
	enum Direction {L,LU,U,RU,R,RD,D,LD,STOP};
	
	private Direction dir=Direction.STOP;
	
	public Tank(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Tank(int x, int y , TankWindow tw)
	{
		this(x,y);
		this.tw=tw;
	}
    
	public void draw(Graphics g)
	{
		Color c=g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x,y,TANKWIDTH,TANKHEIGH);
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
			x+=XSPEED;
			y-=YSPEED;
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
		    case KeyEvent.VK_CONTROL:
		    	tw.myMissile=fire();//持有对方的引用
		    	break;
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
	
	public Missile fire()
	{
		int x=this.x+Tank.TANKWIDTH/2-Missile.MISSILEWITH/2;
		int y=this.y+Tank.TANKHEIGH/2-Missile.MISSILEHEIGH/2;
		Missile m=new Missile(x,y,dir);
		return m;
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

	public void keyReleased(KeyEvent e)
	{
		int key=e.getKeyCode();
		switch(key)
		{
			case KeyEvent.VK_RIGHT:
				bR=false;
				break;
			case KeyEvent.VK_DOWN:
		        bD=false;
				break;
			case KeyEvent.VK_LEFT:
				bL=false;
				break;
			case KeyEvent.VK_UP:
				bU=false;
				break;
		}
		locationDirection();
	}
	
}
