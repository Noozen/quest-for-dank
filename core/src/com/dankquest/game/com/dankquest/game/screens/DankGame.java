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
import com.dankquest.game.com.dankquest.game.actors.PortraitActor;
import com.dankquest.game.com.dankquest.game.actors.SkillActor;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.logic.Hero;

import java.util.ArrayList;
import java.util.List;

import java.util.Comparator;

/**
 * Created by Antah on 2015/09/02.
 */
public class DankGame extends BasicScreen {
    private Stage stage;
    private Table table;
    //Our beautiful assets
    private Music battleMusic;
    private Image backgroundFloorImage, backgroundWallImage;
    //UI
    private Skin skin;
    private TextButton retreatButton, castButton, passButton;
    private PortraitActor portraitActor;
    private SkillActor skill1, skill2, skill3, skill4;
    //Heroes
    private HeroActor hero1, hero2, hero3, hero4;
    private HeroActor enemy1, enemy2, enemy3, enemy4;
    //Targeting
    private int skillCast = 0;
    private boolean targetMode = false;
    private List<Hero> targetList = new ArrayList<Hero>();
    //Pass mechanic
    private List<Hero> passHeroesList = new ArrayList<Hero>();
    private boolean passPhase = false;


    public DankGame(Game game) {
        super(game);
    }


    @Override
    public void show() {
        heroesListSetup();
        stageSetup();
        musicSetup();
        backgroundSetup();
        uiSetup();
        ifComputerBegins();
        update();
    }

    private void heroesListSetup() {
        Dank.allHeroesInGameList.addAll(Dank.chosenHeroesList);
        Dank.allHeroesInGameList.addAll(Dank.enemyHeroesList);
        Dank.allHeroesInGameList.sort((new Comparator<Hero>() {

            @Override
            public int compare(Hero o1, Hero o2) {
                if (o1.initiative < o2.initiative)
                    return 1;
                if (o1.initiative == o2.initiative)
                    return 0;
                if (o1.initiative > o2.initiative)
                    return -1;
                return -2; //Error
            }
        }));
        Dank.thisTurnLeftHeroesList.addAll(Dank.allHeroesInGameList);
        Dank.activeHero = Dank.allHeroesInGameList.get(0);
    }
    private void stageSetup() {
        stage = new Stage(new FitViewport(640, 480));
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

    }
    private void musicSetup() {
        battleMusic = Gdx.audio.newMusic(Gdx.files.internal("music/Battle2.ogg"));
        battleMusic.setVolume(0.2f);
        battleMusic.setLooping(true);
        battleMusic.play();
    }
    private void backgroundSetup() {
        //Background Setup
        backgroundFloorImage = new Image(new TextureRegion(new Texture(Gdx.files.internal("backgrounds/Castle.png"))));
        backgroundWallImage = new Image(new TextureRegion(new Texture(Gdx.files.internal("backgrounds/Castle1.png"))));
        table.addActor(backgroundFloorImage);
        table.addActor(backgroundWallImage);
    }

