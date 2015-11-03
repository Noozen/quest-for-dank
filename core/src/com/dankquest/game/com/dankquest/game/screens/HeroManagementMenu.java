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
import com.dankquest.game.com.dankquest.game.util.Assets;

/**
 * Created by Antah on 2015/09/02.
 */
public class HeroManagementMenu extends BasicScreen {
    public HeroManagementMenu(Game game) {
        super(game);
    }
    public TextButton backButton;private Stage stage;
    private Table table;
    private com.badlogic.gdx.scenes.scene2d.ui.Image backgroundImage;
    private Skin skin;

    @Override
    public void show() {
        loadAssets();
        setupStage();
        setupTable();
        setupBackground();
        setupSkin();
        setupBackButton();
    }
    private void setupBackButton() {
        backButton = new TextButton("Back", skin, "orange_yellow_fat");

        backButton.setWidth(100);
        backButton.setHeight(70);

        backButton.setX(0);
        backButton.setY(0);

        backButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameMenu(game));
                return true;
            }
        });
        table.addActor(backButton);
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
    private void loadAssets() {
        Assets.loadMainMenu();
    }

    private void setupTable() {
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void hide() {

    }
}