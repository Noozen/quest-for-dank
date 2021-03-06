package com.dankquest.game.com.dankquest.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class Assets implements Disposable, AssetErrorListener {
    public static final AssetManager manager = new AssetManager();

    public static void load(String filePath, Class fileClass){
        manager.load(String.valueOf(Gdx.files.internal(filePath)), fileClass);
        loadingScreen();
    }

    public static void loadMainMenu(){
        load("backgrounds/Tower1.png", Texture.class);
        load("skins/rainbowpack.json", Skin.class);
        loadingScreen();
    }

    public static void loadPortraits() {
        load("adventure_menu/placeholder.png", Pixmap.class);
        load("adventure_menu/warrior.png", Pixmap.class);
        load("adventure_menu/mage.png", Pixmap.class);
        loadingScreen();
    }

    private static void loadingScreen() {
        while(!manager.update()){
            //System.out.println(manager.getProgress() * 100 + "%");
        }
    }
    @Override
    public void dispose(){
        manager.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {

    }
}
