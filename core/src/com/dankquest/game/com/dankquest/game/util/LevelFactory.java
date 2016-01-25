package com.dankquest.game.com.dankquest.game.util;

import com.dankquest.game.com.dankquest.game.logic.Dank;
import com.dankquest.game.com.dankquest.game.logic.heroes.*;

/**
 * Created by KTamo_000 on 2016-01-21.
 */
public final class LevelFactory {

    public static void setEnemyHeroList(int levelName){
        Dank.enemyHeroesList.clear();
        switch (levelName) {
            case 1:
                for(int i = 0; i< 4; i++)
                    Dank.enemyHeroesList.add(new GoblinHero("Goblin"));
                return;
            case 2:
                for(int i = 0; i< 4; i++)
                    Dank.enemyHeroesList.add(new BigGoblinHero("Big Goblin"));
                return;
            case 3:
                for(int i = 0; i< 4; i++)
                    Dank.enemyHeroesList.add(new UltraGoblinHero("Ultra Goblin"));
                return;
        }
    }
}

