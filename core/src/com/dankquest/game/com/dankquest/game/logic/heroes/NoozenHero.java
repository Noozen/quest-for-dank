package com.dankquest.game.com.dankquest.game.logic.heroes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dankquest.game.com.dankquest.game.logic.Hero;
import com.dankquest.game.com.dankquest.game.logic.HeroClass;
import com.dankquest.game.com.dankquest.game.logic.buffs.Buff;
import com.dankquest.game.com.dankquest.game.logic.skill.*;
import com.dankquest.game.com.dankquest.game.util.Assets;

import java.util.HashMap;

/**
 * Created by Antah on 2015/10/30.
 */
public class NoozenHero extends Hero {
    public NoozenHero(String name){
        super(name);

        this.healthTotal = 400;
        this.healthCurrent = 400;
        this.initiative = 10;
        this.attackDamage = 35;
        this.damageMultiplier = 1;
        this.baseShield = 0;
        this.shield = this.baseShield;

        this.heroClass = HeroClass.WARRIOR;
        this.skill1 = new SlamSkill();
        this.skill2 = new IcelanceSkill();
        this.skill3 = new IceWallSkill();
        this.skill4 = new TwinBladeSkill();
        this.buffMap = new HashMap<String, Buff>();

        Assets.load("portraits/WarriorPortrait.png", Pixmap.class);
        this.portraitTexture = Assets.manager.get("portraits/WarriorPortrait.png", Pixmap.class);
        Assets.load("heroes/IceShaperSheet.png", Texture.class);
        this.characterTexture = Assets.manager.get("heroes/IceShaperSheet.png", Texture.class);
    }
}
