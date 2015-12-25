package com.dankquest.game.com.dankquest.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.logic.HeroClass;

/**
 * Created by Miko on 2015-09-03.
 */
public class ChosenHeroesActor extends Actor {

    private Texture texture;

    private Pixmap chosenHeroesPixmap;
    private Pixmap singleHeroPixmap;

    BitmapFont bitmapFont;

    public ChosenHeroesActor() {
        chosenHeroesPixmap = new Pixmap(434, 116, Pixmap.Format.RGBA8888);
        singleHeroPixmap = new Pixmap(96, 96, Pixmap.Format.RGBA8888);
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(1, 0, 0, 1);
        setBounds(103, 10, 434, 116);
        update();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, 103, 10);
        //writes hero names
        for(int i=0;i<4;i++) {
            if(i>=Dank.chosenHeroesList.size()) {
                continue;
            }
            bitmapFont.draw(batch, Dank.chosenHeroesList.get(i).name,103 + 10 + 106*(i), 106);
        }
    }

    public void update() {
        //updates hero portraits
        for(int i=0;i<4;i++) {
            singleHeroPixmap.setColor(0.5f, 0.5f, 0.5f, 1f);
            singleHeroPixmap.fillRectangle(0, 0, 96, 96);
            chosenHeroesPixmap.drawPixmap(singleHeroPixmap, 10 + i * 106, 10);
            if(i>=Dank.chosenHeroesList.size()) {
                continue;
            }
            singleHeroPixmap.drawPixmap(Dank.chosenHeroesList.get(i).getImage(), 0, 0);
            chosenHeroesPixmap.drawPixmap(singleHeroPixmap, 10 + i * 106, 10);
        }
        texture = new Texture(chosenHeroesPixmap);
    }
}
