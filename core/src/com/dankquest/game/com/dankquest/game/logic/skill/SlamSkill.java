package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.dankquest.game.com.dankquest.game.logic.Hero;

import java.util.List;

/**
 * Created by Miko on 2015-09-04.
 */
public class SlamSkill extends Skill {

    public SlamSkill () {
        amountOfTargets = 1;
        basePixmap = new Pixmap(Gdx.files.internal("skills/slam.png"));
        skillName = "Arrow";
    }
    public void cast(List<Hero> heroList) {
        heroList.get(1).healthCurrent -= heroList.get(0).healthCurrent * 0.2;
    };

}
