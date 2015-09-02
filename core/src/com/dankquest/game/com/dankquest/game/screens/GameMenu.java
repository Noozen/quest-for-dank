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

public class GameMenu extends BasicScreen {

    private Stage stage;
    private Table table;

    Music relaxingMusic = Gdx.audio.newMusic(Gdx.files.internal("music/night_hours.mp3"));

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
        adventureButton = new TextButton("Adventure", skin, "orange_yellow_fat");

        adventureButton.setWidth(240);
        adventureButton.setHeight(140);

        adventureButton.setX(340);
        adventureButton.setY(80);

        adventureButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                relaxingMusic.stop();
                game.setScreen(new AdventureMenu(game));
            }
        });

        table.addActor(adventureButton);

        //partyManagment Button Setup
        partyManagementButton = new TextButton("Party\nManagement", skin, "orange_yellow_fat");

        partyManagementButton.setWidth(240);
        partyManagementButton.setHeight(140);

        partyManagementButton.setX(340);
        partyManagementButton.setY(260);

        partyManagementButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                relaxingMusic.stop();
                game.setScreen(new PartyManagementMenu(game));
            }
        });

        table.addActor(partyManagementButton);

        //Back Button Setup
        backButton = new TextButton("Back", skin, "orange_yellow_fat");

        backButton.setWidth(240);
        backButton.setHeight(140);

        backButton.setX(60);
        backButton.setY(80);

        backButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                relaxingMusic.stop();
                game.setScreen(new MainMenu(game));
            }
        });

        table.addActor(backButton);

        //Shop Button Setup
        shopButton = new TextButton("Shop", skin, "orange_yellow_fat");

        shopButton.setWidth(240);
        shopButton.setHeight(140);

        shopButton.setX(60);
        shopButton.setY(260);

        shopButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                relaxingMusic.stop();
                game.setScreen(new ShopMenu(game));
            }
        });

        table.addActor(shopButton);
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