package com.dankquest.game.com.dankquest.game.logic.skill;

import com.dankquest.game.com.dankquest.game.logic.Hero;

/**
 * Created by Miko on 2015-09-04.
 */
public class SlamSkill implements Skill {
    public void cast(Hero... heroTable) {
        heroTable[1].healthCurrent -= heroTable[0].healthCurrent* 1/5;
    };
}
