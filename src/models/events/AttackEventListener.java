package models.events;

import models.gamesystem.AnimeControl;
import models.entities.Entity;
import models.entities.weapon.Weapon;

import static models.gamesystem.SoundControl.hashPlayer;

//攻击事件的监听/处理器，设计上有一些问题，这个监听器实际上是“僵尸”攻击的监听器，因为只有僵尸能发出AttackEvent
public class AttackEventListener extends EventListener {
    //僵尸吃东西的音效播放完之后才能新开线程重新播放 这个方法比较特殊，需要传入对象的hash，若正在播放列表中没有这个对象的音乐才播放，播放完之后移除
    @Override
    public void eventHandle(Event event) {
        if (event.getEventName().equals("攻击事件")) {
            AttackEvent attackEvent = (AttackEvent) event;
            if (!attackEvent.getAttackSource().getName().equals("JackinTheBoxZombie")) {
                hashPlayer("zombieEat.mp3", attackEvent.getAttackSource().hashCode());
                //获得武器
                Weapon weapon = attackEvent.getWeapon();
                //获得攻击发起者
                Entity attacker = attackEvent.getAttackSource();
                attacker.stopMove();
                //攻击对象在武器的attack方法内决定
                //攻击者要播放攻击的动画
                AnimeControl animeControl = new AnimeControl();
                //将动画切换为攻击
                if (!attacker.getCurAnimeName().equals(attacker.getAttackMotionName()) && (!attacker.getCurAnimeName().equals("jump"))) {
                    animeControl.animeChange(attacker, attacker.getAttackMotionName());
                }
                //停止攻击源的移动
                attacker.stopMove();
                //进行攻击
                weapon.attack(((AttackEvent) event).getEntities());
            }else {
                Entity attacker = attackEvent.getAttackSource();
                AnimeControl animeControl = new AnimeControl();
                //将动画切换为攻击
                if (!attacker.getCurAnimeName().equals(attacker.getAttackMotionName())) {
                    animeControl.animeChange(attacker, attacker.getAttackMotionName());
                }
                //停止攻击源的移动
                attacker.stopMove();
                //进行攻击
            }
        }
    }
}
