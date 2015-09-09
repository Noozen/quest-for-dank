package com.dankquest.game.com.dankquest.game.actors;

/**
 * Created by Antah on 2015/09/09.
 */

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.logic.Hero;
import com.dankquest.game.com.dankquest.game.logic.skill.Skill;
import com.dankquest.game.com.dankquest.game.screens.DankGame;

import java.util.List;

public class CastAnimationActor extends Actor {

    private TextureRegion texture;
    private Skill skill;

    public CastAnimationActor(Skill skill) {
        setBounds(this.skill.getX(),  this.skill.getY(), 192, 192);
        this.skill = skill;
        update();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, this.skill.getX(), this.skill.getY());
    }

    public void update() {
        texture = this.skill.getFrame();
    }
}