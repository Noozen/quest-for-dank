package com.dankquest.game.com.dankquest.game.actors;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.screens.DankGame;


/**
 * Created by Antah on 2015/09/05.
 */
public class SkillIconActor extends Actor {

    private Texture texture;

    BitmapFont bitmapFont;

    private Pixmap pixmap;

    private int x, y, skillNumber;

    private DankGame dankGameInstance;

    public SkillIconActor(int x, int y, int skillNumber, DankGame dankGame) {
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(1, 0, 0, 1);
        pixmap = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
        this.x = x;
        this.y = y;
        this.skillNumber = skillNumber;
        this.dankGameInstance = dankGame;
        setBounds(x, y, 50, 50);
        addCustomListener();
        update();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, x, y);
        bitmapFont.draw(batch, Dank.activeHero.getSkill(skillNumber).toString(), x, y + 62);
    }

    public void update() {
        pixmap.setColor(0, 0, 0, 0);
        pixmap.fill();
        pixmap.drawPixmap(Dank.activeHero.getSkill(skillNumber).getImage(), 0, 0);
        if(Dank.skillCastNumber == skillNumber){
            pixmap.setColor(1, 1, 1, 0.4f);
            pixmap.fillRectangle(0, 0, 100, 100);
        }
        texture = new Texture(pixmap);
        pixmap.setColor(1, 1, 1, 0);
        pixmap.fill();
    }

    public void addCustomListener() {
        addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (Dank.enemyTurnInProgress == true || Dank.animationInProgress == true) {
                    return;
                }
                Dank.targetList.clear();
                if(Dank.skillCastNumber == skillNumber){
                    Dank.skillCastNumber = 0;
                    dankGameInstance.update();
                    return;
                }
                Dank.skillCastNumber = skillNumber;
                Dank.targetList.add(Dank.activeHero);
                dankGameInstance.update();
            }
        });
    }
}
