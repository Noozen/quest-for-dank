package com.dankquest.game.com.dankquest.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dankquest.game.com.dankquest.game.logic.Dank;

/**
 * Created by Miko on 2015-09-05.
 */
public class PortraitActor extends Actor {

    private Texture texture;

    BitmapFont bitmapFont;

    public PortraitActor() {
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(1, 0, 0, 1);
        update();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, 10, 10, 100, 100);
        bitmapFont.draw(batch, Dank.activeHero.name, 50, 50);
    }

    public void update() {
        texture = new Texture(Dank.activeHero.getImage());
    }
}
