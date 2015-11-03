package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dankquest.game.com.dankquest.game.logic.Dank;

/**
 * Created by Antah on 2015/09/12.
 */
public class FreezingMistSkill extends Skill{
    private Animation skillAnimationMist, skillAnimationShards, skillAnimationSpikes;
    protected TextureRegion[] skillFramesMist, skillFramesShards, skillFramesSpikes;
    private float flyingAnimationTime;
    public FreezingMistSkill(){
        amountOfTargets = 1;
        basePixmap = new Pixmap(Gdx.files.internal("skills/mist.png"));
        skillName = "Freezing Mist";
    }

    public void cast() {
        double damage = Dank.targetList.get(0).getAttackDamage()*2 * Dank.targetList.get(0).getDamageMultiplier();
        Dank.targetList.get(1).healthCurrent -= damage;
    };

    @Override
    public void draw(Batch batch, float playTime) {
        if (playTime >= 0.1f) {
            batch.draw(skillAnimationSpikes.getKeyFrame(playTime - 0.1f),
                    Dank.targetList.get(1).heroActor.getX() - Dank.targetList.get(1).heroActor.getWidth(),
                    Dank.targetList.get(1).heroActor.getY() - Dank.targetList.get(1).heroActor.getHeight());
        }
        if (playTime <= skillAnimationMist.getAnimationDuration()) {
            Color color = batch.getColor();
            float oldAlpha = color.a;
            float scale = 0.2f;
            color.a = oldAlpha*scale;
            batch.setColor(color);
            batch.draw(skillAnimationMist.getKeyFrame(playTime),
                    Dank.targetList.get(1).heroActor.getX() - Dank.targetList.get(1).heroActor.getWidth(),
                    Dank.targetList.get(1).heroActor.getY() - Dank.targetList.get(1).heroActor.getHeight());
            color.a = oldAlpha;
            batch.setColor(color);
        }
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
        skillSheet = new Texture(Gdx.files.internal("skills_animations/Ice1.png"));
        TextureRegion[][] tmp = TextureRegion.split(skillSheet, 192, 192);
        skillFramesMist = new TextureRegion[5];
        int index = 0, i;
        for(i = 0; i < 5; i++){
            skillFramesMist[index] = tmp[0][i];
            index++;
        }
        skillFramesSpikes = new TextureRegion[5];
        index = 0;
        for(i = 0; i < 5; i++){
            skillFramesSpikes[index] = tmp[1][i];
            index++;
        }
        skillFramesShards = new TextureRegion[1];

        skillAnimationMist = new Animation(0.1f, skillFramesMist);
        skillAnimationSpikes = new Animation(0.1f, skillFramesSpikes);
    }

    @Override
    public float getAnimationDuration() {
        return skillAnimationMist.getAnimationDuration() + 0.1f;
    }

    @Override
    public boolean isAnimationFinished(float playTime) {
        return skillAnimationSpikes.isAnimationFinished(playTime);
    }

    @Override
    public void setNormalPlayMode() {
        skillAnimationMist.setPlayMode(Animation.PlayMode.NORMAL);
        //skillAnimationShards.setPlayMode(Animation.PlayMode.NORMAL);
        skillAnimationSpikes.setPlayMode(Animation.PlayMode.NORMAL);
    }
}
