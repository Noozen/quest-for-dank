package com.dankquest.game.com.dankquest.game.logic;

import com.dankquest.game.com.dankquest.game.logic.heroes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        exampleHero = new IceShaperHero("Antah");
        ownedHeroesList.add(exampleHero);

        exampleHero = new PyromancerHero("Xental");
        ownedHeroesList.add(exampleHero);

        exampleHero = new NoozenHero("Noozen");
        ownedHeroesList.add(exampleHero);

        exampleHero = new PyromancerHero("Lixe");
        ownedHeroesList.add(exampleHero);

        exampleHero = new PyromancerHero("Ahmi");
        ownedHeroesList.add(exampleHero);

        exampleHero = new LorewalkerHero("Niebo");
        ownedHeroesList.add(exampleHero);

        exampleHero = new PyromancerHero("Ray");
        ownedHeroesList.add(exampleHero);

        Collections.sort(Dank.ownedHeroesList, DankUtil.ascendingNameComparator);

        //Enemies
        exampleHero = new GoblinHero("Goblin 1");
        enemyHeroesList.add(exampleHero);
        exampleHero = new GoblinHero("Goblin 2");
        enemyHeroesList.add(exampleHero);
        exampleHero = new GoblinHero("Goblin 3");
        enemyHeroesList.add(exampleHero);
        exampleHero = new GoblinHero("Goblin 4");
        enemyHeroesList.add(exampleHero);

    }

}
