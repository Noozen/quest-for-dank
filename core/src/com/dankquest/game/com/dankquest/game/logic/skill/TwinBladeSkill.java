package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dankquest.game.com.dankquest.game.logic.Hero;

import java.util.List;

/**
 * Created by Miko on 2015-09-04.
 */
public class TwinBladeSkill extends Skill {

    public TwinBladeSkill () {
        amountOfTargets = 1;
        basePixmap = new Pixmap(Gdx.files.internal("skills/twin_blade.png"));
        skillName = "Twin Blade";
        skillSheet = new Texture(Gdx.files.internal("skills_animations/Fireball.png"));
        skillFrames = new TextureRegion[3 * 5];
        TextureRegion[][] tmp = TextureRegion.split(skillSheet, 192, 192);
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                skillFrames[index++] = tmp[i][j];
            }
        }
        skillAnimation = new Animation(0.05f, skillFrames);
        spriteBatch = new SpriteBatch();
    }
    public void cast(List<Hero> heroList) {
        heroList.get(1).healthCurrent -= heroList.get(0).attackDamage * 0.8;
        heroList.get(2).healthCurrent -= heroList.get(0).attackDamage * 0.8;
    };
    public void castAnimation(List<Hero> heroList, float skillTime){
        currentSkillFrame = skillAnimation.getKeyFrame(skillTime, true);
        spriteBatch.begin();
        spriteBatch.draw(currentSkillFrame, 50, 50);
        spriteBatch.end();
    }
}