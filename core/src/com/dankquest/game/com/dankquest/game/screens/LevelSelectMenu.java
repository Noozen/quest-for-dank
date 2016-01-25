package com.dankquest.game.com.dankquest.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.util.LevelFactory;
import com.dankquest.game.com.dankquest.game.util.Assets;
import com.dankquest.game.com.dankquest.game.util.DankMusic;

/**
 * Created by Antah on 2015/09/02.
 */
public class LevelSelectMenu extends BasicScreen {

    private Stage stage;
    private Table table, ownedHeroesTable;

    private Image backgroundImage;

    private Skin skin;

    private TextButton backButton;
    private TextButton playButton;
    private TextButton level1, level2, level3;

    private boolean levelSelected;

    public LevelSelectMenu(Game game) {
        super(game);
        levelSelected = false;
    }

    @Override
    public void show() {
        loadAssets();
        setupStage();
        setupTable();
        musicSetup();
        setupBackground();
        setupSkin();
        setupBackButton();
        setupPlayButton();
        setupLevelButtons();
    }
    private void setupLevelButtons() {
        level1 = new TextButton("Level 1", skin, "orange_yellow_fat");
        level2 = new TextButton("Level 2", skin, "orange_yellow_fat");
        level3 = new TextButton("Level 3", skin, "orange_yellow_fat");

        level1.setWidth(144);
        level1.setHeight(96);
        level2.setWidth(144);
        level2.setHeight(96);
        level3.setWidth(144);
        level3.setHeight(96);

        level1.setX(52);
        level1.setY(374);
        level2.setX(248);
        level2.setY(374);
        level3.setX(444);
        level3.setY(374);

        level1.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                levelSelected = true;
                LevelFactory.setEnemyHeroList(1);
            }
        });
        level2.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                levelSelected = true;
                LevelFactory.setEnemyHeroList(2);
            }
        });
        level3.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                levelSelected = true;
                LevelFactory.setEnemyHeroList(3);
            }
        });

        table.addActor(level1);
        table.addActor(level2);
        table.addActor(level3);
    }

    private void setupPlayButton() {
        playButton = new TextButton("Play", skin, "orange_yellow_fat");

        playButton.setWidth(96);
        playButton.setHeight(96);

        playButton.setX(537);
        playButton.setY(20);

        playButton.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (Dank.chosenHeroesList.size() == 4) {
                    if(levelSelected)
                    game.setScreen(new DankGame(game));
                }
            }
        });

        table.addActor(playButton);
    }

    private void setupBackButton() {
        backButton = new TextButton("Back", skin, "orange_yellow_fat");

        backButton.setWidth(96);
        backButton.setHeight(96);

        backButton.setX(7);
        backButton.setY(20);

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

    private void setupSkin() {
        skin = Assets.manager.get("skins/rainbowpack.json", Skin.class);
    }

    private void setupBackground() {
        backgroundImage = new Image(new TextureRegion(new Texture(Gdx.files.internal("backgrounds/Tower1.png"))));
        table.addActor(backgroundImage);
    }

    private void musicSetup() {
        DankMusic.changeSoundtrack("music/night_hours.mp3");
    }

    private void setupTable() {
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        ownedHeroesTable = new Table();
        ownedHeroesTable.setFillParent(true);
        stage.addActor(ownedHeroesTable);
    }

    private void setupStage() {
        stage = new Stage(new FitViewport(640,480));
        Gdx.input.setInputProcessor(stage);
    }

    private void loadAssets() {
        Assets.loadMainMenu();
        Assets.loadPortraits();
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