package com.dankquest.game.com.dankquest.game.logic.skill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.dankquest.game.com.dankquest.game.logic.Hero;

import java.util.List;

/**
 * Created by Miko on 2015-09-04.
 */
public abstract class Skill {

    protected int amountOfTargets;
    protected Pixmap basePixmap;
    protected String skillName;
    //Animation
    protected Texture skillSheet;
    protected TextureRegion[] skillFrames;
    public Animation skillAnimation;
    protected SpriteBatch spriteBatch;
    protected TextureRegion currentSkillFrame;

    abstract public void cast();

    public Pixmap getImage() {
        return basePixmap;
    }

    public int getAmountOfTargets() {
        return amountOfTargets;
    }

    public String toString() {
        return skillName;
    }

    abstract public void draw (Batch batch, float playTime);

    abstract public float getX();

    abstract public float getY();

    abstract public void createAnimation();

    public abstract float getAnimationDuration();

    public abstract boolean isAnimationFinished(float playTime);

    public abstract void setNormalPlayMode();

}
