package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.dankquest.game.com.dankquest.game.logic.Hero;

import java.util.List;

/**
 * Created by Miko on 2015-09-04.
 */
public class SlamSkill implements Skill {

    private int amountOfTargets = 1;

    Pixmap basePixmap = new Pixmap(Gdx.files.internal("skills/slam.png"));

    public void cast(List<Hero> heroList) {
        heroList.get(1).healthCurrent -= heroList.get(0).healthCurrent * 0.2;
    };

    public Pixmap getImage() {
        return basePixmap;
    }

    public int getAmountOfTargets() {
        return amountOfTargets;
    }

    public String toString(){ return "Slam"; }
}
