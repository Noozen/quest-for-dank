package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dankquest.game.com.dankquest.game.logic.Dank;

/**
 * Created by KTamo_000 on 2015-12-03.
 */
public class HellboltSkill extends Skill {
    public HellboltSkill(){
        amountOfTargets = 1;
        basePixmap = new Pixmap(Gdx.files.internal("skills/fireball.png"));
        skillName = "Hellbolt";
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
        double damage = Dank.targetList.get(0).getAttackDamage() * 3 * Dank.targetList.get(0).getDamageMultiplier();
        Dank.targetList.get(1).recieveDamage(damage, true);
    }

    @Override
    public void draw(Batch batch, float playTime) {
        batch.draw(skillAnimation.getKeyFrame(playTime), Dank.targetList.get(1).heroActor.getX() - Dank.targetList.get(1).heroActor.getWidth(),Dank.targetList.get(1).heroActor.getY() - Dank.targetList.get(1).heroActor.getHeight());
    }

    @Override
    public float getX() {
        return Dank.targetList.get(1).heroActor.getX()-64;
    }

    @Override
    public float getY() {
        return Dank.targetList.get(1).heroActor.getY()-64;
    }

    @Override
    public void createAnimation() {

    }

    @Override
    public float getAnimationDuration() {
        return skillAnimation.getAnimationDuration();
    }

    @Override
    public boolean isAnimationFinished(float playTime) {
        return skillAnimation.isAnimationFinished(playTime);
    }

    @Override
    public void setNormalPlayMode() {
        skillAnimation.setPlayMode(Animation.PlayMode.NORMAL);
    }
}
