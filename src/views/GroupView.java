package views;

import models.gamesystem.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GroupView extends JFrame {
    public GroupView(){
      //  setLayout(GridLayout);

        setLayout(null);
        ImageIcon icon1=new ImageIcon("src/resources/images/interface/Button4.png");
        ImageIcon icon2=new ImageIcon("src/resources/images/interface/Button5.png");
        ImageIcon icon3=new ImageIcon("src/resources/images/interface/group.png");
        ImageIcon mission1=new ImageIcon("src/resources/images/interface/Button1(1).png");
        ImageIcon mission2=new ImageIcon("src/resources/images/interface/Button2(1).png");
        ImageIcon mission3=new ImageIcon("src/resources/images/interface/Button3(1).png");
        JLabel jb=new JLabel(icon3);
        JButton jb1=new JButton(icon1);
        JButton jb2=new JButton(icon2);
        JButton m1=new JButton(mission1);
        JButton m2=new JButton(mission2);
        JButton m3=new JButton(mission3);

        setSize(icon3.getIconWidth(),icon3.getIconHeight());
        Toolkit toolkit=Toolkit.getDefaultToolkit();
        Dimension d=toolkit.getScreenSize();
        setLocation((d.width-getWidth())/2, (d.height-getHeight())/2);
        add(jb);
        add(jb1);
        add(jb2);
        add(m1);
        add(m2);
        add(m3);
        jb.setBounds(-10,-10,icon3.getIconWidth(),icon3.getIconHeight());
        jb1.setBounds(60,226,icon1.getIconWidth(),icon1.getIconHeight());
        jb2.setBounds(310,226,icon2.getIconWidth(),icon2.getIconHeight());
        m1.setBounds(100,200,mission1.getIconWidth(),mission1.getIconHeight());
        m2.setBounds(100,300,mission2.getIconWidth(),mission2.getIconHeight());
      //  m3.setBounds();
        jb1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Stage.setPlayerGroup(1);
                new CardView();
                close();
            }
        });
        jb2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Stage.setPlayerGroup(2);
                new CardView();
                close();
            }
        });
        setVisible(true);
    }
    public void close(){
        this.dispose();
    }
}
