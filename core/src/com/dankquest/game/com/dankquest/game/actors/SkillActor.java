package com.dankquest.game.com.dankquest.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dankquest.game.com.dankquest.game.logic.Dank;


/**
 * Created by Antah on 2015/09/05.
 */
public class SkillActor extends Actor {

    private Texture texture;

    BitmapFont bitmapFont;

    private int x, y, skill_number;

    public SkillActor(int x, int y, int skill_number) {
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(1, 0, 0, 1);
        this.x = x;
        this.y = y;
        this.skill_number = skill_number;
        setBounds(x, y, 50, 50);
        update();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, x, y);
        bitmapFont.draw(batch, Dank.activeHero.getSkill(skill_number).toString(),x,y);
    }

    public void update() {
        texture = Dank.activeHero.getSkill(skill_number).getImage();
    }
}
