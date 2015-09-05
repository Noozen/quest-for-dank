package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dankquest.game.com.dankquest.game.logic.Hero;

import java.util.List;

/**
 * Created by Miko on 2015-09-04.
 */
public class TwinBladeSkill implements Skill {

    Texture baseTexture = new Texture(Gdx.files.internal("skills/twin_blade.png"));

    public void cast(List<Hero> heroList) {
        heroList.get(1).healthCurrent -= heroList.get(0).attackDamage * 0.8;
        heroList.get(2).healthCurrent -= heroList.get(0).attackDamage * 0.8;
    };

    public Texture getImage() {
        return baseTexture;
    }

    public String toString(){ return "TwinBlade"; }
}