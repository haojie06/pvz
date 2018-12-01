package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import  models.gamesystem.Grid;
import models.gamesystem.Stage;

public class CardView extends JFrame {
    private Graphics graphics;
    private  ArrayList<Grid> grids;
    private static ArrayList<Grid> upgrids;
    private  static int positionX=0;
    static int ifStart = 0;
    public CardView(Graphics graphic) {
        this.graphics = graphic;
        grids = new ArrayList<Grid>();
        add(new Grid("樱桃炸弹", 0));

    }
    public CardView(){
        setLayout(null);
        ImageIcon icon=new ImageIcon("src/resources/card/PrepareGrowPlants.png");
        JButton jb=new JButton(icon);
        add(jb);
        jb.setSize(67,47);
        jb.setLocation(150, 100);
        /*ImageIcon icon1=new ImageIcon("src/resources/images/interface/cardplace1.png");
        JLabel jl=new JLabel(icon1);
        add(jl);
        jl.setBounds(0,0,450,80);*/


        grids=new ArrayList<Grid>();
        upgrids=new ArrayList<Grid>();
        this.setBounds(300,300,380,410);
        Toolkit toolkit=Toolkit.getDefaultToolkit();
        Dimension d=toolkit.getScreenSize();
        setLocation((d.width-getWidth())/2, (d.height-getHeight())/2);
        //Image image= this.createImage(this.getWidth(),this.getHeight());
        if(Stage.getPlayerGroup()==1) {
            Grid cherrybomb = new Grid("樱桃炸弹", 0);
            Grid potatomine = new Grid("土豆雷", 1);
            Grid gatlingpea = new Grid("机枪射手", 2);
            Grid cabbage = new Grid("卷心菜投手", 3);
            Grid sunflower = new Grid("太阳花", 4);
            Grid chomper = new Grid("食人花", 5);
            Grid tallwallnut = new Grid("高坚果", 6);
            Grid Peashooter = new Grid("普通豌豆射手", 7);
            Grid SnowPea = new Grid("冰冻豌豆射手", 8);
            Grid Spikerock = new Grid("地刺王", 9);
            Grid SplitPea = new Grid("双向豌豆射手", 10);
            Grid Threepeater = new Grid("三头豌豆射手", 11);
            Grid TwinSunflower = new Grid("双头向日葵", 12);
            grids.add(cherrybomb);
            grids.add(potatomine);
            grids.add(gatlingpea);
            grids.add(cabbage);
            grids.add(sunflower);
            grids.add(chomper);
            grids.add(tallwallnut);
            grids.add(Peashooter);
            grids.add(SnowPea);
            grids.add(Spikerock);
            grids.add(SplitPea);
            grids.add(Threepeater);
            grids.add(TwinSunflower);
            for (int i=0;i<grids.size();i++) {
                add(grids.get(i));
                if(i<7)
                    grids.get(i).setLocation(i*50,210);
                else
                    grids.get(i).setLocation(50*i-350,290);
            }

            setVisible(true);

            //准备按钮监听
            jb.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("开始游戏");
                    ifStart = 1;
                    startGame();
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });


            cherrybomb.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(cherrybomb);
                }
            });
            potatomine.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    ;
                    paintGrid(potatomine);
                }
            });
            gatlingpea.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(gatlingpea);
                }
            });
            cabbage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(cabbage);
                }
            });
            sunflower.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(sunflower);
                }
            });
            chomper.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(chomper);
                }
            });
            tallwallnut.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(tallwallnut);
                }
            });
            Peashooter.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(Peashooter);
                }
            });
            SnowPea.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(SnowPea);
                }
            });
            Spikerock.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(Spikerock);
                }
            });
            SplitPea.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(SplitPea);
                }
            });
            Threepeater.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(Threepeater);
                }
            });
            TwinSunflower.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(TwinSunflower);
                }
            });
        }else{
            Grid BuckHeadZombie=new Grid("铁桶僵尸",13);
            Grid ConeheadZombie=new Grid("路障僵尸",14);
            Grid GraveStone=new Grid("墓碑",165);
            Grid ImpZombie=new Grid("小鬼僵尸",16);
            Grid JackinTheBoxZombie=new Grid("爆炸僵尸",17);
            Grid PoleVaultingZombie=new Grid("撑杆跳僵尸",18);
            Grid NormalZombie=new Grid("普通僵尸",19);
            Grid FootballZombie=new Grid("运动员僵尸",20);
            grids.add(BuckHeadZombie);
            grids.add(ConeheadZombie);
            grids.add(GraveStone);
            grids.add(ImpZombie);
            grids.add(JackinTheBoxZombie);
            grids.add(PoleVaultingZombie);
            grids.add(NormalZombie);
            grids.add(FootballZombie);
            for (int i=0;i<grids.size();i++) {
                add(grids.get(i));
                if(i<7)
                    grids.get(i).setLocation(i*50,210);
                else
                    grids.get(i).setLocation(50*i-350,290);
            }
            setVisible(true);
            NormalZombie.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(NormalZombie);
                }
            });
            GraveStone.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(GraveStone);
                }
            });
            FootballZombie.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(FootballZombie);
                }
            });
            JackinTheBoxZombie.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(JackinTheBoxZombie);
                }
            });
            ImpZombie.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(ImpZombie);
                }
            });
            BuckHeadZombie.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(BuckHeadZombie);
                }
            });
            ConeheadZombie.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(ConeheadZombie);
                }
            });
            PoleVaultingZombie.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    paintGrid(PoleVaultingZombie);
                }
            });
            //准备按钮监听
            jb.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("开始游戏");
                    ifStart = 1;
                    startGame();
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }
    }
    public Graphics getGraphic(){
        return graphics;
    }

    public  void paintGrid(Grid agrid) {
        if (agrid.status == 0) {
       /*     agrid.setLocation(positionX, 0);
            positionX += 50;*/
            // agrid.position=-1;
            for (int i = 0; i < grids.size(); i++) {
                if (grids.get(i).equals(agrid)) {
                    upgrids.add(grids.get(i));
                    grids.remove(grids.get(i));
/*                    for (int j = 0; j < grids.size(); j++) {
                        if (j > i) {
                            grids.get(j).position -= 1;
                            grids.get(j).setLocation((grids.get(j).position) * 50, 210);
                        }
                    }*/
                }
            }
            agrid.status = 1;
        } else if(agrid.status==1){
            for (int i = 0; i < upgrids.size(); i++) {
                if (upgrids.get(i).equals(agrid)) {
                    grids.add(upgrids.get(i));
                    upgrids.remove(upgrids.get(i));
                }
            }
            agrid.status = 0;
        }
        for (int i = 0; i < grids.size(); i++) {
            if(i<7)
                grids.get(i).setLocation(i*50,210);
            else
                grids.get(i).setLocation(50*i-350,290);
        }
        for (int i = 0; i < upgrids.size(); i++) {
            if(i<7)
                upgrids.get(i).setLocation(i*50,10);
            else
                upgrids.get(i).setLocation(50*i-350,90);
        }
    }
    public static void drawCard(Graphics graphics){
        for(int i=0;i<upgrids.size();i++){
            Image image=upgrids.get(i).getImage().getImage();
            graphics.drawImage(image,420+i*50,10,null);

        }
    }

    public static ArrayList<Grid> getUpgrids() {
        return upgrids;
    }

    public void startGame() {
        this.dispose();
        //终止睡眠
    }

    public static int getIfStart() {
        return ifStart;
    }

    public static void setIfStart(int ifStart) {
        CardView.ifStart = ifStart;
    }
}
