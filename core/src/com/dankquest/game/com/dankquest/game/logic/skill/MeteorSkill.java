package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.logic.heroes.Hero;

/**
 * Created by Antah on 2015/10/31.
 */
public class MeteorSkill extends Skill{

    public MeteorSkill () {
        amountOfTargets = 0;
        basePixmap = new Pixmap(Gdx.files.internal("skills/meteor.png"));
        skillName = "Meteor Shower";
        skillSheet = new Texture(Gdx.files.internal("skills_animations/Meteor.png"));
        skillFrames = new TextureRegion[3 * 5];
        TextureRegion[][] tmp = TextureRegion.split(skillSheet, 206, 193);
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                skillFrames[index++] = tmp[i][j];
            }
        }
        skillAnimation = new Animation(0.07f, skillFrames);
        spriteBatch = new SpriteBatch();
    }

    public void cast() {
        double damage = Dank.targetList.get(0).getAttackDamage() * 1 * Dank.targetList.get(0).getDamageMultiplier();
        for(Hero h: Dank.enemyHeroesList)
            h.recieveDamage(damage, false);
    }

    @Override
    public void draw(Batch batch, float playTime) {
        for(Hero h: Dank.enemyHeroesList)
            batch.draw(skillAnimation.getKeyFrame(playTime), h.heroActor.getX() - h.heroActor.getWidth(),h.heroActor.getY() - h.heroActor.getHeight(), 103, 0, 206, 193, -1, 1, 0);
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
