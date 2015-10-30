package com.dankquest.game.com.dankquest.game.logic.heroes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.dankquest.game.com.dankquest.game.logic.Hero;
import com.dankquest.game.com.dankquest.game.logic.HeroClass;
import com.dankquest.game.com.dankquest.game.logic.buffs.Buff;
import com.dankquest.game.com.dankquest.game.logic.skill.FreezingMistSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.IceWallSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.IcelanceSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.TwinBladeSkill;

import java.util.HashMap;

/**
 * Created by Antah on 2015/10/30.
 */
public class IceShaperHero extends Hero {
    public IceShaperHero(String name) {
        super(name);

        this.healthTotal = 150;
        this.healthCurrent = 150;
        this.initiative = 15;
        this.attackDamage = 50;
        this.damageMultiplier = 1;
        this.baseShield = 100;
        this.shield = this.baseShield;

        this.heroClass = HeroClass.MAGE;
        this.skill1 = new FreezingMistSkill();
        this.skill2 = new IcelanceSkill();
        this.skill3 = new IceWallSkill();
        this.skill4 = new TwinBladeSkill();
        this.texture = new Pixmap(Gdx.files.internal("heroes/dwarf.png"));
        this.buffMap = new HashMap<String, Buff>();
    }
}
