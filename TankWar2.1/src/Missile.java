import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;


public class Missile
{
	public static final int XSPEED=15;
	public static final int YSPEED=15;
	
	public static final int MISSILEWITH=10;
	public static final int MISSILEHEIGH=10;
	
	int x,y;
	Tank.Direction dir;
	
	private TankWindow tw;
	
	private boolean live=true;
	
	private boolean good;

	public Missile(int x, int y,Tank.Direction dir)
	{
		this.x = x;
		this.y = y;
		this.dir=dir;
	}
	
	public Missile(int x, int y,Tank.Direction dir,boolean good,TankWindow tw)
	{
		this(x,y,dir);
		this.good=good;
		this.tw=tw;
	}
	
	public void setLive(boolean live)
	{
		this.live = live;
	}

	public void draw(Graphics g)
	{   
		if(!live) 
		{
			tw.missiles.remove(this);
			return;
		}
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
		//清除容器中多余的子弹，不占有内存
		if (x<0 || y<0 || x>800 || y>800)
		{  
			live=false;
			
		}	
	}
	
	public Rectangle getRect()
	{
		return new Rectangle(x,y,MISSILEWITH,MISSILEHEIGH);
	}
	
	//处理子弹打到坦克不消失的情况
	public boolean hitTank(Tank t)
	{
		if (this.live&&this.getRect().intersects(t.getRect())&&t.isLive()&&this.good!=t.isGood())//注意要看看，tank是不是live
		{   
			t.setLive(false);
			this.live=false;
			Explode e=new Explode(x ,y ,this.tw);
			tw.explodes.add(e);
			return true;
		}
		else
			return false;
	}

    public boolean hitTanks(List<Tank> e)
    {   
    	for (int i=0;i<e.size();i++)
    	{
    		Tank t=e.get(i);
    		if (hitTank(t))
    		{
    			return true;
    		}
    	}
    	return false;
    }
	
}
