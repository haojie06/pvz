package models.gamesystem;

import views.CardView;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Grid extends JButton{
    public int clickstatus=1,cardnumber=1;
    public int status=0;
    public int time=0;
    private HashMap<String,Double> cooldown;
    private  HashMap<String,Integer> gold;
    public int cost;
    private  double cool;
    private  String cardname,pathname;
    private ImageIcon image;
    public int position;
    public Grid(String cardname,int x){
        this.cardname=cardname;
        this.position=x;
        cooldown=new HashMap<String, Double>();
        gold=new HashMap<String,Integer>();
        if(this.cardname.equals("樱桃炸弹")) {
            image = new ImageIcon("src/resources/card/CherryBomb/CherryBomb1.png");
            this.pathname = "CherryBomb";
        }
        if(this.cardname.equals("机枪射手")){
            image=new ImageIcon("src/resources/card/GatlingPea/GatlingPea1.png");
            this.pathname="GatlingPea";
        }
        if(this.cardname.equals("卷心菜投手")) {
            image = new ImageIcon("src/resources/card/Cabbage/Cabbage1.png");
            this.pathname = "Cabbage";
        }
        if(this.cardname.equals("土豆雷")) {
            image = new ImageIcon("src/resources/card/PotatoMine/PotatoMine1.png");
            this.pathname = "PotatoMine";
        }
        if(this.cardname.equals("太阳花")) {
            image = new ImageIcon("src/resources/card/SunFlower/SunFlower1.png");
            this.pathname = "SunFlower";
        }
        if(this.cardname.equals("食人花")) {
            image = new ImageIcon("src/resources/card/Chomper/Chomper1.png");
            this.pathname = "Chomper";
        }
        if(this.cardname.equals("高坚果")) {
            image = new ImageIcon("src/resources/card/TallWallNut/TallWallNut1.png");
            this.pathname = "TallWallNut";
        }
        if(this.cardname.equals("普通豌豆射手")) {
            image = new ImageIcon("src/resources/card/Peashooter/Peashooter1.png");
            this.pathname = "Peashooter";
        }
        if(this.cardname.equals("冰冻豌豆射手")){
            image=new ImageIcon("src/resources/card/SnowPea/SnowPea1.png");
            this.pathname="SnowPea";
        }
        if(this.cardname.equals("地刺王")) {
            image = new ImageIcon("src/resources/card/Spikerock/Spikerock1.png");
            this.pathname = "Spikerock";
        }
        if(this.cardname.equals("双向豌豆射手")) {
            image = new ImageIcon("src/resources/card/SplitPea/SplitPea1.png");
            this.pathname = "SplitPea";
        }
        if(this.cardname.equals("三头豌豆射手")) {
            image = new ImageIcon("src/resources/card/Threepeater/Threepeater1.png");
            this.pathname = "Threepeater";
        }
        if(this.cardname.equals("双头向日葵")) {
            image = new ImageIcon("src/resources/card/TwinSunflower/TwinSunflower1.png");
            this.pathname = "TwinSunflower";
        }

        if(this.cardname.equals("普通僵尸")) {
            image = new ImageIcon("src/resources/card/NormalZombie/NormalZombie1.png");
            this.pathname = "NormalZombie";
        }
        if(this.cardname.equals("墓碑")) {
            image = new ImageIcon("src/resources/card/GraveStone/GraveStone1.png");
            this.pathname = "GraveStone";
        }
        if(this.cardname.equals("运动员僵尸")) {
            image = new ImageIcon("src/resources/card/FootballZombie/FootballZombie1.png");
            this.pathname = "FootballZombie";
        }
        if(this.cardname.equals("爆炸僵尸")) {
            image = new ImageIcon("src/resources/card/JackinTheBoxZombie/JackinTheBoxZombie1.png");
            this.pathname = "JackinTheBoxZombie";
        }
        if(this.cardname.equals("小鬼僵尸")){
            image=new ImageIcon("src/resources/card/ImpZombie/ImpZombie1.png");
            this.pathname="ImpZombie";
        }
        if(this.cardname.equals("铁桶僵尸")) {
            image = new ImageIcon("src/resources/card/BucketHeadZombie/BucketHeadZombie.png");
            this.pathname = "BucketHeadZombie";
        }
        if(this.cardname.equals("路障僵尸")) {
            image = new ImageIcon("src/resources/card/ConeheadZombie/ConeheadZombie1.png");
            this.pathname = "ConeheadZombie";
        }
        if(this.cardname.equals("撑杆跳僵尸")) {
            image = new ImageIcon("src/resources/card/PoleVaultingZombie/PoleVaultingZombie1.png");
            this.pathname = "PoleVaultingZombie";
        }

        setIcon(image);
        setSize(50,70);
        cooldown.put("土豆雷",20d);
        cooldown.put("樱桃炸弹",50d);
        cooldown.put("地刺",7.5);
        cooldown.put("太阳花",7.5);
        cooldown.put("坚果墙",30d);
        cooldown.put("高坚果",30d);
        cooldown.put("卷心菜投手",7.5);
        cooldown.put("食人花",30d);
        cooldown.put("机枪射手",40d);
        cooldown.put("普通豌豆射手",20d);
        cooldown.put("地刺王",15d);
        cooldown.put("冰冻豌豆射手",30d);
        cooldown.put("双向豌豆射手",30d);
        cooldown.put("三头豌豆射手",30d);
        cooldown.put("双头向日葵",15d);

        cooldown.put("普通僵尸",30d);
        cooldown.put("墓碑",7.5);
        cooldown.put("运动员僵尸",30d);
        cooldown.put("爆炸僵尸",40d);
        cooldown.put("小鬼僵尸",15d);
        cooldown.put("铁桶僵尸",30d);
        cooldown.put("路障僵尸",30d);
        cooldown.put("撑杆跳僵尸",30d);

        gold.put("土豆雷",25);
        gold.put("樱桃炸弹",150);
        gold.put("地刺",100);
        gold.put("坚果墙",50);
        gold.put("太阳花",50);
        gold.put("卷心菜投手",100);
        gold.put("高坚果",125);
        gold.put("食人花",150);
        gold.put("机枪射手",100);
        gold.put("普通豌豆射手",100);
        gold.put("地刺王",125);
        gold.put("冰冻豌豆射手",175);
        gold.put("双向豌豆射手",125);
        gold.put("三头豌豆射手",300);
        gold.put("双头向日葵",125);

        gold.put("普通僵尸",50);
        gold.put("墓碑",50);
        gold.put("运动员僵尸",175);
        gold.put("爆炸僵尸",300);
        gold.put("小鬼僵尸",25);
        gold.put("铁桶僵尸",125);
        gold.put("路障僵尸",75);
        gold.put("撑杆跳僵尸",75);


        for(Map.Entry<String,Double> entry: cooldown.entrySet())
        {
            if(this.cardname.equals(entry.getKey()))
                cool=entry.getValue();
        }
        for(Map.Entry<String,Integer> entry: gold.entrySet())
        {
            if(this.cardname.equals(entry.getKey()))
                cost=entry.getValue();
        }
    }

    public double getCool() {
        return ((int)cool*200);
    }

    public void setCool(double cool) {
        this.cool = cool;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCardname() {
        return cardname;
    }

    public String getPathname() {
        return pathname;
    }
    public void changeCardPic(Graphics graphics){
        this.image = new ImageIcon("src/resources/card/" + pathname + "/" + pathname + cardnumber + ".png");
            if(cardnumber==1){
                cardnumber=5;
                clickstatus=1;
            }
            cardnumber--;
        CardView.drawCard(graphics);
    }
}
