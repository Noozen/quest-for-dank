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
        ownedHeroesPixmap = new Pixmap(328, 222, Pixmap.Format.RGBA8888);
        singleHeroPixmap = new Pixmap(96, 96, Pixmap.Format.RGBA8888);
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(1, 0, 0, 1);
        setBounds(156, 248, 328, 222);
        update();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, 156, 248);
        //writes hero names
        for(int i=currentHero;i<(currentHero+6);i++) {
            if(i>=Dank.ownedHeroesList.size()) {
                continue;
            }
            bitmapFont.draw(batch, Dank.ownedHeroesList.get(i).name,10 + (i - currentHero)%3 * (-106) + 412, 10 + (i - currentHero)%2 * (-106) + 306);
        }
    }

    public void heroIncreased() {
        if(currentHero<Dank.ownedHeroesList.size()-6) {
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
        for(int i=currentHero;i<(currentHero+6);i++) {


            singleHeroPixmap.setColor(0.5f, 0.5f, 0.5f, 1f);
            singleHeroPixmap.fillRectangle(0, 0, 96, 96);
            ownedHeroesPixmap.drawPixmap(singleHeroPixmap, 10 + (i - currentHero)%3 * (-106) + 212, 10 + (i - currentHero)%2 * (-106) + 106);
            if(i>=Dank.ownedHeroesList.size()) {
                continue;
            }
            singleHeroPixmap.drawPixmap(Dank.ownedHeroesList.get(i).getImage(), 0, 0);
            ownedHeroesPixmap.drawPixmap(singleHeroPixmap, 10 + (i - currentHero)%3 * (-106) + 212, 10 + (i - currentHero)%2 * (-106) + 106);
        }
        texture = new Texture(ownedHeroesPixmap);
    }
}