    private void uiSetup() {
        //Skin setup
        skin = new Skin(Gdx.files.internal("skins/rainbowpack.json"),
                new TextureAtlas(Gdx.files.internal("skins/rainbowpack.pack")));

        retreatButtonSetup();
        castButtonSetup();
        passButtonSetup();
        skillsSetup();
        heroesSetup();
        enemiesSetup();

        //Portrait Actor
        portraitActor = new PortraitActor();
        table.addActor(portraitActor);
    }
    private void retreatButtonSetup() {
        retreatButton = new TextButton("Babyrage", skin, "orange_yellow_fat");

        retreatButton.setWidth(150);
        retreatButton.setHeight(50);

        retreatButton.setX(10);
        retreatButton.setY(420);

        retreatButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                battleMusic.stop();
                resetHealthAndStuff();
                game.setScreen(new GameMenu(game));
            }
        });

        table.addActor(retreatButton);
    }
    private void passButtonSetup() {
        passButton = new TextButton("Pass", skin, "orange_yellow_fat");

        passButton.setWidth(50);
        passButton.setHeight(50);

        passButton.setX(580);
        passButton.setY(420);

        passButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(passPhase == true){
                    return;
                }
                passHeroesList.add(Dank.activeHero);
                checkIfGameEnded();
                clearTargetsAndSortHeroList();
                checkEnemyTurn();
                update();
            }
        });

        table.addActor(passButton);
    }
    private void castButtonSetup() {
        castButton = new TextButton("Cast", skin, "orange_yellow_fat");

        castButton.setWidth(100);
        castButton.setHeight(100);

        castButton.setX(530);
        castButton.setY(10);

        castButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (targetMode == false)
                    return;
                if (Dank.chosenHeroesList.contains(Dank.activeHero)) {
                    Dank.activeHero.getSkill(skillCast).cast(targetList);
                    targetMode = false;
                    checkIfGameEnded();
                    clearTargetsAndSortHeroList();
                }
                ifComputerBegins();
                update();
            }
        });
        table.addActor(castButton);
    }
    private void skillsSetup() {
        skill1 = new SkillActor(200, 10, 1);
        skill2 = new SkillActor(260, 10, 2);
        skill3 = new SkillActor(320, 10, 3);
        skill4 = new SkillActor(380, 10, 4);
        skill1.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (targetMode == true) {
                    if (skillCast == 1) {
                        targetMode = false;
                        targetList.clear();
                    }
                    return;
                }
                skillCast = 1;
                targetMode = true;
                targetList.add(Dank.activeHero);
            }
        });
        skill2.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (targetMode == true) {
                    if (skillCast == 2) {
                        targetMode = false;
                        targetList.clear();
                    }
                    return;
                }
                skillCast = 2;
                targetMode = true;
                targetList.add(Dank.activeHero);
            }
        });
        skill3.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (targetMode == true) {
                    if (skillCast == 3) {
                        targetMode = false;
                        targetList.clear();
                    }
                    return;
                }
                skillCast = 3;
                targetMode = true;
                targetList.add(Dank.activeHero);
            }
        });
        skill4.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (targetMode == true) {
                    if (skillCast == 4) {
                        targetMode = false;
                        targetList.clear();
                    }
                    return;
                }
                skillCast = 4;
                targetMode = true;
                targetList.add(Dank.activeHero);
            }
        });
        table.addActor(skill1);
        table.addActor(skill2);
        table.addActor(skill3);
        table.addActor(skill4);
    }
    private void heroesSetup() {
        hero1 = new HeroActor(10, 120, 1, true);
        hero2 = new HeroActor(60, 180, 2, true);
        hero3 = new HeroActor(110, 240, 3, true);
        hero4 = new HeroActor(160, 300, 4, true);
        hero1.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (targetMode == false)
                    return;
                targetList.add(Dank.chosenHeroesList.get(0));
            }
        });
        hero2.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (targetMode == false)
                    return;
                targetList.add(Dank.chosenHeroesList.get(1));
            }
        });
        hero3.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (targetMode == false)
                    return;
                targetList.add(Dank.chosenHeroesList.get(2));
            }
        });
        hero4.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (targetMode == false)
                    return;
                targetList.add(Dank.chosenHeroesList.get(3));
            }
        });
        table.addActor(hero4);
        table.addActor(hero3);
        table.addActor(hero2);
        table.addActor(hero1);
    }
    private void enemiesSetup() {
        enemy1 = new HeroActor(566, 120, 1, false);
        enemy2 = new HeroActor(516, 180, 2, false);
        enemy3 = new HeroActor(466, 240, 3, false);
        enemy4 = new HeroActor(416, 300, 4, false);
        enemy1.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (targetMode == false)
                    return;
                targetList.add(Dank.enemyHeroesList.get(0));
            }
        });
        enemy2.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (targetMode == false)
                    return;
                targetList.add(Dank.enemyHeroesList.get(1));
            }
        });
        enemy3.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (targetMode == false)
                    return;
                targetList.add(Dank.enemyHeroesList.get(2));
            }
        });
        enemy4.addListener(new InputListener() {

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (targetMode == false)
                    return;
                targetList.add(Dank.enemyHeroesList.get(3));
            }
        });
        table.addActor(enemy4);
        table.addActor(enemy3);
        table.addActor(enemy2);
        table.addActor(enemy1);
    }

    private void update() {
        hero1.update();
        hero2.update();
        hero3.update();
        hero4.update();
        enemy1.update();
        enemy2.update();
        enemy3.update();
        enemy4.update();
        skill1.update();
        skill2.update();
        skill3.update();
        skill4.update();
        portraitActor.update();
    }

    private void checkEnemyTurn() {
        while (Dank.enemyHeroesList.contains(Dank.activeHero)) {
            artificialIntelligence();
            checkIfGameEnded();
            clearTargetsAndSortHeroList();
        }
    }

    private void ifComputerBegins() {
        while (Dank.enemyHeroesList.contains(Dank.activeHero)) {
            artificialIntelligence();
            checkIfGameEnded();
            clearTargetsAndSortHeroList();
        }
    }

    private void resetHealthAndStuff() {
        for (Hero hero : Dank.allHeroesInGameList) {
            hero.healthCurrent = hero.healthTotal;
        }
    }

    private void checkIfGameEnded() {
        boolean win = true;
        boolean lose = true;
        for (Hero hero : Dank.enemyHeroesList) {
            if (hero.healthCurrent > 0) {
                win = false;
            }
        }
        for (Hero hero : Dank.chosenHeroesList) {
            if (hero.healthCurrent > 0) {
                lose = false;
            }
        }
        if (win) {
            for (Hero hero : Dank.allHeroesInGameList) {
                hero.healthCurrent = hero.healthTotal;
            }
            Image winImage = new Image(new Texture(Gdx.files.internal("inne/victory.png")));
            winImage.setX(270);
            winImage.setY(215);
            table.addActor(winImage);
            resetHealthAndStuff();
        }
        if (lose) {
            for (Hero hero : Dank.allHeroesInGameList) {
                hero.healthCurrent = hero.healthTotal;
            }
            Image loseImage = new Image(new Texture(Gdx.files.internal("inne/defeat.png")));
            loseImage.setX(270);
            loseImage.setY(215);
            table.addActor(loseImage);
            resetHealthAndStuff();
        }
    }

    private void artificialIntelligence() {
        int i;
        for (i = 0; ; i++) {
            if (i == 4) {
                checkIfGameEnded();
                return;
            }
            if (Dank.chosenHeroesList.get(i).healthCurrent > 0) {
                break;
            }
        }
        targetList.add(Dank.activeHero);
        targetList.add(Dank.chosenHeroesList.get(i));
        Dank.activeHero.getSkill(0).cast(targetList);
    }

    private void clearTargetsAndSortHeroList() {
        targetMode = false;
        targetList.clear();
        Dank.thisTurnLeftHeroesList.remove(Dank.activeHero);
        Dank.thisTurnLeftHeroesList.sort((new Comparator<Hero>() {

            @Override
            public int compare(Hero o1, Hero o2) {
                if (o1.initiative < o2.initiative)
                    return 1;
                if (o1.initiative == o2.initiative)
                    return 0;
                if (o1.initiative > o2.initiative)
                    return -1;
                return -2; //Error
            }
        }));
        Dank.allHeroesInGameList.sort((new Comparator<Hero>() {

            @Override
            public int compare(Hero o1, Hero o2) {
                if (o1.initiative < o2.initiative)
                    return 1;
                if (o1.initiative == o2.initiative)
                    return 0;
                if (o1.initiative > o2.initiative)
                    return -1;
                return -2; //Error
            }
        }));
        if (Dank.thisTurnLeftHeroesList.isEmpty()) {
            if(!passHeroesList.isEmpty()) {
                passHeroesList.sort((new Comparator<Hero>() {

                    @Override
                    public int compare(Hero o1, Hero o2) {
                        if (o1.initiative < o2.initiative)
                            return 1;
                        if (o1.initiative == o2.initiative)
                            return 0;
                        if (o1.initiative > o2.initiative)
                            return -1;
                        return -2; //Error
                    }
                }));
                Dank.thisTurnLeftHeroesList.addAll(passHeroesList);
                passPhase = true;
            }
            else {
                Dank.thisTurnLeftHeroesList.addAll(Dank.allHeroesInGameList);
            }
        }
        Dank.activeHero = Dank.thisTurnLeftHeroesList.get(0);
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