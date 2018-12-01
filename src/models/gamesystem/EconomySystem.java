package models.gamesystem;

import java.io.Serializable;
import java.nio.file.FileAlreadyExistsException;

//经济系统 将 阳光/骷髅都当作金钱看待
public class EconomySystem implements Serializable {
    //当前金钱 金钱用来购买植物/僵尸
    private static final long serialVersionUID = 1L;
    private static int curMoney = 800;

    //获得金钱，储存
    public static void addMoney(int addMoney) {
        curMoney += addMoney;
        System.out.println("EcoSys： 当前金钱增加了，现在为: " + curMoney);
    }

    //获知当前有多少钱（打印要用）
    public static int getMoney() {
        return curMoney;
    }

    //购买的方法 返回购买是否成功
    public static Boolean buy(int price) {
        if (price <= curMoney) {
            //消费
            curMoney -= price;
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public static int getCurMoney() {
        return curMoney;
    }

    public static void setMoney(int curMoney) {
        EconomySystem.curMoney = curMoney;
    }

    //归零
    public static void reset() {
        curMoney = 0;
    }
}
