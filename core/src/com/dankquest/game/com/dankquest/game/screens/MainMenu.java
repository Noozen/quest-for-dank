package com.dankquest.game.com.dankquest.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenu extends BasicScreen {

    private float timer = 0;
    private int cameraCountdown = 0;

    private Stage stage;
    private Table table;

    private Image backgroundImage;

    FreeTypeFontGenerator freeTypeFontGenerator;
    FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameter;

    BitmapFont buttonFont;

    private TextButton.TextButtonStyle buttonStyle;
    private TextButton playButton;
    private TextButton settingsButton;
    private TextButton exitButton;

    Music relaxingMusic;

    public MainMenu(Game game) {
        super(game);
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(640,480));
        stage.getCamera().far = 500f;
        stage.getCamera().near = 10f;
        stage.getCamera().position.set(320, 240, 100);
        stage.getCamera().lookAt(320, 240, 0);
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        //Music Setup
        relaxingMusic = Gdx.audio.newMusic(Gdx.files.internal("music/cool_music.mp3"));
        relaxingMusic.setVolume(0.2f);
        relaxingMusic.setLooping(true);
        relaxingMusic.play();

        //Background Setup
        backgroundImage = new Image(new TextureRegion(new Texture(Gdx.files.internal("main_menu/main_menu_background.png"))));
        backgroundImage.setX(-80);
        backgroundImage.setY(-60);

        table.addActor(backgroundImage);

        //Font Setup
        freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/dank.ttf"));
        freeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameter.size = 70;
        freeTypeFontParameter.color = Color.RED;
        buttonFont = freeTypeFontGenerator.generateFont(freeTypeFontParameter);

        //Button Style Setup
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = buttonFont;
        //Play Button Setup
        playButton = new TextButton("PLAY", buttonStyle);

        playButton.setX(370);
        playButton.setY(Gdx.graphics.getHeight() * 3 / 4 - playButton.getHeight()/2 - 30);

        playButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                freeTypeFontParameter.color = Color.BLUE;
                buttonFont = freeTypeFontGenerator.generateFont(freeTypeFontParameter);
                buttonStyle.font = buttonFont;
                playButton.setStyle(buttonStyle);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                relaxingMusic.stop();
                game.setScreen(new GameMenu(game));
            }
        });

        table.addActor(playButton);

        //Settings Button Setup
        settingsButton = new TextButton("OPTIONS", buttonStyle);

        settingsButton.setX(320);
        settingsButton.setY(Gdx.graphics.getHeight() * 2 / 4 - playButton.getHeight()/2 - 30);

        settingsButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                freeTypeFontParameter.color = Color.BLUE;
                buttonFont = freeTypeFontGenerator.generateFont(freeTypeFontParameter);
                buttonStyle.font = buttonFont;
                settingsButton.setStyle(buttonStyle);
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                relaxingMusic.stop();
                game.setScreen(new SettingsMenu(game));
            }
        });

        table.addActor(settingsButton);

        //Exit Button Setup
        exitButton = new TextButton("EXIT", buttonStyle);

        exitButton.setX(370);
        exitButton.setY(Gdx.graphics.getHeight() / 4 - playButton.getHeight()/2 - 30);

        exitButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });

        table.addActor(exitButton);
    }

    @Override
    public void render(float delta) {
        timer+=delta;
        if(timer>=0.46875f) {
            timer -= 0.46875f;
            cameraCountdown = 20;
        }
        if(cameraCountdown>0) {
            if(cameraCountdown>4) {
                stage.getCamera().position.y+=1;
                stage.getCamera().lookAt(320,240,0);
                stage.getCamera().update();
            }
            if(cameraCountdown<=4) {
                stage.getCamera().position.y-=4;
                stage.getCamera().lookAt(320,240,0);
                stage.getCamera().update();
            }
            if(cameraCountdown == 1) {
                stage.getCamera().lookAt(320, 240, 0);
                stage.getCamera().up.set(0,1,0);
                stage.getCamera().update();
            }
            cameraCountdown--;
        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void hide() {
        stage.dispose();
        freeTypeFontGenerator.dispose();
    }

    @Override
    public void resize(int width, int heigth) {
        stage.getViewport().update(width, heigth);
    }
}