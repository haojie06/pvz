package models.gamesystem;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//音乐播放控制类
public class SoundControl {
    Player player;
    File music;
    private static Thread Bgthread = new Thread();
    //背景音乐是否正在播放
    static int backGroundInPlaying = 0;
    //记录当前播放的对象有哪些
    static List<Integer> hashList = new ArrayList<>();
    //构造方法  参数是一个.mp3音频文件
    public SoundControl() {
    }


    //播放方法
    public void play() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
        player = new Player(buffer);
        player.play();
    }

    //该方法应该要支持中断当前音乐的播放以及切换 （终止线程）
    public static void playBackgoundMusic(String backgroundSoundName) {
        File backGroundMusic = new File("src//resources/sound/" + backgroundSoundName);

        //如果背景音乐正在播放
        if (backGroundInPlaying == 1) {
            //终止当前的播放
            backGroundInPlaying = 0;
            Bgthread.stop();
        }


        Bgthread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    try {
                        //正在播放
                        backGroundInPlaying = 1;
                        //重复播放背景音乐 ！！切换的问题还没解决，可能线程的创建方式需要修改
                        while (true) {
                            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(backGroundMusic));
                            Player player = new Player(buffer);
                            player.play();
                            try {
                                buffer.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JavaLayerException ex) {
                        ex.printStackTrace();
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };

        Bgthread.start();
        //Bgthread.stop();

    }

    public static void playSound(String musicName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File backGroundMusic = new File("src//resources/sound/" + musicName);
                try {
                    BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(backGroundMusic));
                    try {
                        Player player = new Player(buffer);
                        player.play();
                    } catch (JavaLayerException ex) {
                        ex.printStackTrace();
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

    }


    public static void hashPlayer(String musicName, Integer hashCode) {
        String hash = String.valueOf(hashCode);
        if (!hashList.contains(hashCode)) {
            hashList.add(hashCode);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File backGroundMusic = new File("src//resources/sound/" + musicName);
                    try {
                        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(backGroundMusic));
                        try {
                            Player player = new Player(buffer);
                            player.play();
                            hashList.remove(hashCode);
                        } catch (JavaLayerException ex) {
                            ex.printStackTrace();
                        }
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }
    }


}