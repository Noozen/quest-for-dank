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

    private Pixmap placeholderPixmap = new Pixmap(Gdx.files.internal("adventure_menu/placeholder.png"));
    private Pixmap warriorPixmap = new Pixmap(Gdx.files.internal("adventure_menu/warrior.png"));
    private Pixmap magePixmap = new Pixmap(Gdx.files.internal("adventure_menu/mage.png"));

    BitmapFont bitmapFont;

    public ChosenHeroesActor() {
        chosenHeroesPixmap = new Pixmap(400, 100, Pixmap.Format.RGBA8888);
        singleHeroPixmap = new Pixmap(100,100, Pixmap.Format.RGBA8888);
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(1, 0, 0, 1);
        setBounds(120, 10, 400, 100);
        update();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, 120, 10);
        //writes hero names
        for(int i=0;i<4;i++) {
            if(i>=Dank.chosenHeroesList.size()) {
                continue;
            }
            bitmapFont.draw(batch, Dank.chosenHeroesList.get(i).name,120+100*(i), 110);
        }
    }

    public void update() {
        //updates hero portraits
        for(int i=0;i<4;i++) {
            if(i>=Dank.chosenHeroesList.size()) {
                singleHeroPixmap.drawPixmap(placeholderPixmap, 0, 0);
                chosenHeroesPixmap.drawPixmap(singleHeroPixmap, i * 100, 0);
                continue;
            }
            if(Dank.chosenHeroesList.get(i).heroClass == HeroClass.WARRIOR) {
                singleHeroPixmap.drawPixmap(warriorPixmap, 0, 25, 100, 125, 0, 0, 100, 100);
            }
            if(Dank.chosenHeroesList.get(i).heroClass == HeroClass.MAGE) {
                singleHeroPixmap.drawPixmap(magePixmap, 0, 0);
            }
            chosenHeroesPixmap.drawPixmap(singleHeroPixmap, i * 100, 0);
        }
        texture = new Texture(chosenHeroesPixmap);
    }
}
