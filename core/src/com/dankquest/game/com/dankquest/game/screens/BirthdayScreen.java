package com.dankquest.game.com.dankquest.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.util.Assets;
import com.dankquest.game.com.dankquest.game.util.DankMusic;

import java.util.*;
import java.util.List;

/**
 * Created by Antah on 2015/12/26.
 */
public class BirthdayScreen extends BasicScreen {
    public BirthdayScreen(Game game) {
        super(game);
    }
    private Stage stage;
    private Table table;
    private Image backgroundImage;
    private Skin skin;
    private List<ParticleEffect> particle = new ArrayList<ParticleEffect>();
    private SpriteBatch batch;
    private float TIME;
    private String[] zyczenia = new String[12];
    private TextButton playButton;
    private TextButton toLeftButton;
    private TextButton toRightButton;
    private int page;
    private boolean particlesSpam;

    @Override
    public void show() {
        loadAssets();
        setupMusic();
        setupStage();
        setupTable();
        setupBackground();
        setupSkin();
        setupZyczenia();
        setupPlayButton();
        setupArrowButtons();
        batch = new SpriteBatch();
        TIME = 0;
        particlesSpam = true;
    }

    private void setupZyczenia() {
        zyczenia[0] = "Wszystkiego najlepszego!\n" +
                "Standardowo szczęścia,\n" +
                "zdrowia, pomyślności\n" +
                "i tak dalej.\n" +
                "(naciskaj szczałki)";

        zyczenia[1] = "A tak personalnie,\n" +
                "to mam nadzieję,\n" +
                "że miło spędziłaś\n" +
                "święta z rodziną.";

        zyczenia[2] = "Do tego życzę Ci,\n" +
                "żeby dalej dobrze\n" +
                "japoński szedł.";

        zyczenia[3] = "Żeby wykładowcy nie\n" +
                "męczyli zbytnio.";

        zyczenia[4] = "Żeby projekty\n" +
                "łatwo szły.";

        zyczenia[5] = "Oczywiście masy\n" +
                "słodyczy też.";

        zyczenia[6] = "A tak samolubnie\n" +
                "trochę, to życzę Ci\n" +
                "więcej wolnego czasu.\n" +
                "(w tym dla mnie :()";

        zyczenia[7] = "I nie zmieniaj się.\n" +
                "Przynajmniej nie za dużo.";

        zyczenia[8] = "To już wszystko.\n" +
                "Miłego wypoczywania\n" +
                "i udanego Sylwestra!";
    }

    private void setupMusic() {
        DankMusic.changeSoundtrack("music/Minions Happy Birthday Song.mp3");
        DankMusic.setSoundtrackLooping(false);
    }

    private void loadAssets() {
        Assets.loadMainMenu();
        Assets.load("backgrounds/birthdayBackground.jpg", Texture.class);
    }

    private void setupTable() {
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
    }

    private void setupPlayButton() {
        page = 0;
        playButton = new TextButton(zyczenia[page], skin, "birthday");

        playButton.setWidth(380);
        playButton.setHeight(200);

        playButton.setX(130);
        playButton.setY(10);

        playButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        playButton.setColor(1f,1f,1f,0f);
        playButton.addAction(Actions.fadeIn(6f));
        table.addActor(playButton);
    }
    private void setupArrowButtons() {
        toLeftButton = new TextButton("<-", skin, "orange_yellow_fat");

        toLeftButton.setWidth(64);
        toLeftButton.setHeight(64);

        toLeftButton.setX(10);
        toLeftButton.setY(10);

        toLeftButton.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(page <= 0){
                    return;
                }
                page--;
                playButton.setText(zyczenia[page]);
            }
        });
        toLeftButton.setColor(1f,1f,1f,0f);
        toLeftButton.addAction(Actions.fadeIn(6f));
        table.addActor(toLeftButton);

        toRightButton = new TextButton("->", skin, "orange_yellow_fat");

        toRightButton.setWidth(64);
        toRightButton.setHeight(64);

        toRightButton.setX(566);
        toRightButton.setY(10);

        toRightButton.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(page >= 8){
                    return;
                }
                page++;
                playButton.setText(zyczenia[page]);
            }
        });
        toRightButton.setColor(1f,1f,1f,0f);
        toRightButton.addAction(Actions.fadeIn(6f));
        table.addActor(toRightButton);
    }

    private void setupSkin() {
        skin = Assets.manager.get("skins/rainbowpack.json", Skin.class);
    }

    private void setupBackground() {
        backgroundImage = new Image(Assets.manager.get("backgrounds/birthdayBackground.jpg", Texture.class));
        table.addActor(backgroundImage);
    }

    private void setupStage() {
        stage = new Stage(new FitViewport(640,480));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        batch.begin();
        if(particlesSpam == true && (TIME += delta) >= 0.1f){
            TIME = 0;
            addNewParticle();
        }
        Iterator<ParticleEffect> i = particle.iterator();
        while (i.hasNext()) {
            ParticleEffect p = i.next();
            p.draw(batch, delta);
            if(p.isComplete()) {
                i.remove();
            }
        }
        batch.end();
        playButton.act(delta);
        toRightButton.act(delta);
        toLeftButton.act(delta);
        if(!DankMusic.isSoundtrackPlaying()){
            DankMusic.changeSoundtrack("music/Hitomi wo Tojite #3.mp3");
            DankMusic.setSoundtrackLooping(true);
            particlesSpam = false;
        }
    }

    private void addNewParticle() {
        ParticleEffect tmp = new ParticleEffect();
        tmp.load(Gdx.files.internal("effects/pink2.p"), Gdx.files.internal("effects"));
        Random generator = new Random();
        int x = generator.nextInt(640) + 1;
        int y = generator.nextInt(480) + 1;
        tmp.setPosition(x, y);
        float t =  generator.nextFloat() * 1f + 0.5f;
        tmp.start();
        particle.add(tmp);
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
