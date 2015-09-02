package com.dankquest.game.com.dankquest.game.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Miko on 2015-08-28.
 */
public class Dank {
    private Dank(){};

    private static List<Hero> ownedHeroesList;
    private static List<Hero> chosenHeroesList;
    private static List<Item> unassignedItemsList;

    static {
        ownedHeroesList = new ArrayList<Hero>();
        chosenHeroesList = new ArrayList<Hero>();
        unassignedItemsList = new ArrayList<Item>();

        //Przyk³adowe dane, w przysz³oœci powinne byæ ³adowane z pliku savu
        Hero exampleHero;

        exampleHero = new Hero();
        exampleHero.name = "Noozen";
        exampleHero.heroClass = HeroClass.WARRIOR;
        exampleHero.heathTotal = 200;
        exampleHero.attackDamage = 5;
        ownedHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Xental";
        exampleHero.heroClass = HeroClass.WARRIOR;
        exampleHero.heathTotal = 100;
        exampleHero.attackDamage = 10;
        ownedHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Antah";
        exampleHero.heroClass = HeroClass.MAGE;
        exampleHero.heathTotal = 50;
        exampleHero.attackDamage = 20;
        ownedHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Noctiphobia";
        exampleHero.heroClass = HeroClass.MAGE;
        exampleHero.heathTotal = 60;
        exampleHero.attackDamage = 15;
        ownedHeroesList.add(exampleHero);
    }
}
