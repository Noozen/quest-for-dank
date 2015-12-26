package com.dankquest.game.com.dankquest.game.actors;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.screens.AdventureMenu;

/**
 * Created by Antah on 2015/12/26.
 */
public class ChosenHeroPortraitActor extends PortraitActor {

    private AdventureMenu adventureMenuInstance;

    public ChosenHeroPortraitActor(AdventureMenu adventureMenu) {
        super();
        this.adventureMenuInstance = adventureMenu;
    }

    public void addCustomListener() {
        addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
    }
}
