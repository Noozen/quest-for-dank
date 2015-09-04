package com.dankquest.game.com.dankquest.game.logic;

import com.dankquest.game.com.dankquest.game.logic.skill.ArrowSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.FireballSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.SlamSkill;
import com.dankquest.game.com.dankquest.game.logic.skill.TwinBladeSkill;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Miko on 2015-08-28.
 */
public class Dank {

    private Dank() {
    }

    public static List<Hero> ownedHeroesList;
    public static List<Hero> chosenHeroesList;
    public static List<Item> unassignedItemsList;

    public static Hero activeHero;

    static {
        ownedHeroesList = new ArrayList<Hero>();
        chosenHeroesList = new ArrayList<Hero>();
        unassignedItemsList = new ArrayList<Item>();

        //Przyk�adowe dane, w przysz�o�ci powinne by� �adowane z pliku savu
        Hero exampleHero;

        exampleHero = new Hero();
        exampleHero.name = "Noozen";
        exampleHero.heroClass = HeroClass.WARRIOR;
        exampleHero.heathTotal = 200;
        exampleHero.attackDamage = 5;
        exampleHero.skill1 = new ArrowSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        ownedHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Xental";
        exampleHero.heroClass = HeroClass.WARRIOR;
        exampleHero.heathTotal = 100;
        exampleHero.attackDamage = 10;
        exampleHero.skill1 = new ArrowSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        ownedHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Antah";
        exampleHero.heroClass = HeroClass.MAGE;
        exampleHero.heathTotal = 50;
        exampleHero.attackDamage = 20;
        exampleHero.skill1 = new ArrowSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        ownedHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Noctiphobia";
        exampleHero.heroClass = HeroClass.MAGE;
        exampleHero.heathTotal = 60;
        exampleHero.attackDamage = 15;
        exampleHero.skill1 = new ArrowSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        ownedHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Nachrichter";
        exampleHero.heroClass = HeroClass.WARRIOR;
        exampleHero.heathTotal = 80;
        exampleHero.attackDamage = 12;
        exampleHero.skill1 = new ArrowSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        ownedHeroesList.add(exampleHero);

        exampleHero = new Hero();
        exampleHero.name = "Ray";
        exampleHero.heroClass = HeroClass.MAGE;
        exampleHero.heathTotal = 30;
        exampleHero.attackDamage = 30;
        exampleHero.skill1 = new ArrowSkill();
        exampleHero.skill2 = new FireballSkill();
        exampleHero.skill3 = new SlamSkill();
        exampleHero.skill4 = new TwinBladeSkill();
        ownedHeroesList.add(exampleHero);

        ownedHeroesList.sort(new Comparator<Hero>() {

            @Override
            public int compare(Hero o1, Hero o2) {
                return o1.name.compareTo(o2.name);
            }
        });
    }

}
