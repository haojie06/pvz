package models.gamesystem;

import models.entities.Entity;

import java.util.List;

//动画控制类，负责动画的播放（PNG切换）与动画的切换例如 行走->死亡
public class AnimeControl {
    private int deadTime;
    int curIndex, maxIndex;
    //动画的播放
    public void animePlayer(Entity entity) {
        //图片索引（从1开始，代表是动画中的第几张图片） 当前图片索引 最大图片索引

        List<String> imgList;
        String curAnimeName = entity.getCurAnimeName();
        imgList = entity.getMotionImgList(curAnimeName);
        //获得当前图片是第几张以及动画列表一共有多少张
        curIndex = entity.getImgID();
        maxIndex = imgList.size();
        //增加索引,得到下一张图片的索引
        curIndex++;
        //具体的动画播放
        //若当前索引比最大索引还要大说明动画已经播放完一次，对于行走这样的动作需要不断循环因此将索引再置回第一张
        String cName = entity.getCurAnimeName();

        if (curIndex > maxIndex && (cName.equals("move") || cName.equals("dance") || cName.equals("attack") || cName.equals("fly") || cName.equals("break"))) {
            curIndex = 1;
        }

        if (curIndex <= maxIndex) {
            //只有能切换图片的时候才切换图片 eg：如果对象死亡，curIndex会一直增加，始终大于最大索引，这时图片会维持不变
            entity.setImgID(curIndex);
            //get的起始为0
            entity.setCurImgSrc(imgList.get(curIndex - 1));
        } else {
            entity.setImgID(maxIndex);
            //get的起始为0
            if (maxIndex != 1 && maxIndex != 0)
                entity.setCurImgSrc(imgList.get(maxIndex - 2));
        }
    }

    //切换当前要播放的动画  传入实体以及动画名
    public void animeChange(Entity entity, String animeName) {
        //修正坐标
            entity.setPositionX(entity.getPositionX() + entity.getPositionCorrect());
            //设置索引到第一张图
            entity.setImgID(1);
            entity.setCurAnimeName(animeName);
            //新的动作的图片链接
            List<String> newSrcList = entity.getMotion().getMotionSrcList(animeName);
            entity.setMotionImgList(newSrcList);
            entity.setCurImgSrc(newSrcList.get(0));
    }
}
