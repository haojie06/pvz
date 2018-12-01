package models.gamesystem;

import Interface.Getable;
import models.entities.Entity;
import models.entities.weapon.bullets.Bullet;
import models.entities.weapon.bullets.LawnCleaner;
import models.entities.zombie.NormalZombie;
import models.mission.Mission;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//Map类 保有所有的MapBlock 以及地图图片
public class Map {
    Stage gameStage;
    //地图有几行
    private int maxRow = 5;
    //地图背景图片
    private String backGroundSrc;

    public Map(Stage gameStage) {
        this.gameStage = gameStage;
        //默认背景图片和最多行
        this.backGroundSrc = "src/resources/images/interface/background3.jpg";
    }
    //单元格起始绘制的位置
    private final int startX = 176, startY = 90, totalColumn = 15;
    List<List<MapBlock>> mapBlockList = new ArrayList<>();
    //之后可以根据不同地图加载不同的图片，这个在关卡选择之后就确定吧

    //初始化也要根据关卡的选择进行
    public void mapInitial() {
        System.out.println("地圖初始化中");
        this.maxRow = gameStage.getMission().getTotalRow();
        int curX = startX, curY = startY;
        //c=初始化地图/创建单元格 5行或者6行
        for (int i = 0; i < maxRow; i++) {
            curX = startX;
            List<MapBlock> blockList = new ArrayList<>();
            for (int j = 0; j < totalColumn; j++) {
                //i j 分别代表单元格是第几行第几列
                MapBlock block = new MapBlock(curX, curY, i, j);
                blockList.add(block);
                //X坐标增加80到下一个单元格
                curX += 80;
            }
            //高度增加 90
            mapBlockList.add(blockList);
            curY += 90;
        }

        //初始化六辆车
        List<Bullet> bullets = gameStage.getAllBuletList();
        bullets.add(new LawnCleaner(190, 100, 0));
        bullets.add(new LawnCleaner(190, 190, 1));
        bullets.add(new LawnCleaner(190, 280, 2));
        bullets.add(new LawnCleaner(190, 370, 3));
        bullets.add(new LawnCleaner(190, 460, 4));
        if (maxRow == 6) {
            bullets.add(new LawnCleaner(190, 550, 5));
        }
    }


    //更新地图状况:将实体放到单元格中
    public void updateMapStatus(Stage stage) {
        for (Entity e : stage.getAllEntity()) {
            int row = e.getRow();
            int col = e.getCol();
            List<MapBlock> mapBlocksY = new ArrayList<>();
            for (int i = 0; i < maxRow; i++) {
                //获得和实体一列的所有单元格
                mapBlocksY.add(stage.getMap().getMapBlockList().get(i).get(col));
            }
            List<MapBlock> mapBlocksX = mapBlockList.get(row);
            //这一行11列遍历
            for (int i = 0; i < totalColumn; i++) {
                MapBlock mapBlock = mapBlocksX.get(i);
                if ((e.getBoxFirPositionX()) < mapBlock.getPostionX() + 80 && (e.getBoxFirPositionX()) > mapBlock.getPostionX()) {
                    //移除上一个单元格中的的该实体，有待完善
                    if (i != totalColumn + e.getDirectionX() && i != 0) {
                        if (!(e instanceof Getable)) {
                            mapBlocksX.get(i - e.getDirectionX()).removeEntity(e);
                        }
                    }
                    e.setCol(i);
                    mapBlocksX.get(i).addEntity(e);
                }
            }


            //纵向遍历 6行 应对 天上掉下来的太阳/骷髅等等，除此之外的实体的row不应该改变
            for (int j = 0; j < maxRow; j++) {
                MapBlock mapBlock = mapBlocksY.get(j);
                if (e instanceof Getable && (e.getBoxFirPositionY()) < mapBlock.getPostionY() + 70 && (e.getBoxFirPositionY()) > mapBlock.getPostionY()) {
                    //移除上一个单元格中的实体 Y方向
                    if (j != 0) {
                        mapBlocksY.get(j - 1).removeEntity(e);
                    }
                    e.setRow(j);
                    mapBlocksY.get(j).addEntity(e);
                }
            }
        }
    }

    public List<List<MapBlock>> getMapBlockList() {
        return mapBlockList;
    }
    public void setMapBlockList(List<List<MapBlock>> mapBlockList) {
        this.mapBlockList = mapBlockList;
    }
    public MapBlock getBlock(int row, int col) {
        return mapBlockList.get(row).get(col);
    }

    public Entity place(int mouseX,int mouseY,Graphics graphics)  //
    {
        ImageIcon jiangshi=new ImageIcon("src/resources/images/interface/woshijiangshi.png");
           for(int i=0;i<mapBlockList.size();i++){
               List<MapBlock> blocklist=mapBlockList.get(i);
               for(int j=0;j<blocklist.size();j++){
                   MapBlock block =blocklist.get(j);
                   if(mouseX>block.getPostionX()&&mouseX<block.getPostionX()+80&&mouseY>block.getPostionY()&&mouseY<block.getPostionY()+90){
                       graphics.drawImage(jiangshi.getImage(),block.getPostionX(),block.getPostionY(),null);
                       Entity entity = new NormalZombie(block.getPostionX(), block.getPostionY());
                       return entity;
                   }
               }
           }
           return null;
    }

    public int getTotalRow() {
        return maxRow;
    }

    public int getTotalColumn() {
        return totalColumn;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }

    public String getBackGroundSrc() {
        return backGroundSrc;
    }

    public void setBackGroundSrc(String backGroundSrc) {
        this.backGroundSrc = backGroundSrc;
    }
}
