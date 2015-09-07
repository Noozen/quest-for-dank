package com.dankquest.game.com.dankquest.game.util;

import com.dankquest.game.com.dankquest.game.logic.Hero;

import java.util.Comparator;

/**
 * Created by Miko on 2015-09-07.
 */
public class DankUtil {

    private DankUtil() {}

    public static Comparator<Hero> ascendingNameComparator;
    public static Comparator<Hero> descendingInitiativeComparator;

    static {
        ascendingNameComparator = new Comparator<Hero>() {
            @Override
            public int compare(Hero o1, Hero o2) {
                return o1.name.compareTo(o2.name);
            }
        };

        descendingInitiativeComparator = new Comparator<Hero>() {

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
        };
    }

}
