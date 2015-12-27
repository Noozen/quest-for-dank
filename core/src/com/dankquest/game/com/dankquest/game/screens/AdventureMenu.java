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
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dankquest.game.com.dankquest.game.actors.*;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.logic.Hero;
import com.dankquest.game.com.dankquest.game.util.Assets;
import com.dankquest.game.com.dankquest.game.util.DankMusic;
import com.dankquest.game.com.dankquest.game.util.DankUtil;
import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Antah on 2015/09/02.
 */
public class AdventureMenu extends BasicScreen {

    private Stage stage;
    private Table table, ownedHeroesTable;

    private Image backgroundImage;

    private Skin skin;

    private TextButton backButton;
    private TextButton playButton;
    private TextButton toLeftCharacterButton;
    private TextButton toRightCharacterButton;

    private OwnedHeroesActor ownedHeroesActor = new OwnedHeroesActor();
    private List<PortraitActor> ownedHeroesActorList = new ArrayList<PortraitActor>();
    private List<PortraitActor> chosenHeroesActorList = new ArrayList<PortraitActor>();
    private int ownedHeroIndex;


    public AdventureMenu(Game game) {
        super(game);
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
        setupArrowButtons();
        setupOwnedHeroesActor();
        setupChosenHeroesActor();
    }

    private void setupChosenHeroesActor() {
        for (int i=0; i < 4; i++) {
            ChosenHeroPortraitActor tmp = new ChosenHeroPortraitActor(this);
            tmp.setX(113 + i * 106);
            tmp.setY(20);
            tmp.update();
            chosenHeroesActorList.add(tmp);
        }
        for (PortraitActor p: chosenHeroesActorList) {
            table.addActor(p);
        }
    }

    public void updateChosenHeroesActorList(){
        int i = 0;
        for(Hero h: Dank.chosenHeroesList){
            chosenHeroesActorList.get(i).setHero(h);
            chosenHeroesActorList.get(i).update();
            i++;
        }
        for(int j = i; i < 4; i++){
            chosenHeroesActorList.get(j).setHero(null);
            chosenHeroesActorList.get(j).update();
        }
    }

    private void setupOwnedHeroesActor() {
        ownedHeroIndex = 0;
        for (Hero h:Dank.ownedHeroesList) {
            OwnedHeroPortraitActor tmp = new OwnedHeroPortraitActor(h, this);
            ownedHeroesActorList.add(tmp);
        }
        updateOwnedHeroesActorTable();
    }

    private void setupArrowButtons() {
        toLeftCharacterButton = new TextButton("<-", skin, "orange_yellow_fat");

        toLeftCharacterButton.setWidth(128);
        toLeftCharacterButton.setHeight(64);

        toLeftCharacterButton.setX(7);
        toLeftCharacterButton.setY(250);

        toLeftCharacterButton.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(ownedHeroIndex <=0){
                    return;
                }
                ownedHeroIndex--;
                updateOwnedHeroesActorTable();
            }
        });

        table.addActor(toLeftCharacterButton);

        toRightCharacterButton = new TextButton("->", skin, "orange_yellow_fat");

        toRightCharacterButton.setWidth(128);
        toRightCharacterButton.setHeight(64);

        toRightCharacterButton.setX(505);
        toRightCharacterButton.setY(250);

        toRightCharacterButton.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(ownedHeroIndex >= ownedHeroesActorList.size()-5){
                    return;
                }
                ownedHeroIndex++;
                updateOwnedHeroesActorTable();
            }
        });

        table.addActor(toRightCharacterButton);
    }

    private void updateOwnedHeroesActorTable() {
        ownedHeroesTable.clear();
        for(int i=ownedHeroIndex; i < ownedHeroIndex+6; i++){
            if(ownedHeroesActorList.size() <= i){
                continue;
            }
            ownedHeroesActorList.get(i).setX(166 + ((i/2)%3) * 106);
            ownedHeroesActorList.get(i).setY(200 + ((i+1)%2) * 106);
            ownedHeroesActorList.get(i).update();
            ownedHeroesTable.addActor(ownedHeroesActorList.get(i));
        }
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
                game.setScreen(new GameMenu(game));
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