package com.dankquest.game.com.dankquest.game.actors;

/**
 * Created by Antah on 2015/09/05.
 */

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.logic.Hero;
import com.dankquest.game.com.dankquest.game.screens.DankGame;

import java.util.List;

public class HeroActor extends Actor {

    private Texture texture;

    BitmapFont bitmapFont;



    private int x, y, heroNumber;

    private Pixmap pixmap;

    List<Hero> characterList;

    private DankGame dankGameInstance;

    public HeroActor(int x, int y, int heroNumber, boolean playerControlled, DankGame dankGame) {
        if(playerControlled) {
            characterList = Dank.chosenHeroesList;
        } else {
            characterList = Dank.enemyHeroesList;
        }
        pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(1, 0, 0, 1);
        this.x = x;
        this.y = y;
        this.heroNumber = heroNumber;
        this.dankGameInstance = dankGame;
        setBounds(x, y, 64, 64);
        addCustomListener();
        update();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, x, y);
        bitmapFont.draw(batch, characterList.get(heroNumber - 1).name, x, y+74);
    }

    public void update() {
        pixmap.drawPixmap(characterList.get(heroNumber - 1).getImage(), 0, 0);
        pixmap.setColor(1, 0.5f, 0.5f, 1);
        pixmap.fillRectangle(0, 0, 64, 10);
        pixmap.setColor(1, 0, 0, 1);
        pixmap.fillRectangle(0, 0, (int) (64 * (characterList.get(heroNumber - 1).healthCurrent / characterList.get(heroNumber - 1).healthTotal)), 10);
        if(Dank.targetList.size()>1) {
            if(Dank.targetList.subList(1,Dank.targetList.size()).contains(characterList.get(heroNumber-1))) {
                pixmap.setColor(1,1,1,0.4f);
                pixmap.fillRectangle(0,0,64,64);
            }
        }
        texture = new Texture(pixmap);
        pixmap.setColor(1,1,1,0);
        pixmap.fill();
    }

    public void addCustomListener() {
        addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (Dank.skillCastNumber == 0) {
                    return;
                }
                if (Dank.enemyTurnInProgress == true) {
                    return;
                }
                if (Dank.targetList.subList(1, Dank.targetList.size()).contains(characterList.get(heroNumber - 1))) {
                    for(int i=1;i<Dank.targetList.size();i++) {
                        if(Dank.targetList.get(i) == characterList.get(heroNumber-1)) {
                            Dank.targetList.remove(i);
                            dankGameInstance.update();
                            return;
                        }
                    }
                }
                if (Dank.targetList.size() <= characterList.get(heroNumber - 1).getSkill(Dank.skillCastNumber).getAmountOfTargets())
                {
                    Dank.targetList.add(characterList.get(heroNumber - 1));
                }
                dankGameInstance.update();
            }
        });
    }
}