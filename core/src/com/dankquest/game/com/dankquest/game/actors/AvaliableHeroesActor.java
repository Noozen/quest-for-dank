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
public class AvaliableHeroesActor extends Actor {
    Pixmap pixmap;
    Pixmap pixmapHelper;
    Texture texture;
    public int currentHero = 0;
    BitmapFont bitmapFont = new BitmapFont();

    Pixmap placeholderPixmap = new Pixmap(Gdx.files.internal("adventure_menu/placeholder.png"));
    Pixmap warriorPixmap = new Pixmap(Gdx.files.internal("adventure_menu/warrior.png"));
    Pixmap magePixmap = new Pixmap(Gdx.files.internal("adventure_menu/mage.png"));

    public AvaliableHeroesActor () {
        pixmap = new Pixmap(400, 350, Pixmap.Format.RGBA8888);
        pixmapHelper = new Pixmap(100,350, Pixmap.Format.RGBA8888);
        bitmapFont.setColor(1, 0, 0, 1);
        setBounds(120, 120, 400, 350);
        updateTexture();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, 120, 120);
        for(int i=currentHero;i<(currentHero+4);i++) {
            if(i>=Dank.ownedHeroesList.size()) {
                continue;
            }
            bitmapFont.draw(batch, Dank.ownedHeroesList.get(i).name,120+100*(i-currentHero), 470);
        }
    }

    public void heroIncreased() {
        if(currentHero<Dank.ownedHeroesList.size()-4) {
            currentHero++;
        }
        updateTexture();
    }

    public void heroDecreased() {
        if(currentHero>0){
            currentHero--;
        }
        updateTexture();
    }

    public void updateHero() {
        updateTexture();
    }

    private void updateTexture() {
        for(int i=currentHero;i<(currentHero+4);i++) {
            if(i>=Dank.ownedHeroesList.size()) {
                pixmapHelper.drawPixmap(placeholderPixmap, 0, 0);
                pixmap.drawPixmap(pixmapHelper,(i-currentHero)*100,0);
                continue;
            }
            if(Dank.ownedHeroesList.get(i).heroClass == HeroClass.WARRIOR) {
                pixmapHelper.drawPixmap(warriorPixmap, 0, 0);
            }
            if(Dank.ownedHeroesList.get(i).heroClass == HeroClass.MAGE) {
                pixmapHelper.drawPixmap(magePixmap, 0, 0);
            }
            pixmap.drawPixmap(pixmapHelper,(i-currentHero)*100,0);
        }
        texture = new Texture(pixmap);
    }
}
