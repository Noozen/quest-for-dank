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
import com.dankquest.game.com.dankquest.game.logic.buffs.IceWallBuff;

import java.util.List;
import com.badlogic.gdx.graphics.g2d.Sprite;
/**
 * Created by Miko on 2015-09-04.
 */
public class IceWallSkill extends Skill {
    Pixmap pixmap;
    Texture texture;

    public IceWallSkill () {
        amountOfTargets = 0;
        basePixmap = new Pixmap(Gdx.files.internal("skills/icewall.png"));
        skillName = "Ice Wall";
        skillSheet = new Texture(Gdx.files.internal("skills_animations/icewall.png"));
        skillFrames = new TextureRegion[2 * 5];
        TextureRegion[][] tmp = TextureRegion.split(skillSheet, 190, 205);
        int index = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                skillFrames[index++] = tmp[i][j];
            }
        }
        skillAnimation = new Animation(0.1f, skillFrames);
        spriteBatch = new SpriteBatch();
        pixmap = new Pixmap(640, 480, Pixmap.Format.RGBA8888);
    }

    public void cast() {
        Buff iceWallBuff = new IceWallBuff();
        for (Hero h : Dank.chosenHeroesList) {
            if (h.getHealthCurrent() > 0) {
                h.buffMap.put(iceWallBuff.getName() + Dank.targetList.get(0).toString(), iceWallBuff);
            }
        }
    }

    @Override
    public void draw(Batch batch, float playTime) {
        for(Hero h: Dank.chosenHeroesList){
            if(h.getHealthCurrent() > 0){
                batch.draw(skillAnimation.getKeyFrame(playTime), h.heroActor.getX() - 64, h.heroActor.getY() - 40);
            }
        }
        if(playTime >= 0.2f && playTime < 0.6f) {
            pixmap.setColor(1, 1, 1, 1 - playTime);
            pixmap.fillRectangle(0, 0, 640, 480);
            texture = new Texture(pixmap);
            pixmap.setColor(1, 1, 1, 0);
            pixmap.fill();
            batch.enableBlending();
            batch.draw(texture, 0, 0);
        }
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
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
