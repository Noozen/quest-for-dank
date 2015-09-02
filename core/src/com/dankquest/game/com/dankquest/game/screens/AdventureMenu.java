package com.dankquest.game.com.dankquest.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AdventureMenu extends BasicScreen {

    private Stage stage;
    private Table table;

    Music relaxingMusic = Gdx.audio.newMusic(Gdx.files.internal("music/night_hours.mp3"));

    private Image backgroundImage;

    private Skin skin;

    private TextButton toBattleButton;

    public AdventureMenu(Game game) {
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
        relaxingMusic.setVolume(0.2f);
        relaxingMusic.setLooping(true);
        relaxingMusic.play();

        //Background Setup
        backgroundImage = new Image(new TextureRegion(new Texture(Gdx.files.internal("backgrounds/Tower1.png"))));
        table.addActor(backgroundImage);

        //Skin setup
        skin = new Skin(Gdx.files.internal("skins/rainbowpack.json"),
                new TextureAtlas(Gdx.files.internal("skins/rainbowpack.pack")));

        //Adventure Button Setup
        toBattleButton = new TextButton("To Battle!", skin, "orange_yellow_fat");

        toBattleButton.setWidth(240);
        toBattleButton.setHeight(140);

        toBattleButton.setX(340);
        toBattleButton.setY(80);

        toBattleButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                relaxingMusic.stop();
                game.setScreen(new DankGame(game));
            }
        });

        table.addActor(toBattleButton);
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