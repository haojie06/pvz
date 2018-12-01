package views;

import models.gamesystem.EconomySystem;
import models.gamesystem.Map;
import models.gamesystem.MapBlock;

import javax.swing.*;
import java.awt.*;
import java.util.List;


//绘制地图
public class MapView  {

    int mapMoveRightMax = 310; //地图最多向右移动310px
    int curMove = 0; //当前地图偏移
    int curMoveDirection = 0;//当前地图移动方向 0不动 1左移 -1右移
    int moveDistance = 30; //每次移动的距离
    public static int menustatus=0,grass=0;//grass决定是否用草坪覆盖

    //构造方法传入graphy
    private Graphics graphic;

    public MapView(Graphics graphic) {
        this.graphic = graphic;
    }

    //之后需要参数,来自Stage
    public void drawMap(Map map) {
        Image cardplace=new ImageIcon("src/resources/images/interface/cardplace.png").getImage();
       // Image cardplace1=new ImageIcon("src/resources/images/interface/cardplace.png").getImage();
        Image shovel=new ImageIcon("src/resources/images/interface/Shovel.png").getImage();
        Image shovelback=new ImageIcon("src/resources/images/interface/Shovelback.png").getImage();
        Image pause=new ImageIcon("src/resources/images/interface/pause.png").getImage();
        Image b1=new ImageIcon("src/resources/images/interface/Button1.png").getImage();
        Image b2=new ImageIcon("src/resources/images/interface/Button2.png").getImage();
        Image b3=new ImageIcon("src/resources/images/interface/Button6.png").getImage();
        //需要从关卡中读取
        Image background = new ImageIcon(map.getBackGroundSrc()).getImage();
        /*this.add(new PlantCard("樱桃炸弹",1));*/
        //-310切换到地图模式2(僵尸基地)

        graphic.drawImage(background, curMove, 0, null);
        graphic.drawImage(cardplace,340,0,null);
        graphic.drawImage(shovelback,900,10,null);
        graphic.drawImage(shovel,900,10,null);
        graphic.drawImage(shovelback,1000,30,null);
        //graphic.drawImage(pause,500,30,null);
        if(menustatus==1) {
            graphic.drawImage(b1, 440, 300, null);
            graphic.drawImage(b2, 660, 300, null);
            graphic.drawImage(b3, 550, 400, null);
        }
        if(grass==1){
            graphic.drawImage(background, curMove, 0, null);
            graphic.drawImage(cardplace,340,0,null);
            graphic.drawImage(shovelback,900,10,null);
            graphic.drawImage(shovel,900,10,null);
            graphic.drawImage(shovelback,1000,30,null);
        }
        //视关卡而改变
        CardView.drawCard(graphic);
        List<List<MapBlock>> mapBlockList;
        mapBlockList = map.getMapBlockList();
        //打印金钱
        Font font = new Font("隶书", Font.BOLD, 15); // 定义字体对象
        graphic.setFont(font);  //设置graphics的字体对象S
        graphic.drawString(String.valueOf(EconomySystem.getMoney()), 360, 78);
        //graphic.setColor(Color.black);
        //graphic.drawLine(1000, 0, 1000, 1400);
        graphic.setColor(Color.BLACK);
 /*       for (int i = 0; i < mapBlockList.size(); i++) {
            for (int j = 0; j < mapBlockList.get(i).size(); j++) {
                MapBlock block = mapBlockList.get(i).get(j);
                //graphic.drawLine(block.getPostionX(), 0, block.getPostionX(), 700);
               // graphic.drawLine(0, block.getPostionY(), 1400, block.getPostionY());
            }
        }
*/
        //移动地图--如果
        moveMap(curMoveDirection);
    }

    //把地图移动到右边/移动回来 返回值代表移动是否 结束 0-未结束 1-结束 --移动地图的时候所有实体得向左移
    public int moveMap(int direction) {
        this.curMoveDirection = direction;
        //指令向右移动地图
        if (direction == -1) {
            curMove += direction * moveDistance;
            //向右移动至边缘
            if (curMove <= -1 * mapMoveRightMax) {
                curMove = -1 * mapMoveRightMax;
                this.curMoveDirection = 0;
                return 1;
            }
            return 0;
        }
        //左移回去
        else if (direction == 1) {
            curMove += direction * moveDistance;
            if (curMove >= 0) {
                curMove = 0;
                this.curMoveDirection = 0;
                return 1;
            }
        }
        return 0;
    }
}
