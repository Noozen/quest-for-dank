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
public class OwnedHeroesActor extends Actor {

    private Texture texture;

    public int currentHero = 0;
    private Pixmap ownedHeroesPixmap;
    private Pixmap singleHeroPixmap;


    private Pixmap placeholderPixmap = new Pixmap(Gdx.files.internal("adventure_menu/placeholder.png"));
    private Pixmap warriorPixmap = new Pixmap(Gdx.files.internal("adventure_menu/warrior.png"));
    private Pixmap magePixmap = new Pixmap(Gdx.files.internal("adventure_menu/mage.png"));

    private BitmapFont bitmapFont;

    public OwnedHeroesActor() {
        ownedHeroesPixmap = new Pixmap(400, 350, Pixmap.Format.RGBA8888);
        singleHeroPixmap = new Pixmap(100,350, Pixmap.Format.RGBA8888);
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(1, 0, 0, 1);
        setBounds(120, 120, 400, 350);
        update();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, 120, 120);
        //writes hero names
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
        update();
    }

    public void heroDecreased() {
        if(currentHero>0){
            currentHero--;
        }
        update();
    }

    public void update() {
        //updates hero portraits
        for(int i=currentHero;i<(currentHero+4);i++) {
            if(i>=Dank.ownedHeroesList.size()) {
                singleHeroPixmap.drawPixmap(placeholderPixmap, 0, 0);
                ownedHeroesPixmap.drawPixmap(singleHeroPixmap, (i - currentHero) * 100, 0);
                continue;
            }
            if(Dank.ownedHeroesList.get(i).heroClass == HeroClass.WARRIOR) {
                singleHeroPixmap.drawPixmap(warriorPixmap, 0, 0);
            }
            if(Dank.ownedHeroesList.get(i).heroClass == HeroClass.MAGE) {
                singleHeroPixmap.drawPixmap(magePixmap, 0, 0);
            }
            ownedHeroesPixmap.drawPixmap(singleHeroPixmap, (i - currentHero) * 100, 0);
        }
        texture = new Texture(ownedHeroesPixmap);
    }
}
