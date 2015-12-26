package com.dankquest.game.com.dankquest.game.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.logic.Hero;
import com.dankquest.game.com.dankquest.game.screens.AdventureMenu;

/**
 * Created by Antah on 2015/12/26.
 */
public class OwnedHeroPortraitActor extends PortraitActor{

    private AdventureMenu adventureMenuInstance;

    public OwnedHeroPortraitActor(Hero h, AdventureMenu adventureMenu){
        super(h);
        this.adventureMenuInstance = adventureMenu;
    }

    public void addCustomListener() {
        final PortraitActor tmp = this;
        addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(chosen == false && Dank.chosenHeroesList.size() < 4) {
                    chosen = true;
                    Dank.chosenHeroesList.add(getHero());
                    adventureMenuInstance.updateChosenHeroesActorList(tmp);
                    System.out.print("dodaje  " + chosen);
                }
                System.out.print("dodanyyy  ");
            }
        });
    }
}
