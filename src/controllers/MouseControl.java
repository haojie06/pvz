package controllers;

import models.gamesystem.*;
import views.Canvas;
import views.CardView;
import views.MapView;
import views.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//所有点击事件的处理
public class MouseControl {
    private int number=0;
    private Canvas canvas;
    private Stage stage;
    private Image image,shadow;
    private ArrayList<String> grid;
    Image shovel=new ImageIcon("src/resources/images/interface/Shovel.png").getImage();
    Image shovel1=new ImageIcon("src/resources/images/interface/Shovel1.png").getImage();
    Image shovelback=new ImageIcon("src/resources/images/interface/Shovelback.png").getImage();
    public int pausestatus=0;
    int lineX, lineY;
    int modX,modY;//用于修饰植物的位置
    int clickFlag=0;   //clickFlag为1时代表已经点过卡槽
    String cardName;
    public MouseControl(Canvas canvas, Stage stage) {
        this.canvas = canvas;
        this.stage = stage;
        this.stage.graphics=canvas.getGraphics();
        List<List<MapBlock>> mapBlockList=stage.getMap().getMapBlockList();
       

        //以下为鼠标事件的测试,之后会移到其他地方实现
        //按以下这种方式获得点击坐标,然后写一个方法来获得这个坐标上的组件/对象 就可以实现相关功能了
        canvas.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //鼠标被拖动
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //鼠标移动
                lineX = e.getX();
                lineY = e.getY();
                //canvas.drawLine(lineX, lineY);//添加一个实体Image,并覆盖下方的image
                    if(clickFlag==1)
                    for (int i = 0; i < mapBlockList.size(); i++) {
                        List<MapBlock> blockList = mapBlockList.get(i);
                        for (int j = 0; j < blockList.size()-5; j++) {
                            MapBlock block = blockList.get(j);
                            block.changeStatus();
                            if (e.getX() > block.getPostionX() && e.getX() < block.getPostionX() + 80&&j>0) {
                                if(cardName.equals("铲子")) {
                                    if (e.getY() > block.getPostionY() && e.getY() < block.getPostionY() + 90 && block.isHasPlant()) {
                                        canvas.getGraphics().drawImage(shovel1, block.getPostionX() + 10, block.getPostionY() + 20, null);
                                    } else if (e.getY() > block.getPostionY() && e.getY() < block.getPostionY() + 90 && !block.isHasPlant()) {
                                        canvas.getGraphics().drawImage(shovel, block.getPostionX() + 10, block.getPostionY() + 20, null);
                                    }
                                }
                                else
                                if (e.getY() > block.getPostionY() && e.getY() < block.getPostionY() + 90 && !block.isHasPlant()) {
                                    canvas.getGraphics().drawImage(image, block.getPostionX() +modX, block.getPostionY() +modY, null);
                                }

                            }
                        }
                    }
                //可能会在这里再加一个卡槽的效果
            }
        });
        //鼠标点击所在的单元格内有可拾取物品时要进行处理
        canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //在移动至某个Block中时产生预计的位置
                System.out.println(Stage.getPlayerGroup());
                if(Stage.getPlayerGroup()==1) {
                    if (clickFlag == 1 && !cardName.equals("铲子")) {
                        for (int i = 0; i < mapBlockList.size(); i++) {
                            List<MapBlock> blockList = mapBlockList.get(i);
                            for (int j = 0; j < blockList.size() - 5; j++) {
                                MapBlock block = blockList.get(j);
                                block.changeStatus();
                                if (e.getX() > block.getPostionX() && e.getX() < block.getPostionX() + 80) {
                                    if (e.getY() > block.getPostionY() && e.getY() < block.getPostionY() + 90 && !block.isHasPlant()&& EconomySystem.buy(CardView.getUpgrids().get(number).cost)) {
                                        stage.getPlantFactory().genPlant(cardName, i, j);
                                        CardView.getUpgrids().get(number).clickstatus = 0;//0代表进入冷却
                                        block.setHasPlant(true);
                                    }
                                }
                            }
                        }
                        clickFlag = 0;
                    } else if (clickFlag == 1 && cardName.equals("铲子")) {
                        for (int i = 0; i < mapBlockList.size(); i++) {
                            List<MapBlock> blockList = mapBlockList.get(i);
                            for (int j = 0; j < blockList.size() - 5; j++) {
                                MapBlock block = blockList.get(j);
                                block.changeStatus();
                                if (e.getX() > block.getPostionX() && e.getX() < block.getPostionX() + 80 && j > 0) {
                                    if (e.getY() > block.getPostionY() && e.getY() < block.getPostionY() + 90 && block.isHasPlant()) {
                                        block.getPlants().get(0).setHealth(-1);
                                        System.out.println("铲除奸邪");
                                    }
                                }
                            }
                        }

                        clickFlag = 0;
                    }
                }else{
                    if (clickFlag == 1 && !cardName.equals("铲子")) {
                        for (int i = 0; i < mapBlockList.size(); i++) {
                            List<MapBlock> blockList = mapBlockList.get(i);
                            for (int j = 8; j < blockList.size(); j++) {
                                MapBlock block = blockList.get(j);
                                block.changeStatus();
                                if (e.getX() > block.getPostionX() && e.getX() < block.getPostionX() + 80) {
                                    if (e.getY() > block.getPostionY() && e.getY() < block.getPostionY() + 90 && !block.isHasPlant()) {
                                        stage.getZombieFactory().genZombie(cardName, i, j);
                                        CardView.getUpgrids().get(number).clickstatus = 0;//0代表进入冷却
                                        block.setHasPlant(true);
                                    }
                                }
                            }
                        }
                        clickFlag = 0;
                    }
                }

                for(int i=0;i<CardView.getUpgrids().size();i++){
                    if (e.getX()>420+i*50&&e.getX()<470+i*50&&e.getY()>10&&e.getY()<80){
                        if(CardView.getUpgrids().get(i).clickstatus==1){
                            clickFlag=1;
                            cardName=CardView.getUpgrids().get(i).getCardname();
                            CardView.getUpgrids().get(i).cardnumber=5;
                            number=i;
                            modX=0;
                            modY=0;
                            String str=CardView.getUpgrids().get(i).getPathname();
                            if(cardName.equals("樱桃炸弹"))
                            image=new ImageIcon("src/resources/PlantPng/"+str+"/dance/"+str+"7.png").getImage();
                            if(cardName.equals("食人花"))
                                image=new ImageIcon("src/resources/PlantPng/"+str+"/dance/"+str+"_hungry_1.png").getImage();
                            else image=new ImageIcon("src/resources/PlantPng/"+str+"/dance/"+str+"1.png").getImage();
                            System.out.println("你点击了:"+cardName);
                        }
                    }
                }


                if(e.getX()>900&&e.getX()<960&&e.getY()>10&&e.getY()<50){   //其他格子每次将X加50即可
                    clickFlag=1;
                    cardName="铲子";
                    modX=0;
                    modY=0;
                    image=shovel;

                    System.out.println("你点击了:"+cardName);
                }
                if(e.getX()>1000&&e.getX()<1060&&e.getY()>30&&e.getY()<70){   //其他格子每次将X加50即可
                     System.out.println("你点击了菜单");
                     MapView.menustatus=1;
                     MapView.grass=0;
                     pausestatus=1;
                }
                if(pausestatus==1) {            //三个按钮 pausestatus为1则可以响应
                    if (e.getX() > 440 && e.getX() < 550 && e.getY() > 300 && e.getY() < 340) { //左上角按钮 继续游戏
                          System.out.println("你点了第一个");
                          MapView.grass=1;
                          pausestatus=0;
                    }
                    if (e.getX() > 550 && e.getX() < 660 && e.getY() > 400 && e.getY() < 440) { //下方按钮   存储游戏
                        System.out.println("你点了第二个");
                        StageSave stageSave = new StageSave(stage);

                        try {

                            StageSave.serializeStage();

                        } catch (IOException e1) {

                            e1.printStackTrace();

                        }

                        MapView.grass=1;
                        pausestatus=0;
                    }
                    if (e.getX() > 660 && e.getX() < 770 && e.getY() > 300 && e.getY() < 340) { //右上角按钮   返回主菜单
                        System.out.println("你点了第三个");
                        MapView.grass=1;
                        MenuView m = new MenuView();
                        m.createPanel();
                        m.toShow();
                        pausestatus=0;
                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //点击的方格中是否包含可以拾取的对象
                if (clickFlag != 1) {
                    JudgeMent judgeMent = new JudgeMent(stage);
                    MapBlock clickBlock = judgeMent.clickWhich(e.getX(), e.getY());
                    if (clickBlock != null) {
                        if (clickBlock.containGetable()) {
                            //开始进行判断
                            judgeMent.clickGetJudge(e.getX(), e.getY(), clickBlock);
                        }
                    }
                }
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
