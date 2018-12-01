package views;

import controllers.GameController;
import models.gamesystem.Stage;

import javax.swing.*;
import java.awt.*;

//画布类，所有的图像都在其上绘制，在里面调用特定的view的方法，例如UI绘制 UIViews ui; ui.draws();
//向具体的方法中传入Graphics 在具体方法中绘制，MODEL 通过 CONTROLLER 调用该方法画图
public class Canvas extends JPanel {
    JFrame jf;
    Canvas jp;
    private GameController gameController;
    private Image image;
    private Graphics graphics;
    private EntityView entityView;
    //绘图测试线
    int lineX = 0, lineY = 0;
    //僵尸倒计时
    private String Zcount = "";
    public void iniImage() {
        image = this.createImage(this.getWidth(), this.getHeight());
        graphics = image.getGraphics();
    }

    private MapView mapView;
    private EffectView effectView;
    //添加鼠标监听事件测试，之后写到controller中去
    //在创建对象时初始化canvas
    public Canvas() {
        System.out.println("Canvas初始化");
        jf = new JFrame();
        //jf.setBounds(50, 100, 1410, 640);
        //this.setSize(1400, 630);
        jf.setBounds(50, 100, 1100, 640);
        this.setSize(1100, 630);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.add(this);
        jf.setVisible(true);
        //初始化缓冲图片
        this.iniImage();
        mapView = new MapView(graphics);
        effectView = new EffectView(graphics);
        entityView = new EntityView(graphics);
    }

    public void drawGame(Stage stage) {
        super.paint(graphics);
        //绘制地图
        mapView.drawMap(stage.getMap());
        //绘制实体
        entityView.drawEntities(stage);
        effectView.drawEffects(stage);
        //绘制文字
        //倒计时
        if (Stage.getPlayerGroup() == 2) {
            drawString(getZcount(), 20, 50, 30);
        }
        //画红线
       // graphics.setColor(Color.red);
        //graphics.drawLine(lineX, 0, lineX, 700);
        //graphics.drawLine(0, lineY, 1400, lineY);
        this.repaint();
    }

    public void drawLine(int x, int y) {
        lineX = x;
        lineY = y;
    }

    public void drawString(String s, int x, int y, int fontSize) {
        Font font = new Font("隶书", Font.BOLD, fontSize); // 定义字体对象
        graphics.setColor(Color.black);
        graphics.setFont(font);  //设置graphics的字体对象S
        graphics.drawString(s, x, y);
    }



    /**
     * repaint方法会调用paint方法，并自动获得Graphics对像
     * 然后可以用该对像进行2D画图
     * 注：该方法是重写了JPanel的paint方法
     */

    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    public JFrame getJf() {
        return jf;
    }

    public void setJf(JFrame jf) {
        this.jf = jf;
    }

    public Canvas getJp() {
        return jp;
    }

    public void setJp(Canvas jp) {
        this.jp = jp;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public MapView getMapView() {
        return mapView;
    }

    public String getZcount() {
        return Zcount;
    }

    public void setZcount(String zcount) {
        Zcount = zcount;
    }
}


