package models.gamesystem;

import java.io.Serializable;

//得分系统
public class ScoreSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    private static int curScore;

    public static void addScore(int add) {
        curScore += add;
    }

    //获知当前分数
    public static int getScore() {
        return curScore;
    }

    //清零
    public static void reset() {
        curScore = 0;
    }
    public  static  void setScore(int i){
         curScore=i;
    }

    public static void setCurScore(int curScore) {
        ScoreSystem.curScore = curScore;
    }
}
