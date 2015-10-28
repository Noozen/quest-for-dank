package com.dankquest.game.com.dankquest.game.util;

import com.badlogic.gdx.audio.Music;

/**
 * Created by Antah on 2015/10/27.
 */
public class DankMusic {
    private static Music music;
    public static String musicName;
    public static void playMusic(String filename){
        if(musicName != null && musicName.equals(filename))
            return;
        musicName = filename;
        Assets.load(filename, Music.class);
        while(!Assets.manager.update()){
            System.out.println(Assets.manager.getProgress() * 100 + "%");
        }
        if(music != null)
            music.dispose();
        music = Assets.manager.get(filename, Music.class);
        music.setVolume(0.2f);
        music.setLooping(true);
        music.play();
    }
}
