package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.dankquest.game.com.dankquest.game.logic.Hero;

import java.util.List;

/**
 * Created by Miko on 2015-09-04.
 */
public abstract class Skill {

    protected int amountOfTargets;
    protected Pixmap basePixmap;
    protected String skillName;

    abstract public void cast(List<Hero> heroList);
    public Pixmap getImage() {
        return basePixmap;
    }
    public int getAmountOfTargets() {
        return amountOfTargets;
    }
    public String toString() {
        return skillName;
    };
}
