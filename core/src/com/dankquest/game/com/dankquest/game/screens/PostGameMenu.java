package com.dankquest.game.com.dankquest.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.util.Assets;

/**
 * Created by Antah on 2015/10/31.
 */
public class PostGameMenu extends BasicScreen {
    public PostGameMenu(Game game) {
        super(game);
    }
    private Stage stage;
    private Table table;
    private Image backgroundImage;
    private Skin skin;

    private TextButton playButton;
    private TextButton exitButton;
    private TextButton backButton;
    private int numberOfClicks = 0;

    @Override
    public void show() {
        loadAssets();
        setupStage();
        setupTable();
        setupBackground();
        setupSkin();
        setupPlayButton();
        setupExitButton();
        setupBackButton();
    }

    private void loadAssets() {
        Assets.loadMainMenu();
    }

    private void setupTable() {
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
    }
    private void setupBackButton() {
        backButton = new TextButton("Back", skin, "orange_yellow_fat");

        backButton.setWidth(100);
        backButton.setHeight(70);

        backButton.setX(0);
        backButton.setY(0);

        backButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Dank.chosenHeroesList.clear();
                game.setScreen(new AdventureMenu(game));
            }
        });
        table.addActor(backButton);
    }

    private void setupExitButton() {
        exitButton = new TextButton("Exit", skin, "orange_yellow_fat");

        exitButton.setWidth(100);
        exitButton.setHeight(70);

        exitButton.setX(540);
        exitButton.setY(0);

        exitButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
        });

        table.addActor(exitButton);
    }


    private void setupPlayButton() {
        numberOfClicks = 0;
        playButton = new TextButton("You are so smart.\nGreat job!", skin, "orange_yellow_fat");

        playButton.setWidth(400);
        playButton.setHeight(300);

        playButton.setX(120);
        playButton.setY(90);

        playButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(numberOfClicks == 1){
                    //Dank.chosenHeroesList.clear();
                    //game.setScreen(new BirthdayScreen(game));
                    return true;
                }
                if(numberOfClicks == 0) {
                    playButton.setText("404 error:\nString not found");
                    numberOfClicks++;
                }
                return true;
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
