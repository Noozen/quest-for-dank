package com.dankquest.game.com.dankquest.game.logic.buffs;

import com.badlogic.gdx.graphics.Pixmap;
import com.dankquest.game.com.dankquest.game.logic.heroes.Hero;

/**
 * Created by Miko on 2015-09-09.
 */
abstract public class Buff {

    public Buff() {}

    protected Pixmap basePixmap;
    public int buffDuration;
    String name;
    abstract public void useBuff(Hero hero);
    public Pixmap getImage() {
        return basePixmap;
    }
    public String getName() {
        return name;
    }


}
