import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.impl.protocol.giopmsgheaders.KeyAddr;

public class TankWindow extends Frame
{   
	public int width,heigth;
	Tank myTank=new Tank(600,600,true,Tank.Direction.STOP,this);
	List<Tank> enemyTanks=new ArrayList<Tank>();
	List<Missile> missiles=new ArrayList<Missile>();
	List<Explode> explodes=new ArrayList<Explode>();
	Wall w1=new Wall(100,300,30,300,this);
	Wall w2=new Wall(200,200,300,30,this);
	
	public TankWindow(int width,int heigth,String title)
	{   
	  //���ô��ڵĻ�������
	  this.width=width;
	  this.heigth=heigth;
	  super.setTitle(title);
	  super.setSize(width,heigth);
	  super.setVisible(true );
	  this.setResizable(false);
	  this.setBackground(Color.gray);
	  
	  //��Ӳ˵�
	  MenuBar menuBar=new MenuBar();
	  this.setMenuBar(menuBar);
	  Menu menuFile=new Menu("�ļ�");
	  MenuItem begin=new MenuItem("��ʼ��Ϸ");
	  MenuItem exit=new MenuItem("�˳���Ϸ");
	  menuFile.add(begin);
	  menuFile.add(exit);
	  menuBar.add(menuFile);
	  this.addKeyListener(new KeyMonitor());
	  
	  //�رմ���
	  this.addWindowListener(
              new WindowAdapter()
              {
             	 public void windowClosing(WindowEvent e)
             	 {
             		 System.exit(0);
             	 }
              }
      	);

	  for (int i=0;i<10;i++)
	  {   
		  Tank t=new Tank (150+50*i,60,false,Tank.Direction.D,this);
		  enemyTanks.add(t);
	  }
	  
	  new Thread(new PaintThread()).start();
	}
	
	//ע����������˼�룬��̹����Tank�Լ������飬����Tank�Լ�ȥ��������ֱ�ӵ��������þ���
	public void paint(Graphics g)
	{     
		myTank.draw(g);
		w1.draw(g);
		w2.draw(g);
		for (int i=0;i<enemyTanks.size();i++)
		{
			Tank e=enemyTanks.get(i);
			w1.tankHitWall(e);
			w2.tankHitWall(e);
			e.tanksHit(enemyTanks);
			e.draw(g);
		}
		
		for (int i=0;i<explodes.size();i++)
		{
			Explode e=explodes.get(i);
			e.draw(g);
		}
		
		for (int i=0;i<missiles.size();i++)
		{
			Missile m=missiles.get(i);
			m.hitTanks(enemyTanks);
			m.hitTank(myTank);
			w1.missileHitWall(m);
			w2.missileHitWall(m);
			m.draw(g);
		}
		g.drawString("�ӵ����� �ۼ�:"+missiles.size(), 30, 80);
		g.drawString("��ը���� �ۼ�"+explodes.size(), 30, 100);
		g.drawString("�������� �ۼƣ�"+enemyTanks.size(), 30, 120);
	}
	
	//������˸���󣬰Ѷ����Ȼ���һ��ͼƬ�ϣ�Ȼ����һ�𻭳�������дupdate����
	Image offScreamImage=null;
	public void update(Graphics g) 
	{
		if (offScreamImage==null)
		{
			offScreamImage=this.createImage(width,heigth);
		}
		Graphics graphics=offScreamImage.getGraphics();
		//ˢ������һ������Ļһ����ľ���
		Color c=graphics.getColor();
		graphics.setColor(Color.gray);
		graphics.fillRect(0, 0,width, heigth);
		graphics.setColor(c);
		
		paint(graphics);
		g.drawImage(offScreamImage, 0, 0, null);
	}
	
	//�߳�,ʵ��Runnable�ӿڣ����ػ�Բ,�ڲ���
	private class PaintThread implements Runnable
	{
		public void run() 
		{
			while(true)
			{
				repaint();
				try
				{
					Thread.sleep(50);
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//�ڲ��࣬ʵ��KeyAdapter�ӿ�
	private class KeyMonitor extends KeyAdapter
	{
		public void keyReleased(KeyEvent e)
		{
			if(myTank.isLive())
			myTank.keyReleased(e);
		}

		//ֱ�ӵ���keyPress
		public void keyPressed(KeyEvent e) 
		{
			myTank.keyPress(e);
		}
		
	}
	
    //main����
	public static void main(String args[])
	{
		TankWindow m=new TankWindow(800,800,"TankWar");
	}
}

  
