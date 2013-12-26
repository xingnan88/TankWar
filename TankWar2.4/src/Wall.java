import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Wall
{
	private int x,y,width,height;
	private TankWindow tw;
  
	public Wall(int x, int y,int w,int h, TankWindow tw)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.tw = tw;
	}
	
	public void draw(Graphics g)
	{
		Color c=g.getColor();
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
		g.setColor(c);
	}
  	
	public boolean missileHitWall(Missile m)
	{
		if (this.getRect().intersects(m.getRect()))
		{
			m.setLive(false);
			return true;
		}
		return false;
	}
	
	public boolean tankHitWall(Tank k)
	{
		if (this.getRect().intersects(k.getRect()))
		{
			k.stay();
			return true;
		}
		return false;
	}
	
	public Rectangle getRect()
	{
		return new Rectangle(x,y,width,height);
	}
}
