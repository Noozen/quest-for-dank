package com.dankquest.game.com.dankquest.game.actors;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.logic.Hero;
import com.dankquest.game.com.dankquest.game.logic.buffs.Buff;

import java.util.Map;

/**
 * Created by Miko on 2015-09-05.
 */
public class PortraitActor extends Actor {

    private Hero hero;
    private Texture texture;
    private Pixmap pixmap = new Pixmap(96, 96, Pixmap.Format.RGBA8888);
    BitmapFont bitmapFont;
    protected boolean chosen;
    private int x = 0, y = 0;

    public PortraitActor() {
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(1, 0, 0, 1);
        this.chosen = false;
        setBounds(x, y, 96, 96);
        addCustomListener();
        update();
    }
    public PortraitActor(Hero hero) {
        this.hero = hero;
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(1, 0, 0, 1);
        this.chosen = false;
        setBounds(x, y, 96, 96);
        addCustomListener();
        update();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, this.x, this.y);
        if(hero != null) {
            bitmapFont.draw(batch, hero.name, this.x, this.y + 10);
            int i = 0;
            for (Map.Entry<String, Buff> buffEntry : hero.buffMap.entrySet()) {
                bitmapFont.draw(batch, String.valueOf(buffEntry.getValue().buffDuration), this.x + 2 + i * 16, this.y + 92);
                i++;
            }
        }
    }

    public void update() {
        setBounds(x, y, 96, 96);
        pixmap.setColor(0, 0, 0, 0);
        pixmap.fill();
        pixmap.setColor(0.5f, 0.5f, 0.5f, 1);
        pixmap.fillRectangle(0, 0, 96, 96);
        if(hero != null) {
            pixmap.drawPixmap(hero.getImage(), 0, 0);
            int i = 0;
            for (Map.Entry<String, Buff> buffEntry : hero.buffMap.entrySet()) {
                pixmap.drawPixmap(buffEntry.getValue().getImage(), 0 + 16 * i, 0);
                i++;
            }
        }
        texture = new Texture(pixmap);
    }

    public boolean isChosen() {
        return this.chosen;
    }
    public void setChosen(boolean chosen){
        this.chosen = chosen;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public Hero getHero(){
        return this.hero;
    }
    public void setHero(Hero hero){
        this.hero = hero;
    }

    public void addCustomListener() {
        addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
    }
}
