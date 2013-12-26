import java.awt.*;
import java.awt.event.*;
import java.awt.*;

public class Window extends Frame
{
  public Window(int width,int highth,String title)
  {   
	  //设置窗口的基本属性
	  super.setTitle(title);
	  super.setSize(width,highth);
	  super.setVisible(true );
	 
	  
	  //添加菜单
	  MenuBar menuBar=new MenuBar();
	  this.setMenuBar(menuBar);
	  Menu menuFile=new Menu("文件");
	  MenuItem begin=new MenuItem("开始游戏");
	  MenuItem exit=new MenuItem("退出游戏");
	  menuFile.add(begin);
	  menuFile.add(exit);
	  menuBar.add(menuFile);
	  

   }

  
  public static void main(String args[])
  {
	  Window myFrame=new Window(800,800,"TankWar");
  }
}

  
