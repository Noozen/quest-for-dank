package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.graphics.Texture;
import com.dankquest.game.com.dankquest.game.logic.Hero;

/**
 * Created by Miko on 2015-09-04.
 */
public interface Skill {
    public void cast(Hero... heroTable);
    public Texture getImage();
}