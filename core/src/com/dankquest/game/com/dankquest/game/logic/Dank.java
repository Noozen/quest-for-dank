package com.dankquest.game.com.dankquest.game.logic;

import com.badlogic.gdx.graphics.Pixmap;
import com.dankquest.game.com.dankquest.game.logic.buffs.Buff;
import com.dankquest.game.com.dankquest.game.logic.skill.ArrowSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.FireballSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.SlamSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.TwinBladeSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.IcelanceSkill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.dankquest.game.com.dankquest.game.util.DankUtil;

/**
 * Created by Miko on 2015-08-28.
 */
public class Dank {

    private Dank() {
    }

    public static Hero activeHero;

    public static List<Hero> ownedHeroesList;
    public static List<Hero> chosenHeroesList;
    public static List<Item> unassignedItemsList;
    public static List<Hero> enemyHeroesList;
    public static List<Hero> allHeroesInGameList;
    public static List<Hero> thisTurnLeftHeroesList;
    public static List<Hero> targetList;
    public static List<Hero> passHeroesList;

    //Targeting
    public static int skillCastNumber = 0;
    public static boolean passPhase = false;
    public static boolean characterPassed = false;
    public static boolean enemyTurnInProgress = false;
    //Cast animation
    public static boolean animationInProgress;

    static {
        ownedHeroesList = new ArrayList<Hero>();
        chosenHeroesList = new ArrayList<Hero>();
        unassignedItemsList = new ArrayList<Item>();
        enemyHeroesList = new ArrayList<Hero>();
        allHeroesInGameList = new ArrayList<Hero>();
        thisTurnLeftHeroesList = new ArrayList<Hero>();
        targetList = new ArrayList<Hero>();
        passHeroesList = new ArrayList<Hero>();

        Hero exampleHero;

        //Heroes
        exampleHero = new Hero();
        exampleHero.name = "Noozen";
        exampleHero.heroClass = HeroClass.WARRIOR;
        exampleHero.healthTotal = 200;
        exampleHero.healthCurrent = 200;
        exampleHero.attackDamage = 5;
        exampleHero.initiative = 7;
        exampleHero.damageMultiplier = 1;
        exampleHero.skill1 = new ArrowSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        exampleHero.texture = new Pixmap(Gdx.files.internal("heroes/troll.png"));
        exampleHero.buffMap = new HashMap<String, Buff>();
        ownedHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Xental";
        exampleHero.heroClass = HeroClass.WARRIOR;
        exampleHero.healthTotal = 100;
        exampleHero.healthCurrent = 100;
        exampleHero.attackDamage = 10;
        exampleHero.initiative = 9;
        exampleHero.damageMultiplier = 1;
        exampleHero.skill1 = new ArrowSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        exampleHero.texture = new Pixmap(Gdx.files.internal("heroes/goblin.png"));
        exampleHero.buffMap = new HashMap<String, Buff>();
        ownedHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Antah";
        exampleHero.heroClass = HeroClass.MAGE;
        exampleHero.healthTotal = 50;
        exampleHero.healthCurrent = 50;
        exampleHero.attackDamage = 20;
        exampleHero.initiative = 10;
        exampleHero.damageMultiplier = 1;
        exampleHero.skill1 = new IcelanceSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        exampleHero.texture = new  Pixmap(Gdx.files.internal("heroes/dwarf.png"));
        exampleHero.buffMap = new HashMap<String, Buff>();
        ownedHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Noctiphobia";
        exampleHero.heroClass = HeroClass.MAGE;
        exampleHero.healthTotal = 60;
        exampleHero.healthCurrent = 60;
        exampleHero.attackDamage = 15;
        exampleHero.initiative = 6;
        exampleHero.damageMultiplier = 1;
        exampleHero.skill1 = new ArrowSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        exampleHero.texture = new  Pixmap(Gdx.files.internal("heroes/goblin.png"));
        exampleHero.buffMap = new HashMap<String, Buff>();
        ownedHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Nachrichter";
        exampleHero.heroClass = HeroClass.WARRIOR;
        exampleHero.healthTotal = 80;
        exampleHero.healthCurrent = 80;
        exampleHero.attackDamage = 12;
        exampleHero.initiative = 5;
        exampleHero.damageMultiplier = 1;
        exampleHero.skill1 = new ArrowSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        exampleHero.texture = new  Pixmap(Gdx.files.internal("heroes/goblin.png"));
        exampleHero.buffMap = new HashMap<String, Buff>();
        ownedHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Ray";
        exampleHero.heroClass = HeroClass.MAGE;
        exampleHero.healthTotal = 30;
        exampleHero.healthCurrent = 30;
        exampleHero.attackDamage = 30;
        exampleHero.initiative = 11;
        exampleHero.damageMultiplier = 1;
        exampleHero.skill1 = new ArrowSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        exampleHero.texture = new  Pixmap(Gdx.files.internal("heroes/goblin.png"));
        exampleHero.buffMap = new HashMap<String, Buff>();
        ownedHeroesList.add(exampleHero);

        ownedHeroesList.sort(DankUtil.ascendingNameComparator);

        //Enemies
        exampleHero = new Hero();
        exampleHero.name = "Enemy1";
        exampleHero.heroClass = HeroClass.MAGE;
        exampleHero.healthTotal = 50;
        exampleHero.healthCurrent = 50;
        exampleHero.attackDamage = 20;
        exampleHero.initiative = 9;
        exampleHero.damageMultiplier = 1;
        exampleHero.skill1 = new ArrowSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        exampleHero.texture = new  Pixmap(Gdx.files.internal("heroes/dwarf.png"));
        exampleHero.buffMap = new HashMap<String, Buff>();
        enemyHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Enemy2";
        exampleHero.heroClass = HeroClass.MAGE;
        exampleHero.healthTotal = 50;
        exampleHero.healthCurrent = 50;
        exampleHero.attackDamage = 20;
        exampleHero.initiative = 9;
        exampleHero.damageMultiplier = 1;
        exampleHero.skill1 = new ArrowSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        exampleHero.texture = new  Pixmap(Gdx.files.internal("heroes/dwarf.png"));
        exampleHero.buffMap = new HashMap<String, Buff>();
        enemyHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Enemy3";
        exampleHero.heroClass = HeroClass.MAGE;
        exampleHero.healthTotal = 50;
        exampleHero.healthCurrent = 50;
        exampleHero.attackDamage = 20;
        exampleHero.initiative = 9;
        exampleHero.damageMultiplier = 1;
        exampleHero.skill1 = new ArrowSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        exampleHero.texture = new  Pixmap(Gdx.files.internal("heroes/dwarf.png"));
        exampleHero.buffMap = new HashMap<String, Buff>();
        enemyHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Enemy4";
        exampleHero.heroClass = HeroClass.MAGE;
        exampleHero.healthTotal = 50;
        exampleHero.healthCurrent = 50;
        exampleHero.attackDamage = 20;
        exampleHero.initiative = 9;
        exampleHero.damageMultiplier = 1;
        exampleHero.skill1 = new ArrowSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        exampleHero.texture = new  Pixmap(Gdx.files.internal("heroes/dwarf.png"));
        exampleHero.buffMap = new HashMap<String, Buff>();
        enemyHeroesList.add(exampleHero);

    }

}
