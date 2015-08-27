package com.dankquest.game.com.dankquest.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MainMenu extends BasicScreen {

    private Stage stage;
    private Table table;
    private TextButton johnCenaButton;
    private TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
    private Music johnCenaMusic;
    private Music relaxingMusic;

    private TextureRegion buttonWhenNotPressed;
    private TextureRegion buttonWhenPressed;

    public MainMenu(Game game) {
        super(game);
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        buttonWhenNotPressed = new TextureRegion(new Texture(Gdx.files.internal("game_screen.png")));
        buttonWhenPressed = new TextureRegion(new Texture(Gdx.files.internal("game_screen.png")));
        BitmapFont buttonFont = new BitmapFont();
        johnCenaMusic = Gdx.audio.newMusic(Gdx.files.internal("john_cena.mp3"));
        johnCenaMusic.setVolume(1f);
        relaxingMusic = Gdx.audio.newMusic(Gdx.files.internal("relaxing_music.mp3"));
        relaxingMusic.setVolume(0.2f);
        relaxingMusic.play();

        buttonStyle.up = new TextureRegionDrawable(buttonWhenNotPressed);
        buttonStyle.down = new TextureRegionDrawable(buttonWhenPressed);
        buttonStyle.font = buttonFont;

        johnCenaButton = new TextButton("", buttonStyle);

        johnCenaButton.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(relaxingMusic.isPlaying()) {
                    relaxingMusic.stop();
                }
                johnCenaMusic.play();

                buttonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("john_cena.jpg")));
                buttonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture("john_cena.jpg")));
                johnCenaButton.setStyle(buttonStyle);

                System.out.println("LPM down.");
                return true;
            }
        });

        table.addActor(johnCenaButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(johnCenaMusic.isPlaying() == false && relaxingMusic.isPlaying() == false) {
            buttonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("game_screen.png")));
            buttonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture("game_screen.png")));
            johnCenaButton.setStyle(buttonStyle);
            relaxingMusic.play();
        }
        stage.draw();
    }

    @Override
    public void hide() {
        //Pamietamy ¿e na wszystkimi trzeba wywolywac .dispose()
    }
}