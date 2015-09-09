package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.logic.Hero;

import java.util.List;

/**
 * Created by Miko on 2015-09-04.
 */
public class FireballSkill extends Skill {

    public FireballSkill () {
        amountOfTargets = 1;
        basePixmap = new Pixmap(Gdx.files.internal("skills/fireball.png"));
        skillName = "Fireball";
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

    public void cast() {
        Dank.targetList.get(1).healthCurrent -=  Dank.targetList.get(0).attackDamage*2;
    }

    public void castAnimation(){
        currentSkillFrame = skillAnimation.getKeyFrame(Dank.castStateTime, true);
        spriteBatch.begin();
        spriteBatch.draw(currentSkillFrame, Dank.targetList.get(1).heroActor.getX()-64, Dank.targetList.get(1).heroActor.getY()-64);
        spriteBatch.end();
    }
}