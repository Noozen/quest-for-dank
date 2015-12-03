package com.dankquest.game.com.dankquest.game.util;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Antah on 2015/10/27.
 */
public class DankMusic {
    private static Music soundtrack;
    public static String soundtrackName;
    public static void changeSoundtrack(String filename){
        if(soundtrackName != null && soundtrackName.equals(filename))
            return;
        soundtrackName = filename;
        Assets.load(filename, Music.class);
        while(!Assets.manager.update()){
            System.out.println(Assets.manager.getProgress() * 100 + "%");
        }
        if(soundtrack != null)
            soundtrack.dispose();
        soundtrack = Assets.manager.get(filename, Music.class);
        soundtrack.setVolume(0.2f);
        soundtrack.setLooping(true);
        soundtrack.play();
    }
    public static void playSound(String filename){
        Assets.load(filename, Music.class);
        Assets.manager.finishLoading();
        Music s = Assets.manager.get(filename, Music.class);
        s.setVolume(0.2f);
        s.play();
    }
    public static void stopSoundtrack(){
        soundtrack.stop();
    }
    public static void resumeSoundtrack(){
        if(!soundtrack.isPlaying())
            soundtrack.play();
    }

    public static void setSoundtrackVolume(float soundtrackVolume) {
        soundtrack.setVolume(soundtrackVolume);
    }
}
