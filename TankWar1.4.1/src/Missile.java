import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Missile
{
	public static final int XSPEED=10;
	public static final int YSPEED=10;
	
	public static final int MISSILEWITH=10;
	public static final int MISSILEHEIGH=10;
	
	int x,y;
	Tank.Direction dir;
	
	private TankWindow tw;
	
	private boolean live=true;
	
	public Missile(int x, int y,Tank.Direction dir)
	{
		this.x = x;
		this.y = y;
		this.dir=dir;
	}
	
	public Missile(int x, int y,Tank.Direction dir,TankWindow tw)
	{
		this(x,y,dir);
		this.tw=tw;
	}
	public void draw(Graphics g)
	{
		Color c=g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x, y, MISSILEWITH,MISSILEHEIGH);
		g.setColor(c);

		move();
	}

	private void move()
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
		}
		
		if (x<0 || y<0 || x>800 || y>800)
		{  
			live=false;
			tw.missiles.remove(this);
		}	
	}
	
}
