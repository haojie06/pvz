package models.gamesystem;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//动作，以List记录了相应的动作，由某些实体持有
public class Motion implements Serializable {
    private static final long serialVersionUID = 1L;
    //动作列表，一个动作名字代表一个动作的图片链接序列
    //例如 僵尸应该有 移动，攻击，死亡等动作，这个类的初始化在实体的子类中
    private Map<String, List<String>> motionList = new HashMap<>();

    public Map<String, List<String>> getMotionList() {
        return motionList;
    }
    public void setMotionList(Map<String, List<String>> motionList) {
        this.motionList = motionList;
    }

    //添加指定动作的图片地址列表
    public void addMotionSrcList(String motionName, List<String> srcList) {
        motionList.put(motionName, srcList);
    }

    //获得指定动作的图片地址列表
    public List<String> getMotionSrcList(String motionName) {
        return motionList.get(motionName);
    }

}
