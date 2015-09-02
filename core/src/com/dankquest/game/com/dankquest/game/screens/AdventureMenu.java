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

/**
 * Created by Antah on 2015/09/02.
 */
public class AdventureMenu extends BasicScreen {
    private Stage stage;
    private Table table;

    Music relaxingMusic = Gdx.audio.newMusic(Gdx.files.internal("music/night_hours.mp3"));

    private Image backgroundImage;
    private Image placeholderHeroImage;

    Skin skin;

    private TextButton backButton;
    private TextButton playButton;

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

        //Placeholder Hero Image
        placeholderHeroImage = new Image(new TextureRegion(new Texture(Gdx.files.internal("adventure_menu/character_placeholder.png"))));
        placeholderHeroImage.setX(120);
        placeholderHeroImage.setY(10);
        table.addActor(placeholderHeroImage);
        //Skin setup
        skin = new Skin(Gdx.files.internal("skins/rainbowpack.json"),
                new TextureAtlas(Gdx.files.internal("skins/rainbowpack.pack")));

        //Back Button Setup
        backButton = new TextButton("Back", skin, "orange_yellow_fat");

        backButton.setWidth(100);
        backButton.setHeight(100);

        backButton.setX(10);
        backButton.setY(10);

        backButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                relaxingMusic.stop();
                game.setScreen(new GameMenu(game));
            }
        });

        table.addActor(backButton);

        //Play Button Setup
        playButton = new TextButton("Play", skin, "orange_yellow_fat");

        playButton.setWidth(100);
        playButton.setHeight(100);

        playButton.setX(530);
        playButton.setY(10);

        playButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                relaxingMusic.stop();
                game.setScreen(new DankGame(game));
            }
        });

        table.addActor(playButton);
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