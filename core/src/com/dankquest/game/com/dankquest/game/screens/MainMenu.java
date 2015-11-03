package com.dankquest.game.com.dankquest.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dankquest.game.com.dankquest.game.util.Assets;
import com.dankquest.game.com.dankquest.game.util.DankMusic;

public class MainMenu extends BasicScreen {
    private Stage stage;
    private Table table;
    private Image backgroundImage;
    private Skin skin;

    private TextButton playButton;
    private TextButton settingsButton;
    private TextButton exitButton;

    public MainMenu(Game game) {
        super(game);
    }

    @Override
    public void show() {
        loadAssets();
        setupStage();
        setupTable();
        musicSetup();
        setupBackground();
        setupSkin();
        setupPlayButton();
        setupSettingsButton();
        setupExitButton();
    }

    private void loadAssets() {
        Assets.loadMainMenu();
    }

    private void setupTable() {
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
    }

    private void setupExitButton() {
        exitButton = new TextButton("Exit", skin, "orange_yellow_fat");

        exitButton.setWidth(200);
        exitButton.setHeight(70);

        exitButton.setX(400);
        exitButton.setY(100);

        exitButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });

        table.addActor(exitButton);
    }

    private void setupSettingsButton() {
        settingsButton = new TextButton("Options", skin, "orange_yellow_fat");

        settingsButton.setWidth(200);
        settingsButton.setHeight(70);

        settingsButton.setX(400);
        settingsButton.setY(205);

        settingsButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new SettingsMenu(game));
            }
        });

        table.addActor(settingsButton);
    }

    private void setupPlayButton() {
        playButton = new TextButton("Play", skin, "orange_yellow_fat");

        playButton.setWidth(200);
        playButton.setHeight(70);

        playButton.setX(400);
        playButton.setY(310);

        playButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameMenu(game));
            }
        });

        table.addActor(playButton);
    }

    private void setupSkin() {
        skin = Assets.manager.get("skins/rainbowpack.json", Skin.class);
    }

    private void setupBackground() {
        backgroundImage = new Image(Assets.manager.get("backgrounds/Tower1.png", Texture.class));
        table.addActor(backgroundImage);
    }

    private void setupStage() {
        stage = new Stage(new FitViewport(640,480));
        Gdx.input.setInputProcessor(stage);
    }

    private void musicSetup() {
        DankMusic.changeSoundtrack("music/night_hours.mp3");
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