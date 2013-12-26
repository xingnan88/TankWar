import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class Tank 
{   
	public static final int XSPEED = 10;
	public static final int YSPEED = 10;
	
	public static final int TANKWIDTH=30;
	public static final int TANKHEIGH=30;
	
	TankWindow tw;
	
	private boolean live=true;
	
	private static Random random=new Random();
	private int step=random.nextInt(12)+3;
	
	private int x,y;
	
	private boolean bL=false,bU=false,bR=false,bD=false;
	
	enum Direction {L,LU,U,RU,R,RD,D,LD,STOP};
	
	private Direction dir=Direction.STOP;
	private Direction ptDir=Direction.D;
	
	private boolean good;//判断是自己 的坦克，还是敌人
	
	public boolean isGood()
	{
		return good;
	}

	public Tank(int x, int y ,boolean good)
	{
		this.x = x;
		this.y = y;
		this.good=good;
	}
	
	public Tank(int x, int y , boolean good,Direction dir,TankWindow tw)
	{
		this(x,y,good);
		this.dir=dir;
		this.tw=tw;
	}
	
    
	public void draw(Graphics g)
	{	
	    if (!live)
	    {
	    	if (!good)
	    	{
	    		tw.enemyTanks.remove(this);//把死的坦克给从容器里给移除
	    	}
	        return;
	    }
		Color c=g.getColor();
		
		if (good)
		{g.setColor(Color.ORANGE);}
		else
		{g.setColor(Color.blue);}
		
		g.fillOval(x,y,TANKWIDTH,TANKHEIGH);
		g.setColor(c);
		//这个和其他几个部分，处理坦克静止的时候炮弹打不出去的情况，加一根炮管
		switch(ptDir)
		{
		case L:
			g.drawLine(x+Tank.TANKWIDTH/2, y+Tank.TANKHEIGH/2, x, y+Tank.TANKHEIGH/2);
			break;
		case LU:
			g.drawLine(x+Tank.TANKWIDTH/2, y+Tank.TANKHEIGH/2, x, y);
			break;
		case U:
			g.drawLine(x+Tank.TANKWIDTH/2, y+Tank.TANKHEIGH/2, x+Tank.TANKWIDTH/2, y);
			break;
		case RU:
			g.drawLine(x+Tank.TANKWIDTH/2, y+Tank.TANKHEIGH/2, x+Tank.TANKWIDTH, y);
			break;
		case R:
			g.drawLine(x+Tank.TANKWIDTH/2, y+Tank.TANKHEIGH/2,  x+Tank.TANKWIDTH, y+Tank.TANKHEIGH/2);
			break;
		case RD:
			g.drawLine(x+Tank.TANKWIDTH/2, y+Tank.TANKHEIGH/2, x+Tank.TANKWIDTH, y+Tank.TANKHEIGH);
			break;
		case D:
			g.drawLine(x+Tank.TANKWIDTH/2, y+Tank.TANKHEIGH/2, x+Tank.TANKWIDTH/2, y+Tank.TANKHEIGH);
			break;
		case LD:
			g.drawLine(x+Tank.TANKWIDTH/2, y+Tank.TANKHEIGH/2, x, y+Tank.TANKHEIGH);
			break;
		}
		
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
		
		if (this.dir!=Direction.STOP)
			this.ptDir=this.dir;
		
		//处理坦克出界的问题，嘿嘿，自己写出来的，还和马士兵一样
		if (x<0)x=0;
		if (y<60)y=60;
		if (x>(800-TANKWIDTH))x=800-TANKWIDTH;
		if (y>(800-TANKHEIGH))y=800-TANKHEIGH;
		
		if (!good)
		{  //让敌人随机动起来
			Direction[] dirs=Direction.values();
			if (step==0)
			{   
				step =random.nextInt(12)+3;
				int rn=random.nextInt(dirs.length);
				dir=dirs[rn];
			}	
			step--;
			
			if (random.nextInt(40)>=38) this.fire(); //让敌人攻击
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
	
	public Missile fire()
	{
		int x=this.x+Tank.TANKWIDTH/2-Missile.MISSILEWITH/2;
		int y=this.y+Tank.TANKHEIGH/2-Missile.MISSILEHEIGH/2;
		Missile m=new Missile(x,y,ptDir,this.good,this.tw);
		tw.missiles.add(m);
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
		    case KeyEvent.VK_CONTROL:
		    	tw.missiles.add(fire());//持有对方的引用
		    	break;
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

	public Rectangle getRect()
	{
		return new Rectangle(x,y,TANKWIDTH,TANKHEIGH);
	}

	public void setLive(boolean live)
	{
		this.live = live;
	}

	public boolean isLive()
	{
		return live;
	}
	
	
	
}
