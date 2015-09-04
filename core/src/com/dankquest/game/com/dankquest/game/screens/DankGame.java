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
import com.dankquest.game.com.dankquest.game.actors.HeroActor;
import com.dankquest.game.com.dankquest.game.actors.SkillActor;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.logic.Hero;
import com.dankquest.game.com.dankquest.game.logic.HeroClass;
import com.dankquest.game.com.dankquest.game.logic.skill.ArrowSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.FireballSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.SlamSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.TwinBladeSkill;

import java.awt.*;
import java.util.Comparator;

/**
 * Created by Antah on 2015/09/02.
 */
public class DankGame extends BasicScreen {
    private Stage stage;
    private Table table;

    private Music battleMusic;

    private Image backgroundImage, backgroundImage1;

    private Skin skin;

    private TextButton retreatButton;

    private SkillActor skill1, skill2, skill3, skill4;
    private HeroActor hero1, hero2, hero3, hero4;

    public DankGame(Game game) {
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
        battleMusic = Gdx.audio.newMusic(Gdx.files.internal("music/Battle2.ogg"));
        battleMusic.setVolume(0.2f);
        battleMusic.setLooping(true);
        battleMusic.play();

        //Background Setup
        backgroundImage = new Image(new TextureRegion(new Texture(Gdx.files.internal("backgrounds/Castle.png"))));
        backgroundImage1 = new Image(new TextureRegion(new Texture(Gdx.files.internal("backgrounds/Castle1.png"))));
        table.addActor(backgroundImage);
        table.addActor(backgroundImage1);

        //Skin setup
        skin = new Skin(Gdx.files.internal("skins/rainbowpack.json"),
                new TextureAtlas(Gdx.files.internal("skins/rainbowpack.pack")));

        //Retreat Button Setup
        retreatButton = new TextButton("Retreat", skin, "orange_yellow_fat");

        retreatButton.setWidth(130);
        retreatButton.setHeight(60);

        retreatButton.setX(0);
        retreatButton.setY(420);

        retreatButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                battleMusic.stop();
                game.setScreen(new GameMenu(game));
            }
        });

        table.addActor(retreatButton);

        //Active hero
        Dank.activeHero = Dank.chosenHeroesList.get(0);

        //Skill buttons setup
        skill1 = new SkillActor(440,0,1);
        skill2 = new SkillActor(490,0,2);
        skill3 = new SkillActor(540,0,3);
        skill4 = new SkillActor(590,0,4);
        skill1.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
        skill2.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
        skill3.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
        skill4.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }
        });
        table.addActor(skill1);
        table.addActor(skill2);
        table.addActor(skill3);
        table.addActor(skill4);

        //Heroes setup
        hero1 = new HeroActor(180,200,1);
        hero2 = new HeroActor(200,250,2);
        hero3 = new HeroActor(220,300,3);
        hero4 = new HeroActor(240,350,4);
        table.addActor(hero4);
        table.addActor(hero3);
        table.addActor(hero2);
        table.addActor(hero1);

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