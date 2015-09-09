package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.logic.Hero;
import com.dankquest.game.com.dankquest.game.logic.buffs.Buff;
import com.dankquest.game.com.dankquest.game.logic.buffs.ExhaustedBuff;

import java.util.List;

/**
 * Created by Miko on 2015-09-04.
 */
public class ArrowSkill extends Skill {

    public ArrowSkill () {
        amountOfTargets = 1;
        basePixmap = new Pixmap(Gdx.files.internal("skills/arrow.png"));
        skillName = "Arrow";
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

        double damage = Dank.targetList.get(0).getAttackDamage() * Dank.targetList.get(0).getDamageMultiplier();
        Dank.targetList.get(1).healthCurrent -= damage;
        Buff exhaustedBuff = new ExhaustedBuff();
        Dank.targetList.get(1).buffMap.put(exhaustedBuff.getName() + Dank.targetList.get(0).toString(), exhaustedBuff);
    }

    @Override
    public void draw(Batch batch, float playTime) {
        batch.draw(skillAnimation.getKeyFrame(playTime), Dank.targetList.get(1).heroActor.getX() - Dank.targetList.get(1).heroActor.getWidth(),Dank.targetList.get(1).heroActor.getY() - Dank.targetList.get(1).heroActor.getHeight());
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }
}
