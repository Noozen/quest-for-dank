package com.dankquest.game.com.dankquest.game.actors;

/**
 * Created by Antah on 2015/09/05.
 */

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.Pixmap;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.*;
        import com.badlogic.gdx.scenes.scene2d.Actor;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.InputListener;
        import com.dankquest.game.com.dankquest.game.logic.Dank;
        import com.dankquest.game.com.dankquest.game.logic.heroes.Hero;
        import com.dankquest.game.com.dankquest.game.screens.DankGame;

        import java.util.List;

public class HeroActor extends Actor {

    private Texture texture, heroSheet;
    private TextureRegion[] heroFrames;
    private Animation standingAnimation;
    private SpriteBatch spriteBatch;
    private float elapsedTime;
    boolean playerControlled;

    BitmapFont bitmapFont;

    private int x, y, heroNumber;

    private Pixmap pixmap;

    List<Hero> characterList;

    private DankGame dankGameInstance;

    public HeroActor(int x, int y, int heroNumber, boolean playerControlled, DankGame dankGame) {
        this.playerControlled = playerControlled;
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
        if(playerControlled == true) {
            heroSheet = characterList.get(heroNumber - 1).getCharacterSheet();
            heroFrames = new TextureRegion[4 * 3];
            TextureRegion[][] tmp = TextureRegion.split(heroSheet, 32, 32);
            int index = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    heroFrames[index++] = tmp[i][j];
                }
            }
            TextureRegion[] standingFrames = new TextureRegion[2];
            standingFrames[0] = heroFrames[7];
            standingFrames[1] = heroFrames[8];
            standingAnimation = new Animation(1f, standingFrames);
            spriteBatch = new SpriteBatch();
        } else {
            heroSheet = characterList.get(heroNumber - 1).getCharacterSheet();
            heroFrames = new TextureRegion[1 * 1];
            TextureRegion[][] tmp = TextureRegion.split(heroSheet, 64, 64);
            int index = 0;
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 1; j++) {
                    heroFrames[index++] = tmp[i][j];
                }
            }
            standingAnimation = new Animation(1f, heroFrames);
            spriteBatch = new SpriteBatch();
        }
        setBounds(x, y, 64, 64);
        addCustomListener();
        update();
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        if(playerControlled == true) {
            batch.draw(standingAnimation.getKeyFrame(elapsedTime, true), this.x + 16, this.y + 16);
        } else {
            batch.draw(standingAnimation.getKeyFrame(elapsedTime, true), this.x, this.y);
        }
        batch.draw(texture, x, y);
        bitmapFont.draw(batch, characterList.get(heroNumber - 1).name, x, y+74);
    }

    public void update() {
        pixmap.setColor(1, 0.5f, 0.5f, 1);
        pixmap.fillRectangle(0, 0, 64, 10);
        pixmap.setColor(1, 0, 0, 1);
        pixmap.fillRectangle(0, 0, (int) (64 * (characterList.get(heroNumber - 1).getHealthCurrent() / characterList.get(heroNumber - 1).getHealthTotal())), 10);
        pixmap.setColor(0, 0.5f, 1, 1);
        if(characterList.get(heroNumber - 1).getShield() > characterList.get(heroNumber - 1).getHealthTotal())
            pixmap.fillRectangle(0, 0, 64, 10);
        else
            pixmap.fillRectangle(0, 0, (int) (64 * (characterList.get(heroNumber - 1).getShield() / characterList.get(heroNumber - 1).getHealthTotal())), 5);
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
                    //Wyswietl tooltip
                    return;
                }
                if (Dank.enemyTurnInProgress == true || Dank.animationInProgress == true || characterList.get(heroNumber - 1).getSkill(Dank.skillCastNumber).getAmountOfTargets() == 0) {
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
                if (Dank.targetList.size() == 1 + characterList.get(heroNumber - 1).getSkill(Dank.skillCastNumber).getAmountOfTargets())
                {
                    Dank.targetList.remove(1);
                }
                Dank.targetList.add(characterList.get(heroNumber - 1));
                dankGameInstance.update();
            }
        });
    }
}