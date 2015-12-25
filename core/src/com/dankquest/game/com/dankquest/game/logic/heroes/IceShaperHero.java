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
        this.skill4 = new ZeroBladeSkill();
        this.buffMap = new HashMap<String, Buff>();

        Assets.load("portraits/IceShaperPortrait.png", Pixmap.class);
        Assets.load("heroes/IceShaperSheet.png", Texture.class);
        this.portraitTexture = Assets.manager.get("portraits/IceShaperPortrait.png", Pixmap.class);
        this.characterTexture = Assets.manager.get("heroes/IceShaperSheet.png", Texture.class);
    }
}

