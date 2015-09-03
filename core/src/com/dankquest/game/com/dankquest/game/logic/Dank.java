package com.dankquest.game.com.dankquest.game.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Miko on 2015-08-28.
 */
public class Dank {
    private Dank(){};

    public static List<Hero> ownedHeroesList;
    public static List<Hero> chosenHeroesList;
    public static List<Item> unassignedItemsList;

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

        exampleHero = new Hero();
        exampleHero.name = "Nachrichter";
        exampleHero.heroClass = HeroClass.WARRIOR;
        exampleHero.heathTotal = 80;
        exampleHero.attackDamage = 12;
        ownedHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Niebo";
        exampleHero.heroClass = HeroClass.MAGE;
        exampleHero.heathTotal = 1;
        exampleHero.attackDamage = 30;
        ownedHeroesList.add(exampleHero);

        ownedHeroesList.sort(new Comparator<Hero>() {

            @Override
            public int compare(Hero o1, Hero o2) {
                return o1.name.compareTo(o2.name);
            }
        });
    }
}
