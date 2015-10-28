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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.dankquest.game.com.dankquest.game.util.Assets;
import com.dankquest.game.com.dankquest.game.util.DankMusic;

public class GameMenu extends BasicScreen {

    private Stage stage;
    private Table table;

    private Image backgroundImage;

    private Skin skin;

    private TextButton adventureButton;
    private TextButton partyManagementButton;
    private TextButton backButton;
    private TextButton shopButton;

    public GameMenu(Game game) {
        super(game);
    }

    @Override
    public void show() {
        loadAssets();
        setupStage();
        setupTable();
        setupBackground();
        setupMusic();
        setupSkin();
        setupAdventureButton();
        setupPartyManagementButton();
        setupBackButton();
        setupShopButton();
    }

    private void setupMusic() {
        DankMusic.playMusic("music/night_hours.mp3");
    }

    private void setupShopButton() {
        shopButton = new TextButton("Shop", skin, "orange_yellow_fat");

        shopButton.setWidth(200);
        shopButton.setHeight(70);

        shopButton.setX(400);
        shopButton.setY(155);

        shopButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new ShopMenu(game));
            }
        });

        table.addActor(shopButton);
    }

    private void setupBackButton() {
        backButton = new TextButton("Back", skin, "orange_yellow_fat");

        backButton.setWidth(200);
        backButton.setHeight(70);

        backButton.setX(400);
        backButton.setY(55);

        backButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenu(game));
            }
        });

        table.addActor(backButton);
    }

    private void setupPartyManagementButton() {
        partyManagementButton = new TextButton("Party\nManagement", skin, "orange_yellow_fat");

        partyManagementButton.setWidth(200);
        partyManagementButton.setHeight(70);

        partyManagementButton.setX(400);
        partyManagementButton.setY(255);

        partyManagementButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new HeroManagementMenu(game));
            }
        });

        table.addActor(partyManagementButton);
    }

    private void setupAdventureButton() {
        adventureButton = new TextButton("Adventure", skin, "orange_yellow_fat");

        adventureButton.setWidth(200);
        adventureButton.setHeight(70);

        adventureButton.setX(400);
        adventureButton.setY(355);

        adventureButton.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new AdventureMenu(game));
            }
        });

        table.addActor(adventureButton);
    }

    private void setupSkin() {
        skin = Assets.manager.get("skins/rainbowpack.json", Skin.class);
    }

    private void setupBackground() {
        backgroundImage = new Image(new TextureRegion(new Texture(Gdx.files.internal("backgrounds/Tower1.png"))));
        table.addActor(backgroundImage);
    }

    private void setupTable() {
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
    }

    private void setupStage() {
        stage = new Stage(new FitViewport(640,480));
        Gdx.input.setInputProcessor(stage);
    }

    private void loadAssets() {
        Assets.loadMainMenu();
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