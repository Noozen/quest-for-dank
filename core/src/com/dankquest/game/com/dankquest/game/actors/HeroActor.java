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
import com.dankquest.game.com.dankquest.game.logic.Hero;

import java.util.List;

public class HeroActor extends Actor {

    private Texture texture;

    BitmapFont bitmapFont;

    private int x, y, hero_number;

    private Pixmap pixmap;

    List<Hero> characterList;


    public HeroActor(int x, int y, int hero_number, boolean playerControlled) {
        if(playerControlled) {
            characterList = Dank.chosenHeroesList;
        } else {
            characterList = Dank.enemyHeroesList;
        }
        pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
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
        bitmapFont.draw(batch, characterList.get(hero_number - 1).name, x, y+74);
    }

    public void update() {
        pixmap.drawPixmap(characterList.get(hero_number - 1).getImage(), 0, 0);
        pixmap.setColor(1, 0.5f, 0.5f, 1);
        pixmap.fillRectangle(0, 0, 64, 10);
        pixmap.setColor(1, 0, 0, 1);
        pixmap.fillRectangle(0, 0, (int) (64 * (characterList.get(hero_number - 1).healthCurrent / characterList.get(hero_number - 1).healthTotal)), 10);
        texture = new Texture(pixmap);
    }
}