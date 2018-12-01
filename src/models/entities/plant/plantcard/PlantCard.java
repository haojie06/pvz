package models.entities.plant.plantcard;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class PlantCard extends JLabel{
    private Image image;
    private String cardname;
    private int grid,positionX,positionY;
    public  PlantCard(String cardname,int grid){
        this.cardname=cardname;
        this.grid=grid;
        this.positionX=370+50*grid;
        this.positionY=10;
        this.setBounds(positionX,positionY,50,70);
        if(this.cardname.equals("樱桃炸弹")) {
            this.image= new ImageIcon("src\\resources\\PlantPng\\CherryBomb\\dance\\CherryBomb1.png").getImage();//修改为卡槽图
        }
        if(this.cardname.equals("机枪射手")){
            this.image=new ImageIcon("src\\resources\\PlantPng\\GatlingPea\\dance\\GatlingPea1.png").getImage();
        }if(this.cardname.equals("普通豌豆射手")){
            this.image=new ImageIcon("src\\resources\\PlantPng\\Peashooter\\dance\\Peashooter.png").getImage();
        }
        /*if(this.cardname.equals())*/  //next plant here
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }
}
