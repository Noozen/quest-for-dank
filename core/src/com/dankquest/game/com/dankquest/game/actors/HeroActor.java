package com.dankquest.game.com.dankquest.game.actors;

/**
 * Created by Antah on 2015/09/05.
 */

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dankquest.game.com.dankquest.game.logic.Dank;

public class HeroActor extends Actor {

    private Texture texture;

    BitmapFont bitmapFont;

    private int x, y, hero_number;

    private Pixmap hpPixmap;


    public HeroActor(int x, int y, int hero_number) {
        hpPixmap  = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(1, 0, 0, 1);
        this.x = x;
        this.y = y;
        this.hero_number = hero_number;
        setBounds(x, y, 64, 64);
        update();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, x, y);
    }

    public void update() {
        hpPixmap.drawPixmap(Dank.chosenHeroesList.get(hero_number - 1).getImage(), 0, 0);
        hpPixmap.setColor(1,0.5f,0.5f, 1);
        hpPixmap.fillRectangle(0, 0, 64, 10);
        hpPixmap.setColor(1,0,0,1);
        hpPixmap.fillRectangle(0, 0, (int) (64 * (Dank.chosenHeroesList.get(hero_number - 1).healthCurrent / Dank.chosenHeroesList.get(hero_number - 1).heathTotal)), 10);
        texture = new Texture(hpPixmap);
    }
}