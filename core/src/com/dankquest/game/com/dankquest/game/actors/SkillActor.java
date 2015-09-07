package com.dankquest.game.com.dankquest.game.actors;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.screens.DankGame;


/**
 * Created by Antah on 2015/09/05.
 */
public class SkillActor extends Actor {

    private Texture texture;

    BitmapFont bitmapFont;

    private Pixmap pixmap;

    private int x, y, skillNumber;

    public SkillActor(int x, int y, int skillNumber) {
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(1, 0, 0, 1);
        pixmap = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
        this.x = x;
        this.y = y;
        this.skillNumber = skillNumber;
        setBounds(x, y, 50, 50);
        update();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, x, y);
        bitmapFont.draw(batch, Dank.activeHero.getSkill(skillNumber).toString(),x,y+62);
    }

    public void update() {
        pixmap.setColor(0, 0, 0, 0);
        pixmap.fill();
        pixmap.drawPixmap(Dank.activeHero.getSkill(skillNumber).getImage(),0,0);
        if(DankGame.getSkillCast() == skillNumber){
            pixmap.setColor(1, 1, 1, 0.2f);
            pixmap.fillRectangle(0, 0, 50, 50);
            pixmap.setColor(1, 1, 1, 0.4f);
            pixmap.fillRectangle(20, 20, 30, 30);
            pixmap.setColor(1, 1, 1, 0.6f);
            pixmap.fillRectangle(40, 40, 10, 10);
        }
        texture = new Texture(pixmap);
    }
}
