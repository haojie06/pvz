package test;

import javax.swing.*;
import java.awt.*;

class PaintTest extends JPanel {
    //僵尸是否活着
    static String imgSrc;
    static int ifLive = 1;
    static int x = 1300, y = 60;
    Image image = new ImageIcon("src//resources/images/interface/background3.jpg").getImage();
    Image zombie;
    static int num = 1, count = 1;

    //普通绘制
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (ifLive == 1) {
            if (num == 9) {
                num = 1;
            }
            imgSrc = "src//resources/ZombiePng/NormalZombie/move/z_normal_move_" + num + ".png";

            x -= 12;
            num++;
        }
        //僵尸死亡动画
        if (ifLive == 0) {
            if (!(count == 7)) {
                imgSrc = "src//resources/ZombiePng/NormalZombie/die/z_normal_dieHead_" + count + ".png";
                count++;
            }

        }


        g.drawImage(image, 0, 0, null);
        zombie = new ImageIcon(imgSrc).getImage();
        g.drawImage(zombie, x, 50, null);
        Graphics2D g2d = (Graphics2D) g;
        //g2d.drawRect(x - 10, y - 20, 100, 140);

    }

    public static void main(String[] args) {


        JFrame jf = new JFrame();
        PaintTest jp = new PaintTest();
        jf.setSize(1400, 640);
        //jf.setBounds(0, 0, 747, 320);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jp.setSize(1400, 600);
        jf.add(jp);
        //jf.add(jLabel);
        jf.setVisible(true);

        ifLive = 1;
        for (int i = 0; i < 40; i++) {

            try {
                jp.repaint();
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        ifLive = 0;
        //水平矫正因子
        x -= 70;
        //死亡
        for (int i = 0; i < 6; i++) {
            try {
                jp.repaint();
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        //ImageIcon icon = new ImageIcon("src//resources/images/Zombies/Zombie/ZombieDie.gif");
        //JLabel deadBody = new JLabel();
        //jp.add(deadBody);
        //deadBody.setBounds(50,50,icon.getIconWidth(),icon.getIconHeight());


    }
}