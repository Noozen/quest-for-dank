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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dankquest.game.com.dankquest.game.actors.CastAnimationActor;
import com.dankquest.game.com.dankquest.game.actors.HeroActor;
import com.dankquest.game.com.dankquest.game.actors.PortraitActor;
import com.dankquest.game.com.dankquest.game.actors.SkillIconActor;
import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.logic.Hero;
import com.dankquest.game.com.dankquest.game.util.Assets;
import com.dankquest.game.com.dankquest.game.util.DankMusic;
import com.dankquest.game.com.dankquest.game.util.DankUtil;

import java.util.Collections;
import java.util.Iterator;

/**
 * Created by Antah on 2015/09/02.
 */
public class DankGame extends BasicScreen {
    private Stage stage;
    private Table table;
    //Our beautiful assets
    private Image backgroundFloorImage, backgroundWallImage;
    //UI
    private Skin skin;
    private TextButton retreatButton, castButton, passButton;
    private PortraitActor portraitActor;
    private SkillIconActor skill1, skill2, skill3, skill4;
    //Heroes
    private HeroActor hero1, hero2, hero3, hero4;
    private HeroActor enemy1, enemy2, enemy3, enemy4;
    //Timer
    private Timer AITimer = new Timer();
    //Cast animation
    private CastAnimationActor castAnimationActor;

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
        processComputerTurns();
        update();
    }

    private void heroesListSetup() {
        Dank.allHeroesInGameList.addAll(Dank.chosenHeroesList);
        Dank.allHeroesInGameList.addAll(Dank.enemyHeroesList);
        Dank.thisTurnLeftHeroesList.addAll(Dank.allHeroesInGameList);
        Collections.sort(Dank.thisTurnLeftHeroesList, DankUtil.descendingInitiativeComparator);
        Dank.activeHero = Dank.allHeroesInGameList.get(0);
        Dank.activeHero.reduceBuffDuration();
    }

    private void stageSetup() {
        stage = new Stage(new FitViewport(640, 480));
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

    }

    private void musicSetup() {
        DankMusic.changeSoundtrack("music/Battle2.ogg");
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
        skin = Assets.manager.get("skins/rainbowpack.json", Skin.class);

        retreatButtonSetup();
        castButtonSetup();
        passButtonSetup();
        skillsSetup();
        heroesSetup();
        enemiesSetup();

        //Portrait Actor
        portraitActor = new PortraitActor(Dank.activeHero);
        table.addActor(portraitActor);
    }

    private void retreatButtonSetup() {
        retreatButton = new TextButton("Retreat", skin, "orange_yellow_fat");

        retreatButton.setWidth(200);
        retreatButton.setHeight(50);

        retreatButton.setX(10);
        retreatButton.setY(420);

        retreatButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                return;
                /*
                if (Dank.enemyTurnInProgress == true || Dank.animationInProgress == true) {
                    return;
                }
                resetHealthAndStuff();
                Dank.chosenHeroesList.clear();
                game.setScreen(new GameMenu(game));
                */
            }
        });

        table.addActor(retreatButton);
    }

    private void passButtonSetup() {
        passButton = new TextButton("Pass", skin, "orange_yellow_fat");

        passButton.setWidth(100);
        passButton.setHeight(50);

        passButton.setX(530);
        passButton.setY(420);

        passButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                return;
                /*
                if (Dank.passPhase == true) {
                    return;
                }
                if (Dank.enemyTurnInProgress == true || Dank.animationInProgress == true) {
                    return;
                }
                Dank.characterPassed = true;
                Dank.passHeroesList.add(Dank.activeHero);
                checkIfGameEnded();
                clearTargetsUpdateBuffsSortHeroList();
                processComputerTurns();
                update();
                */
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
                if (Dank.skillCastNumber == 0)
                    return;
                if (Dank.targetList.size() != Dank.activeHero.getSkill(Dank.skillCastNumber).getAmountOfTargets() + 1) {
                    return;
                }
                if (Dank.enemyTurnInProgress == true || Dank.animationInProgress == true) {
                    return;
                }
                if (Dank.chosenHeroesList.contains(Dank.activeHero)) {
                    Dank.animationInProgress = true;
                    Timer timer = new Timer();
                    castAnimationActor = new CastAnimationActor(Dank.activeHero.getSkill(Dank.skillCastNumber));
                    stage.addActor(castAnimationActor);
                    timer.scheduleTask(new Timer.Task() {
                        @Override
                        public void run() {
                            castAnimationActor.remove();
                            Dank.activeHero.getSkill(Dank.skillCastNumber).cast();
                            Dank.skillCastNumber = 0;
                            clearTargetsUpdateBuffsSortHeroList();
                            if(checkIfGameEnded() == true)
                                return;
                            update();
                            processComputerTurns();
                            Dank.animationInProgress = false;
                        }
                    }, Dank.activeHero.getSkill(Dank.skillCastNumber).getAnimationDuration());
                }
            }
        });
        table.addActor(castButton);
    }

    private void processComputerTurns() {
        if (Dank.enemyHeroesList.contains(Dank.activeHero)) {
            Dank.enemyTurnInProgress = true;
            artificialIntelligence();
            castAnimationActor = new CastAnimationActor(Dank.activeHero.getSkill(Dank.skillCastNumber));
            stage.addActor(castAnimationActor);
            AITimer.scheduleTask(new Timer.Task() {

                @Override
                public void run() {
                    castAnimationActor.remove();
                    Dank.activeHero.getSkill(Dank.skillCastNumber).cast();
                    Dank.skillCastNumber = 0;
                    clearTargetsUpdateBuffsSortHeroList();
                    if (checkIfGameEnded())
                        return;
                    update();
                    processComputerTurns();
                }
            }, Dank.activeHero.getSkill(Dank.skillCastNumber).skillAnimation.getAnimationDuration());
        } else {
            Dank.enemyTurnInProgress = false;
        }
    }

    private void skillsSetup() {
        skill1 = new SkillIconActor(200, 10, 1, this);
        skill2 = new SkillIconActor(260, 10, 2, this);
        skill3 = new SkillIconActor(320, 10, 3, this);
        skill4 = new SkillIconActor(380, 10, 4, this);

        table.addActor(skill1);
        table.addActor(skill2);
        table.addActor(skill3);
        table.addActor(skill4);
    }

    private void heroesSetup() {
        hero1 = new HeroActor(10, 120, 1, true, this);
        hero2 = new HeroActor(60, 180, 2, true, this);
        hero3 = new HeroActor(110, 240, 3, true, this);
        hero4 = new HeroActor(160, 300, 4, true, this);

        Dank.chosenHeroesList.get(0).heroActor = hero1;
        Dank.chosenHeroesList.get(1).heroActor = hero2;
        Dank.chosenHeroesList.get(2).heroActor = hero3;
        Dank.chosenHeroesList.get(3).heroActor = hero4;

        table.addActor(hero4);
        table.addActor(hero3);
        table.addActor(hero2);
        table.addActor(hero1);
    }

    private void enemiesSetup() {
        enemy1 = new HeroActor(566, 120, 1, false, this);
        enemy2 = new HeroActor(516, 180, 2, false, this);
        enemy3 = new HeroActor(466, 240, 3, false, this);
        enemy4 = new HeroActor(416, 300, 4, false, this);

        Dank.enemyHeroesList.get(0).heroActor = enemy1;
        Dank.enemyHeroesList.get(1).heroActor = enemy2;
        Dank.enemyHeroesList.get(2).heroActor = enemy3;
        Dank.enemyHeroesList.get(3).heroActor = enemy4;

        table.addActor(enemy4);
        table.addActor(enemy3);
        table.addActor(enemy2);
        table.addActor(enemy1);
    }

    public void update() {
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
        portraitActor.setHero(Dank.activeHero);
        portraitActor.update();
    }

    private void resetHealthAndStuff() {
        for (Hero hero : Dank.allHeroesInGameList) {
            hero.healthCurrent = hero.healthTotal;
        }
        Dank.thisTurnLeftHeroesList.clear();
        Dank.allHeroesInGameList.clear();
        Dank.targetList.clear();
        Dank.passHeroesList.clear();
    }

    private boolean checkIfGameEnded() {
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
                hero.buffMap.clear();
                hero.healthCurrent = hero.healthTotal;
            }
            resetHealthAndStuff();
            DankMusic.playSound("music/ff14_level_up.mp3");
            game.setScreen(new PostGameMenu(game));
        }
        if (lose) {
            for (Hero hero : Dank.allHeroesInGameList) {
                hero.buffMap.clear();
                hero.healthCurrent = hero.healthTotal;
            }
            resetHealthAndStuff();
            game.setScreen(new PostGameMenu(game));
        }
        return false;
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
        Dank.targetList.add(Dank.activeHero);
        Dank.targetList.add(Dank.chosenHeroesList.get(i));
        Dank.skillCastNumber = 1;
    }

    private void clearTargetsUpdateBuffsSortHeroList() {
        Dank.skillCastNumber = 0;
        Dank.targetList.clear();
        Dank.thisTurnLeftHeroesList.remove(Dank.activeHero);
        removeDeadHeroesFromCurrentTurn();
        Collections.sort(Dank.thisTurnLeftHeroesList, DankUtil.descendingInitiativeComparator);
        Collections.sort(Dank.allHeroesInGameList, DankUtil.descendingInitiativeComparator);
        if (Dank.thisTurnLeftHeroesList.isEmpty()) {
            if (!Dank.passHeroesList.isEmpty()) {
                Collections.sort(Dank.passHeroesList, DankUtil.descendingInitiativeComparator);
                Dank.thisTurnLeftHeroesList.addAll(Dank.passHeroesList);
                Dank.passHeroesList.clear();
                Dank.passPhase = true;
            } else {
                Dank.passPhase = false;
                Dank.thisTurnLeftHeroesList.addAll(Dank.allHeroesInGameList);
                removeDeadHeroesFromCurrentTurn();
                Collections.sort(Dank.thisTurnLeftHeroesList, DankUtil.descendingInitiativeComparator);
            }
        }
        if(Dank.characterPassed == false) {
            Dank.activeHero.reduceBuffDuration();
        } else {
            Dank.characterPassed = false;
        }
        Dank.activeHero = Dank.thisTurnLeftHeroesList.get(0);
    }

    private void removeDeadHeroesFromCurrentTurn() {
        Iterator<Hero> heroIterator = Dank.thisTurnLeftHeroesList.iterator();
        Hero heroEntry;
        while(heroIterator.hasNext()) {
            heroEntry = heroIterator.next();
            if(heroEntry.healthCurrent <=0) {
                heroIterator.remove();
            }
        }

        heroIterator = Dank.passHeroesList.iterator();
        while(heroIterator.hasNext()) {
            heroEntry = heroIterator.next();
            if(heroEntry.healthCurrent <=0) {
                heroIterator.remove();
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }
}