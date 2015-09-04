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
import com.dankquest.game.com.dankquest.game.actors.OwnedHeroesActor;
import com.dankquest.game.com.dankquest.game.actors.ChosenHeroesActor;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.logic.Hero;

import java.util.Comparator;

/**
 * Created by Antah on 2015/09/02.
 */
public class AdventureMenu extends BasicScreen {

    private Stage stage;
    private Table table;

    private Music relaxingMusic;

    private Image backgroundImage;

    private Skin skin;

    private TextButton backButton;
    private TextButton playButton;
    private TextButton toLeftCharacterButton;
    private TextButton toRightCharacterButton;

    private OwnedHeroesActor ownedHeroesActor = new OwnedHeroesActor();
    private ChosenHeroesActor chosenHeroesActor = new ChosenHeroesActor();

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
        relaxingMusic = Gdx.audio.newMusic(Gdx.files.internal("music/night_hours.mp3"));
        relaxingMusic.setVolume(0.2f);
        relaxingMusic.setLooping(true);
        relaxingMusic.play();

        //Background Setup
        backgroundImage = new Image(new TextureRegion(new Texture(Gdx.files.internal("backgrounds/Tower1.png"))));
        table.addActor(backgroundImage);

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

        //toLeftCharacter Button Setup
        toLeftCharacterButton = new TextButton("<-", skin, "orange_yellow_fat");

        toLeftCharacterButton.setWidth(100);
        toLeftCharacterButton.setHeight(350);

        toLeftCharacterButton.setX(10);
        toLeftCharacterButton.setY(120);

        toLeftCharacterButton.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ownedHeroesActor.heroDecreased();
            }
        });

        table.addActor(toLeftCharacterButton);

        //toRightCharacter Button Setup
        toRightCharacterButton = new TextButton("->", skin, "orange_yellow_fat");

        toRightCharacterButton.setWidth(100);
        toRightCharacterButton.setHeight(350);

        toRightCharacterButton.setX(530);
        toRightCharacterButton.setY(120);

        toRightCharacterButton.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ownedHeroesActor.heroIncreased();
            }
        });

        table.addActor(toRightCharacterButton);

        //ownedHeroesActor
        ownedHeroesActor.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(Dank.chosenHeroesList.size()<4 && Dank.ownedHeroesList.size()> ownedHeroesActor.currentHero + Math.round(x) / 100) {
                    Dank.chosenHeroesList.add(Dank.ownedHeroesList.remove(ownedHeroesActor.currentHero + Math.round(x) / 100));
                    ownedHeroesActor.update();
                    chosenHeroesActor.update();

                    Dank.ownedHeroesList.sort(new Comparator<Hero>() {

                        @Override
                        public int compare(Hero o1, Hero o2) {
                            return o1.name.compareTo(o2.name);
                        }
                    });
                }
            }
        });

        table.addActor(ownedHeroesActor);

        //chosenHeroesActor
        chosenHeroesActor.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(Dank.chosenHeroesList.size()> Math.round(x) / 100) {
                    Dank.ownedHeroesList.add(Dank.chosenHeroesList.remove(Math.round(x) / 100));
                    ownedHeroesActor.update();
                    chosenHeroesActor.update();

                    Dank.ownedHeroesList.sort(new Comparator<Hero>() {

                        @Override
                        public int compare(Hero o1, Hero o2) {
                            return o1.name.compareTo(o2.name);
                        }
                    });

                }
            }
        });

        table.addActor(chosenHeroesActor);
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

    @Override
    public void resize(int width, int heigth) {
        stage.getViewport().update(width, heigth);
    }
}