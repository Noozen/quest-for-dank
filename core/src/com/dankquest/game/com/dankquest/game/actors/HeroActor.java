package com.dankquest.game.com.dankquest.game.actors;

/**
 * Created by Antah on 2015/09/05.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dankquest.game.com.dankquest.game.logic.Dank;

public class HeroActor extends Actor {

    private Texture texture;

    BitmapFont bitmapFont;

    private int x, y, hero_number;

    public HeroActor(int x, int y, int hero_number) {
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
        texture = Dank.chosenHeroesList.get(hero_number - 1).getImage();
    }
}