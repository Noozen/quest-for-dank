package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dankquest.game.com.dankquest.game.logic.Dank;

import static java.lang.Math.sqrt;

/**
 * Created by Antah on 2015/09/09.
 */
public class IcelanceSkill extends Skill {
    private Animation skillAnimationBreak, skillAnimationFlying, skillAnimationCreate;
    protected TextureRegion[] skillFramesBreak, skillFramesFlying, skillFramesCreate;
    private float flyingAnimationTime;
    public IcelanceSkill(){
        amountOfTargets = 1;
        basePixmap = new Pixmap(Gdx.files.internal("skills/icelance.png"));
        skillName = "Ice Lance";
        skillSheet = new Texture(Gdx.files.internal("skills_animations/Icelance.png"));
        TextureRegion[][] tmp = TextureRegion.split(skillSheet, 192, 192);
        skillFramesCreate = new TextureRegion[8];
        int index = 0, i;
        for(i = 0; i < 5; i++){
            skillFramesCreate[index] = tmp[0][i];
            index++;
        }
        for(i = 1; i < 4; i++){
            skillFramesCreate[index] = tmp[1][i];
            index++;
        }
        skillFramesFlying = new TextureRegion[1];
        skillFramesFlying[0] = tmp[1][3];
        skillFramesBreak = new TextureRegion[4];
        index = 1;
        skillFramesBreak[0] = tmp[1][4];
        for(i = 0; i < 3; i++){
            skillFramesBreak[index] = tmp[2][i];
            index++;
        }
        skillAnimationCreate = new Animation(0.02f, skillFramesCreate);
        skillAnimationBreak = new Animation(0.1f, skillFramesBreak);
}

    public void cast() {
        double damage = Dank.targetList.get(0).getAttackDamage()*2 * Dank.targetList.get(0).getDamageMultiplier();
        Dank.targetList.get(1).healthCurrent -= damage;
    };

    @Override
    public void draw(Batch batch, float playTime) {
        if (playTime <= skillAnimationCreate.getAnimationDuration()) {
            batch.draw(skillAnimationCreate.getKeyFrame(playTime),
                    Dank.targetList.get(0).heroActor.getX() - Dank.targetList.get(0).heroActor.getWidth(),
                    Dank.targetList.get(0).heroActor.getY() - Dank.targetList.get(0).heroActor.getHeight(),
                    96f, 96f, 192f, 192f, 0.3f, 0.3f, 135f + (float) (Math.toDegrees(Math.atan((Dank.targetList.get(1).heroActor.getY() - Dank.targetList.get(0).heroActor.getY()) / (Dank.targetList.get(1).heroActor.getX() - Dank.targetList.get(0).heroActor.getX())))));
        } else if (playTime <= skillAnimationCreate.getAnimationDuration() + skillAnimationFlying.getAnimationDuration()) {
            batch.draw(skillAnimationFlying.getKeyFrame(playTime - skillAnimationCreate.getAnimationDuration()),
                    Dank.targetList.get(0).heroActor.getX() - Dank.targetList.get(0).heroActor.getWidth() + 1f * ((playTime - skillAnimationCreate.getAnimationDuration()) / skillAnimationFlying.getAnimationDuration()) * (Dank.targetList.get(1).heroActor.getX() - Dank.targetList.get(0).heroActor.getX()),
                    Dank.targetList.get(0).heroActor.getY() - Dank.targetList.get(0).heroActor.getHeight() + 1f * ((playTime - skillAnimationCreate.getAnimationDuration()) / skillAnimationFlying.getAnimationDuration()) * (Dank.targetList.get(1).heroActor.getY() - Dank.targetList.get(0).heroActor.getY()),
                    96f, 96f, 192f, 192f, 0.3f, 0.3f, 135f + (float) (Math.toDegrees(Math.atan((Dank.targetList.get(1).heroActor.getY() - Dank.targetList.get(0).heroActor.getY()) / (Dank.targetList.get(1).heroActor.getX() - Dank.targetList.get(0).heroActor.getX())))));
        } else {
            batch.draw(skillAnimationBreak.getKeyFrame(playTime - skillAnimationCreate.getAnimationDuration() - skillAnimationFlying.getAnimationDuration()),
                    Dank.targetList.get(1).heroActor.getX() - Dank.targetList.get(0).heroActor.getWidth(),
                    Dank.targetList.get(1).heroActor.getY() - Dank.targetList.get(1).heroActor.getHeight(),
                    96f, 96f, 192f, 192f, 0.3f, 0.3f, 135f + (float) (Math.toDegrees(Math.atan((Dank.targetList.get(1).heroActor.getY() - Dank.targetList.get(0).heroActor.getY()) / (Dank.targetList.get(1).heroActor.getX() - Dank.targetList.get(0).heroActor.getX())))));
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
        double x = Dank.targetList.get(1).heroActor.getX() - Dank.targetList.get(0).heroActor.getX();
        double y = Dank.targetList.get(1).heroActor.getY() - Dank.targetList.get(0).heroActor.getY();
        double pathLength = sqrt(Math.pow(x,2) + Math.pow(y,2));
        flyingAnimationTime = (float)pathLength/600;
        skillAnimationFlying = new Animation(flyingAnimationTime, skillFramesFlying);
    }

    @Override
    public float getAnimationDuration() {
        return skillAnimationBreak.getAnimationDuration()+skillAnimationFlying.getAnimationDuration()+ skillAnimationCreate.getAnimationDuration();
    }

    @Override
    public boolean isAnimationFinished(float playTime) {
        playTime -= skillAnimationCreate.getAnimationDuration() + skillAnimationFlying.getAnimationDuration();
        if(playTime < 0)
            playTime = 0;
        return skillAnimationBreak.isAnimationFinished(playTime);
    }

    @Override
    public void setNormalPlayMode() {
        skillAnimationCreate.setPlayMode(Animation.PlayMode.NORMAL);
        skillAnimationFlying.setPlayMode(Animation.PlayMode.NORMAL);
        skillAnimationBreak.setPlayMode(Animation.PlayMode.NORMAL);
    }
}
