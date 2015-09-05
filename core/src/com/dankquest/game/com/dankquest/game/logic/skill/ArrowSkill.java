package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.dankquest.game.com.dankquest.game.logic.Hero;

import java.util.List;

/**
 * Created by Miko on 2015-09-04.
 */
public class ArrowSkill implements Skill {

    Pixmap basePixmap = new Pixmap(Gdx.files.internal("skills/arrow.png"));

    public void cast(List<Hero> heroList) {
        heroList.get(1).healthCurrent -= heroList.get(0).attackDamage;
    };

    public Pixmap getImage() {
        return basePixmap;
    }

    public String toString(){ return "Arrow"; }
}
