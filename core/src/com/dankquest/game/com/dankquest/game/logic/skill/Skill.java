package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.graphics.Texture;
import com.dankquest.game.com.dankquest.game.logic.Hero;

import java.util.List;

/**
 * Created by Miko on 2015-09-04.
 */
public interface Skill {
    public void cast(List<Hero> heroList);
    public Texture getImage();
}
