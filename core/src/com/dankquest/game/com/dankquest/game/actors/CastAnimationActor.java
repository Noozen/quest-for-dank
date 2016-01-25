package com.dankquest.game.com.dankquest.game.actors;

/**
 * Created by Antah on 2015/09/09.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dankquest.game.com.dankquest.game.logic.skill.Skill;

public class CastAnimationActor extends Actor {

    private Skill skill;
    public float playTime;

    public CastAnimationActor(Skill skill) {
        if(skill!=null) {
            skill.createAnimation();
            skill.setNormalPlayMode();
            playTime = 0;
            this.skill = skill;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(playTime+Gdx.graphics.getDeltaTime()<skill.getAnimationDuration()) {
            skill.draw(batch, playTime);
            playTime += Gdx.graphics.getDeltaTime();
            if(skill.isAnimationFinished(playTime)) {
                playTime = 0;
            }
        }
    }
}