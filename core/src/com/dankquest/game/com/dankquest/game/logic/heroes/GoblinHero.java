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
public class GoblinHero extends Hero {
    public GoblinHero(String name){
        super(name);

        this.healthTotal = 100;
        this.healthCurrent = 100;
        this.initiative = 8;
        this.attackDamage = 10;
        this.damageMultiplier = 1;
        this.baseShield = 10;
        this.shield = this.baseShield;

        this.heroClass = HeroClass.WARRIOR;
        this.skill1 = new FireballSkill();
        this.skill2 = new IcelanceSkill();
        this.skill3 = new IceWallSkill();
        this.skill4 = new TwinBladeSkill();
        this.buffMap = new HashMap<String, Buff>();

        Assets.load("portraits/IceShaperPortrait.png", Pixmap.class);
        Assets.load("heroes/IceShaperSheet.png", Texture.class);
        this.portraitTexture = Assets.manager.get("portraits/IceShaperPortrait.png", Pixmap.class);
        this.characterTexture = Assets.manager.get("heroes/IceShaperSheet.png", Texture.class);
    }
}
