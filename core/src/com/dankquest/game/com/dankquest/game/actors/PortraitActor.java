package com.dankquest.game.com.dankquest.game.actors;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.logic.buffs.Buff;

import java.util.Map;

/**
 * Created by Miko on 2015-09-05.
 */
public class PortraitActor extends Actor {

    private Texture texture;

    private Pixmap pixmap = new Pixmap(100,100, Pixmap.Format.RGBA8888);

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
        int i = 0;
        for(Map.Entry<String, Buff> buffEntry : Dank.activeHero.buffMap.entrySet()) {
            bitmapFont.draw(batch, String.valueOf(buffEntry.getValue().buffDuration), 12 + i*16, 92);
            i++;
        }
    }

    public void update() {
        pixmap.setColor(0, 0, 0, 0);
        pixmap.fill();
        pixmap.setColor(0.5f, 0.5f, 0.5f, 1);
        pixmap.fillCircle(50, 50, 50);
        pixmap.drawPixmap(Dank.activeHero.getImage(), 0, 0, 64, 64, 0, 0, 100, 100);
        int i = 0;
        for(Map.Entry<String, Buff> buffEntry : Dank.activeHero.buffMap.entrySet()) {
            pixmap.drawPixmap(buffEntry.getValue().getImage(), 0 + 16*i, 0);
            i++;
        }
        texture = new Texture(pixmap);
    }
}
