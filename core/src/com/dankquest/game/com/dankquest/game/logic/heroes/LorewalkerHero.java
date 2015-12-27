package com.dankquest.game.com.dankquest.game.logic.heroes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dankquest.game.com.dankquest.game.logic.Hero;
import com.dankquest.game.com.dankquest.game.logic.HeroClass;
import com.dankquest.game.com.dankquest.game.logic.buffs.Buff;
import com.dankquest.game.com.dankquest.game.logic.skill.FreezingMistSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.IceWallSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.IcelanceSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.TwinBladeSkill;
import com.dankquest.game.com.dankquest.game.util.Assets;

import java.util.HashMap;

/**
 * Created by Antah on 2015/10/30.
 */
public class LorewalkerHero extends Hero {
    public LorewalkerHero(String name){
        super(name);

        this.healthTotal = 300;
        this.healthCurrent = 300;
        this.initiative = 15;
        this.attackDamage = 40;
        this.damageMultiplier = 1;
        this.baseShield = 0;
        this.shield = this.baseShield;

        this.heroClass = HeroClass.WARRIOR;
        this.skill1 = new FreezingMistSkill();
        this.skill2 = new IcelanceSkill();
        this.skill3 = new IceWallSkill();
        this.skill4 = new TwinBladeSkill();
        this.buffMap = new HashMap<String, Buff>();

        Assets.load("portraits/LorewalkerPortrait.png", Pixmap.class);
        this.portraitTexture = Assets.manager.get("portraits/LorewalkerPortrait.png", Pixmap.class);
        Assets.load("heroes/LorewalkerSheet.png", Texture.class);
        this.characterTexture = Assets.manager.get("heroes/LorewalkerSheet.png", Texture.class);
    }

}
