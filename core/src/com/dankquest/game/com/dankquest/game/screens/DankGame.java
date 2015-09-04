package com.dankquest.game.com.dankquest.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.awt.*;

/**
 * Created by Antah on 2015/09/02.
 */
public class DankGame extends BasicScreen {
    private Stage stage;
    private Table table;

    private Music battleMusic;

    private Image backgroundImage, backgroundImage1;

    private Skin skin;

    private TextButton retreatButton,
            skill1Button, skill2Button, skill3Button, skill4Button,
            item1Button, item2Button,
            attackButton, guardButton;

    public DankGame(Game game) {
        super(game);
    }


    @Override
    public void show() {
        stage = new Stage(new FitViewport(640,480));
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        //Music Setup
        battleMusic = Gdx.audio.newMusic(Gdx.files.internal("music/Battle2.ogg"));
        battleMusic.setVolume(0.2f);
        battleMusic.setLooping(true);
        battleMusic.play();

        //Background Setup
        backgroundImage = new Image(new TextureRegion(new Texture(Gdx.files.internal("backgrounds/Castle.png"))));
        backgroundImage1 = new Image(new TextureRegion(new Texture(Gdx.files.internal("backgrounds/Castle1.png"))));
        table.addActor(backgroundImage);
        table.addActor(backgroundImage1);

        //Skin setup
        skin = new Skin(Gdx.files.internal("skins/rainbowpack.json"),
                new TextureAtlas(Gdx.files.internal("skins/rainbowpack.pack")));

        //Retreat Button Setup
        retreatButton = new TextButton("Retreat", skin, "orange_yellow_fat");

        retreatButton.setWidth(130);
        retreatButton.setHeight(60);

        retreatButton.setX(0);
        retreatButton.setY(420);

        retreatButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                battleMusic.stop();
                game.setScreen(new GameMenu(game));
            }
        });

        table.addActor(retreatButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int heigth) {
        stage.getViewport().update(width, heigth);
    }
}