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
            public int compare(Hero o1, Hero o2) {
                return o1.name.compareTo(o2.name);
            }
        };

        descendingInitiativeComparator = new Comparator<Hero>() {
            public int compare(Hero o1, Hero o2) {
                if (o1.getInitiative() < o2.getInitiative())
                    return 1;
                if (o1.getInitiative() == o2.getInitiative())
                    return 0;
                if (o1.getInitiative() > o2.getInitiative())
                    return -1;
                return -2; //Error
            }
        };
    }

}
