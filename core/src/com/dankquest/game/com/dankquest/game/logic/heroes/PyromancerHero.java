package com.dankquest.game.com.dankquest.game.logic.heroes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.dankquest.game.com.dankquest.game.logic.Hero;
import com.dankquest.game.com.dankquest.game.logic.HeroClass;
import com.dankquest.game.com.dankquest.game.logic.buffs.Buff;
import com.dankquest.game.com.dankquest.game.logic.skill.*;

import java.util.HashMap;

/**
 * Created by Antah on 2015/10/30.
 */
public class PyromancerHero extends Hero {
    public PyromancerHero(String name) {
        super(name);

        this.healthTotal = 200;
        this.healthCurrent = 200;
        this.initiative = 17;
        this.attackDamage = 70;
        this.damageMultiplier = 1;
        this.baseShield = 0;
        this.shield = this.baseShield;

        this.heroClass = HeroClass.MAGE;
        this.skill1 = new FireballSkill();
        this.skill2 = new IcelanceSkill();
        this.skill3 = new MeteorSkill();
        this.skill4 = new TwinBladeSkill();
        this.texture = new Pixmap(Gdx.files.internal("heroes/dwarf.png"));
        this.buffMap = new HashMap<String, Buff>();
    }
}