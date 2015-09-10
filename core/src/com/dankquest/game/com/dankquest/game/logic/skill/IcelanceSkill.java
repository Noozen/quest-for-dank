package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dankquest.game.com.dankquest.game.logic.Dank;

import static java.lang.Math.sqrt;

/**
 * Created by Antah on 2015/09/09.
 */
public class IcelanceSkill extends Skill {
    private Animation skillAnimationBreak, skillAnimationFlying;
    private float flyingAnimationTime;
    public IcelanceSkill(){
        amountOfTargets = 1;
        basePixmap = new Pixmap(Gdx.files.internal("skills/arrow.png"));
        skillName = "Icelance";
        skillSheet = new Texture(Gdx.files.internal("skills_animations/Icelance.png"));
        skillFrames = new TextureRegion[3*5];
}

    public void cast() {
        double damage = Dank.targetList.get(0).getAttackDamage()*2 * Dank.targetList.get(0).getDamageMultiplier();
        Dank.targetList.get(1).healthCurrent -= damage;
    };

    @Override
    public void draw(Batch batch, float playTime) {
        if(skillAnimation.isAnimationFinished(playTime)) {
            return;
        } else {
            batch.draw(skillAnimation.getKeyFrame(playTime),
                    Dank.targetList.get(0).heroActor.getX() + (playTime / skillAnimation.getAnimationDuration()) * (Dank.targetList.get(1).heroActor.getX() - Dank.targetList.get(0).heroActor.getX()),
                    Dank.targetList.get(0).heroActor.getY() + (playTime / skillAnimation.getAnimationDuration()) * (Dank.targetList.get(1).heroActor.getY() - Dank.targetList.get(0).heroActor.getY()));
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
        TextureRegion[][] tmp = TextureRegion.split(skillSheet, 192, 192);
        int index = 0;
        for (int j = 2; j < 4; j++) {
            skillFrames[index] = tmp[1][j];
            index++;
        }
        double x = Dank.targetList.get(1).heroActor.getX() - Dank.targetList.get(0).heroActor.getX();
        double y = Dank.targetList.get(1).heroActor.getY() - Dank.targetList.get(0).heroActor.getY();
        double pathLength = sqrt(Math.pow(x,2) + Math.pow(y,2));
        flyingAnimationTime = (float)pathLength/100;

        skillFrames[index] = tmp[1][4];
        index++;
        for (int j = 0; j < 3; j++) {
            skillFrames[index] = tmp[2][j];
            index++;
        }
        skillAnimation = new Animation(0.15f, skillFrames);
    }
}
