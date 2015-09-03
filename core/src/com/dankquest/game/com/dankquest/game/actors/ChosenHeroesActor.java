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
    Pixmap pixmap;
    Pixmap pixmapHelper;
    Texture texture;
    BitmapFont bitmapFont = new BitmapFont();

    Pixmap placeholderPixmap = new Pixmap(Gdx.files.internal("adventure_menu/placeholder.png"));
    Pixmap warriorPixmap = new Pixmap(Gdx.files.internal("adventure_menu/warrior.png"));
    Pixmap magePixmap = new Pixmap(Gdx.files.internal("adventure_menu/mage.png"));

    public ChosenHeroesActor() {
        pixmap = new Pixmap(400, 100, Pixmap.Format.RGBA8888);
        pixmapHelper = new Pixmap(100,100, Pixmap.Format.RGBA8888);
        bitmapFont.setColor(1, 0, 0, 1);
        setBounds(120, 10, 400, 100);
        updateHeroList();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, 120, 10);
        for(int i=0;i<4;i++) {
            if(i>=Dank.chosenHeroesList.size()) {
                continue;
            }
            bitmapFont.draw(batch, Dank.chosenHeroesList.get(i).name,120+100*(i), 110);
        }
    }

    public void updateHeroList() {
        for(int i=0;i<4;i++) {
            if(i>=Dank.chosenHeroesList.size()) {
                pixmapHelper.drawPixmap(placeholderPixmap, 0, 0);
                pixmap.drawPixmap(pixmapHelper,i*100,0);
                continue;
            }
            if(Dank.chosenHeroesList.get(i).heroClass == HeroClass.WARRIOR) {
                pixmapHelper.drawPixmap(warriorPixmap,0, 25, 100, 125, 0 ,0 ,100, 100);
            }
            if(Dank.chosenHeroesList.get(i).heroClass == HeroClass.MAGE) {
                pixmapHelper.drawPixmap(magePixmap, 0, 0);
            }
            pixmap.drawPixmap(pixmapHelper,i*100,0);
        }
        texture = new Texture(pixmap);
    }
}
