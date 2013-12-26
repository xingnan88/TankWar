import java.awt.Color;
import java.awt.Graphics;


public class Explode
{
	
	int x,y;
	boolean live=true;
	
	private TankWindow tw;
	
	int[] radius={6,20,30,60,70,80,50,20,6};
	int step=0;
	
	public Explode(int x, int y ,TankWindow tw)
	{
		this.x = x;
		this.y = y;
		this.tw=tw;
	}
	
	public void draw(Graphics g)
	{
		if (!live) 
		{
			tw.explodes.remove(this);
			return;
		}
		if (step>=radius.length)
		{
			live=false;
			step=0;
		}
		Color c=g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, radius[step],radius[step]);
		g.setColor(c);
		step++;
	}
	
}
