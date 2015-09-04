package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dankquest.game.com.dankquest.game.logic.Hero;

/**
 * Created by Miko on 2015-09-04.
 */
public class SlamSkill implements Skill {

    Texture baseTexture = new Texture(Gdx.files.internal("skills/slam.png"));

    public void cast(Hero... heroTable) {
        heroTable[1].healthCurrent -= heroTable[0].healthCurrent* 1/5;
    };

    public Texture getImage() {
        return baseTexture;
    }

    public String toString(){ return "Slam"; }
}
