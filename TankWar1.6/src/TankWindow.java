import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.impl.protocol.giopmsgheaders.KeyAddr;

public class TankWindow extends Frame
{   
	public int width,heigth;
	Tank myTank=new Tank(150,150,true,this);
	Tank enemyTank=new Tank(100,100,false,this);
	List<Missile> missiles=new ArrayList<Missile>();
	
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
	  
	  //���Ӳ˵�
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
	  
	  new Thread(new PaintThread()).start();
	}
	
	//ע����������˼�룬��̹����Tank�Լ������飬����Tank�Լ�ȥ��������ֱ�ӵ��������þ���
	public void paint(Graphics g)
	{   
		myTank.draw(g);
		enemyTank.draw(g);
		for (int i=0;i<missiles.size();i++)
		{
			Missile m=missiles.get(i);
			if (enemyTank.isLive())//���˻��ŵ�ʱ�򣬲��б�Ҫȥ��hitTank����
			m.hitTank(enemyTank);
			m.draw(g);
		}
		g.drawString("���� �ۼ�:"+missiles.size(), 30, 80);
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

  