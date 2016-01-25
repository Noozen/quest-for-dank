package com.dankquest.game.com.dankquest.game.logic.heroes;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.dankquest.game.com.dankquest.game.logic.HeroClass;
import com.dankquest.game.com.dankquest.game.logic.buffs.Buff;
import com.dankquest.game.com.dankquest.game.logic.skill.*;
import com.dankquest.game.com.dankquest.game.util.Assets;

import java.util.HashMap;

/**
 * Created by Antah on 2015/10/30.
 */
public class BigGoblinHero extends Hero {
    public BigGoblinHero(String name){
        super(name);

        this.healthTotal = 200;
        this.healthCurrent = 200;
        this.initiative = 8;
        this.attackDamage = 20;
        this.damageMultiplier = 1;
        this.baseShield = 10;
        this.shield = this.baseShield;

        this.heroClass = HeroClass.WARRIOR;
        this.skill1 = new FireballSkill();
        this.skill2 = new IcelanceSkill();
        this.skill3 = new IceWallSkill();
        this.skill4 = new TwinBladeSkill();
        this.buffMap = new HashMap<String, Buff>();

        Assets.load("portraits/GoblinPortrait.png", Pixmap.class);
        this.portraitTexture = Assets.manager.get("portraits/GoblinPortrait.png", Pixmap.class);
        Assets.load("heroes/goblin.png", Texture.class);
        this.characterTexture = Assets.manager.get("heroes/goblin.png", Texture.class);
    }
}
